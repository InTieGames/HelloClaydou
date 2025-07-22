
/**
 * HelloClaydou - A Bukkit plugin that creates interactive pig entities
 * 
 * This plugin allows players to spawn and interact with special pig entities called "Claydou".
 * These pigs can have simple conversations with players through a state-based interaction system.
 * 
 * @version 1.5
 * @author IntieGames
 */
package com.intiegames.divinecraft.helloclaydou;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Objects;

/**
 * Main plugin class for HelloClaydou.
 * 
 * This plugin creates interactive pig entities that can have conversations with players.
 * Players can spawn new Claydou pigs using the /claydou command and interact with them
 * by right-clicking. The pigs will respond with different messages based on the 
 * conversation state.
 */
public class HelloClaydou extends JavaPlugin implements Listener, CommandExecutor {

    /** Metadata key for storing the interaction state of a Claydou pig */
    private static final String CLAYDOU_INTERACTION_KEY = "claydou_interaction";

    /** Metadata key for tracking whether a Claydou pig has seen a player before */
    private static final String HAS_SEEN_PLAYER_KEY = "has_seen_player";

    /** Constant zero velocity vector used to stop entity movement */
    private static final Vector ZERO_VELOCITY = new Vector(0, 0, 0);

    /** Language manager for internationalization */
    private LanguageManager languageManager;

    /**
     * Called when the plugin is enabled.
     * Registers event listeners and command executors.
     */
    @Override
    public void onEnable() {
        // Save default config
        saveDefaultConfig();

        // Initialize language manager
        languageManager = new LanguageManager(this);

        // Register event listeners and command executors
        Bukkit.getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(this.getCommand("claydou")).setExecutor(this);

        // Log plugin enabled message
        getLogger().info(languageManager.getMessage("plugin.enabled"));
    }

    /**
     * Called when the plugin is disabled.
     * Performs cleanup operations.
     */
    @Override
    public void onDisable() {
        // Clean up metadata for all entities when plugin is disabled
        for (org.bukkit.World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.hasMetadata(CLAYDOU_INTERACTION_KEY)) {
                    entity.removeMetadata(CLAYDOU_INTERACTION_KEY, this);
                }
                if (entity.hasMetadata(HAS_SEEN_PLAYER_KEY)) {
                    entity.removeMetadata(HAS_SEEN_PLAYER_KEY, this);
                }
            }
        }

        // Log plugin disabled message
        getLogger().info(languageManager.getMessage("plugin.disabled"));
    }

    /**
     * Handles entity death events.
     * 
     * This method cleans up metadata when a Claydou pig dies to prevent memory leaks.
     * 
     * @param event The EntityDeathEvent containing information about the entity death
     */
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        // Clean up metadata when entity dies
        if (entity.hasMetadata(CLAYDOU_INTERACTION_KEY)) {
            entity.removeMetadata(CLAYDOU_INTERACTION_KEY, this);
        }
        if (entity.hasMetadata(HAS_SEEN_PLAYER_KEY)) {
            entity.removeMetadata(HAS_SEEN_PLAYER_KEY, this);
        }
    }

    /**
     * Handles player interactions with entities.
     * 
     * This method is triggered when a player right-clicks on an entity. If the entity
     * is a pig, it will be treated as a Claydou and will engage in a conversation with
     * the player. The conversation progresses through different states, with different
     * messages displayed to the player at each state.
     * 
     * @param event The PlayerInteractEntityEvent containing information about the interaction
     */
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();

        // Early return if not a pig
        if (entity.getType() != EntityType.PIG) {
            return;
        }

        Player player = event.getPlayer();

        // Fazer o porco olhar para o jogador e parar o movimento
        faceEntityToPlayer(entity, player);
        entity.setVelocity(ZERO_VELOCITY); // Parar o movimento usando a constante

        // Get current interaction state
        int interactionState = getInteractionState(entity);

        // Handle interaction based on state
        switch (interactionState) {
            case 0:
                player.sendMessage(ChatColor.WHITE + languageManager.getMessage("conversation.player.greeting", player));
                setInteractionState(entity, 1);
                break;
            case 1:
                player.sendMessage(ChatColor.LIGHT_PURPLE + languageManager.getMessage("conversation.claydou.welcome", player));
                setInteractionState(entity, 2);
                break;
            case 2:
                player.sendMessage(ChatColor.WHITE + languageManager.getMessage("conversation.player.greeting", player));
                setInteractionState(entity, 3);
                break;
            case 3:
                if (!hasSeenPlayer(entity)) {
                    player.sendMessage(ChatColor.LIGHT_PURPLE + languageManager.getMessage("conversation.claydou.goodbye", player));
                    setSeenPlayer(entity);
                    setInteractionState(entity, 0);
                } else {
                    // Reset the state if the player has already been seen
                    player.sendMessage(ChatColor.LIGHT_PURPLE + languageManager.getMessage("conversation.claydou.restart", player));
                    setInteractionState(entity, 0);
                }
                break;
            default:
                setInteractionState(entity, 0);
                break;
        }
    }

    /**
     * Handles entity targeting events.
     * 
     * This method prevents Claydou pigs from targeting other entities while they are
     * engaged in a conversation with a player. It checks if the entity is a pig and
     * if it has an active interaction state (greater than 0), and if so, cancels the
     * targeting event.
     * 
     * @param event The EntityTargetEvent containing information about the targeting
     */
    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
        // Impedir que o porco interaja com outros alvos enquanto está em diálogo
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.PIG && getMetadataInt(entity, CLAYDOU_INTERACTION_KEY, 0) > 0) {
            // Only cancel if the pig is in an active conversation state
            event.setCancelled(true);
        }
    }

    /**
     * Handles the execution of the /claydou command.
     * 
     * This method is called when a player or the console executes the /claydou command.
     * It spawns a new Claydou pig entity near the player who executed the command.
     * The command can only be executed by players, not by the console or other command senders.
     * 
     * @param sender The sender who executed the command
     * @param command The command that was executed
     * @param label The alias of the command that was used
     * @param args The arguments provided with the command
     * @return true if the command was handled successfully, false otherwise
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if this is our command
        if (!"claydou".equals(command.getName())) {
            return false;
        }

        // Handle console sender
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(languageManager.getMessage("command.player-only"));
            return true;
        }

        // Handle non-player, non-console senders
        if (!(sender instanceof Player)) {
            return false;
        }

        // Handle player sender
        try {
            Player player = (Player) sender;

            // Check if player has permission
            if (!player.hasPermission("helloclaydou.spawn")) {
                player.sendMessage(ChatColor.RED + "Você não tem permissão para usar este comando.");
                return true;
            }

            // Get spawn distance from config
            int spawnDistance = getConfig().getInt("settings.spawn-distance", 2);

            // Create location at configured distance away from player
            Location loc = player.getLocation().clone().add(spawnDistance, 0, 0);

            // Spawn and initialize the pig
            Entity pig = player.getWorld().spawnEntity(loc, EntityType.PIG);
            setInteractionState(pig, 0);

            player.sendMessage(ChatColor.LIGHT_PURPLE + languageManager.getMessage("command.success", player));
            return true;
        } catch (Exception e) {
            String errorMsg = languageManager.getMessage("command.error").replace("{0}", e.getMessage());
            sender.sendMessage(ChatColor.RED + errorMsg);
            getLogger().warning(errorMsg);
            return false;
        }
    }

    /**
     * Makes an entity face towards a player.
     * 
     * This method calculates the appropriate yaw angle to make the entity
     * face directly towards the player, and then teleports the entity to
     * the same location but with the updated yaw angle.
     * 
     * @param entity The entity that should face the player
     * @param player The player that the entity should face
     */
    private void faceEntityToPlayer(Entity entity, Player player) {
        // Get locations directly without creating new objects
        Location loc = entity.getLocation();

        // Calculate yaw directly from player and entity locations
        double dx = player.getLocation().getX() - loc.getX();
        double dz = player.getLocation().getZ() - loc.getZ();
        loc.setYaw((float) Math.toDegrees(Math.atan2(dz, dx)) - 90);

        // Use teleport with the modified location
        entity.teleport(loc);
    }

    /**
     * Gets the current interaction state of a Claydou pig.
     * 
     * The interaction state determines what message will be displayed
     * when a player interacts with the pig.
     * 
     * @param entity The entity to get the interaction state from
     * @return The current interaction state (0-3), or 0 if not set
     */
    private int getInteractionState(Entity entity) {
        return getMetadataInt(entity, CLAYDOU_INTERACTION_KEY, 0);
    }

    /**
     * Helper method to get integer metadata value with a default fallback.
     * 
     * This method safely retrieves an integer metadata value from an entity,
     * returning a default value if the metadata doesn't exist or is empty.
     * 
     * @param entity The entity to get metadata from
     * @param key The metadata key to look for
     * @param defaultValue The default value to return if metadata is not found
     * @return The integer value of the metadata, or defaultValue if not found
     */
    private int getMetadataInt(Entity entity, String key, int defaultValue) {
        if (!entity.hasMetadata(key)) {
            return defaultValue;
        }
        List<MetadataValue> metadata = entity.getMetadata(key);
        return metadata.isEmpty() ? defaultValue : metadata.get(0).asInt();
    }

    /**
     * Sets the interaction state of a Claydou pig.
     * 
     * This method updates the pig's interaction state and, if the state
     * is set to 3, also resets the "has seen player" flag to false.
     * 
     * @param entity The entity to set the interaction state for
     * @param state The new interaction state (0-3)
     */
    private void setInteractionState(Entity entity, int state) {
        setMetadataValue(entity, CLAYDOU_INTERACTION_KEY, state);
        if (state == 3) {
            setMetadataValue(entity, HAS_SEEN_PLAYER_KEY, false);
        }
    }

    /**
     * Checks if a Claydou pig has seen a player before.
     * 
     * This is used in the final state of the conversation to determine
     * whether to end the conversation or start it over.
     * 
     * @param entity The entity to check
     * @return true if the pig has seen a player before, false otherwise
     */
    private boolean hasSeenPlayer(Entity entity) {
        return getMetadataBoolean(entity, HAS_SEEN_PLAYER_KEY, false);
    }

    /**
     * Helper method to get boolean metadata value with a default fallback.
     * 
     * This method safely retrieves a boolean metadata value from an entity,
     * returning a default value if the metadata doesn't exist or is empty.
     * 
     * @param entity The entity to get metadata from
     * @param key The metadata key to look for
     * @param defaultValue The default value to return if metadata is not found
     * @return The boolean value of the metadata, or defaultValue if not found
     */
    private boolean getMetadataBoolean(Entity entity, String key, boolean defaultValue) {
        if (!entity.hasMetadata(key)) {
            return defaultValue;
        }
        List<MetadataValue> metadata = entity.getMetadata(key);
        return metadata.isEmpty() ? defaultValue : metadata.get(0).asBoolean();
    }

    /**
     * Marks a Claydou pig as having seen a player.
     * 
     * This is used in the final state of the conversation to remember
     * that the pig has already completed a conversation with a player.
     * 
     * @param entity The entity to mark as having seen a player
     */
    private void setSeenPlayer(Entity entity) {
        setMetadataValue(entity, HAS_SEEN_PLAYER_KEY, true);
    }

    /**
     * Helper method to set metadata value on an entity.
     * 
     * This method sets a metadata value on an entity using a FixedMetadataValue
     * with this plugin as the owner.
     * 
     * @param entity The entity to set metadata on
     * @param key The metadata key to set
     * @param value The value to set for the metadata
     */
    private void setMetadataValue(Entity entity, String key, Object value) {
        entity.setMetadata(key, new FixedMetadataValue(this, value));
    }
}
