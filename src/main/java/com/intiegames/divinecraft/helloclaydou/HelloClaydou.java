
//1.5
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
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.List;

public class HelloClaydou extends JavaPlugin implements Listener, CommandExecutor {

    private static final String CLAYDOU_INTERACTION_KEY = "claydou_interaction";
    private static final String HAS_SEEN_PLAYER_KEY = "has_seen_player";

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("claydou").setExecutor(this);
        getLogger().info("claydouCommunicationPlugin habilitado.");
    }

    @Override
    public void onDisable() {
        getLogger().info("claydouCommunicationPlugin desabilitado.");
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if (entity.getType() == EntityType.PIG) {
            // Fazer o porco olhar para o jogador e parar o movimento
            faceEntityToPlayer(entity, player);
            entity.setVelocity(new Vector(0, 0, 0)); // Parar o movimento

            int interactionState = getInteractionState(entity);

            switch (interactionState) {
                case 0:
                    player.sendMessage(ChatColor.WHITE + "Olá Claydou");
                    setInteractionState(entity, 1);
                    break;
                case 1:
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "Olá que bom ver você!!");
                    setInteractionState(entity, 2);
                    break;
                case 2:
                    player.sendMessage(ChatColor.WHITE + "Olá Claydou");
                    setInteractionState(entity, 3);
                    break;
                case 3:
                    if (!hasSeenPlayer(entity)) {
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "Oi, já falei contigo, tenha um bom dia!");
                        setSeenPlayer(entity);
                    }
                    break;
                default:
                    setInteractionState(entity, 0);
                    break;
            }
        }
    }

    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
        // Impedir que o porco interaja com outros alvos enquanto está em diálogo
        if (event.getEntity().getType() == EntityType.PIG) {
            if (event.getEntity().hasMetadata(CLAYDOU_INTERACTION_KEY)) {
                event.setCancelled(true);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("claydou")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Location loc = player.getLocation();
                loc.add(2, 0, 0); // Adicionar 2 blocos de distância do jogador para a localização do novo porco
                player.getWorld().spawnEntity(loc, EntityType.PIG);
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Um novo Claydou foi criado!");
                return true;
            } else if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage("Este comando só pode ser usado por jogadores.");
                return true;
            }
        }
        return false;
    }

    private void faceEntityToPlayer(Entity entity, Player player) {
        Location loc = entity.getLocation();
        Location playerLoc = player.getLocation();

        double dx = playerLoc.getX() - loc.getX();
        double dz = playerLoc.getZ() - loc.getZ();
        float yaw = (float) Math.toDegrees(Math.atan2(dz, dx)) - 90;
        loc.setYaw(yaw);

        entity.teleport(loc);
    }

    private int getInteractionState(Entity entity) {
        List<MetadataValue> metadata = entity.getMetadata(CLAYDOU_INTERACTION_KEY);
        if (metadata.isEmpty()) {
            return 0;
        } else {
            return metadata.get(0).asInt();
        }
    }

    private void setInteractionState(Entity entity, int state) {
        entity.setMetadata(CLAYDOU_INTERACTION_KEY, new FixedMetadataValue(this, state));
        if (state == 3) {
            entity.setMetadata(HAS_SEEN_PLAYER_KEY, new FixedMetadataValue(this, false));
        }
    }

    private boolean hasSeenPlayer(Entity entity) {
        List<MetadataValue> metadata = entity.getMetadata(HAS_SEEN_PLAYER_KEY);
        if (metadata.isEmpty()) {
            return false;
        } else {
            return metadata.get(0).asBoolean();
        }
    }

    private void setSeenPlayer(Entity entity) {
        entity.setMetadata(HAS_SEEN_PLAYER_KEY, new FixedMetadataValue(this, true));
    }
}