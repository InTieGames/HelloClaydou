# HelloClaydou Implementation Details

This document provides detailed information about the implementation of the HelloClaydou plugin, including class structure, methods, and the underlying mechanics.

## Class Structure

The plugin consists of a single main class `HelloClaydou` that handles all functionality:

```
com.intiegames.divinecraft.helloclaydou.HelloClaydou
```

This class extends `JavaPlugin` and implements `Listener` and `CommandExecutor` interfaces.

## Constants

The plugin defines two constants for metadata keys:

```java
private static final String CLAYDOU_INTERACTION_KEY = "claydou_interaction";
private static final String HAS_SEEN_PLAYER_KEY = "has_seen_player";
```

## Lifecycle Methods

### onEnable()

The `onEnable()` method is called when the plugin is enabled. It performs the following tasks:

1. Registers the plugin as an event listener
2. Registers the "claydou" command with the plugin as its executor
3. Logs a message indicating that the plugin has been enabled

```java
@Override
public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
    this.getCommand("claydou").setExecutor(this);
    getLogger().info("claydouCommunicationPlugin habilitado.");
}
```

### onDisable()

The `onDisable()` method is called when the plugin is disabled. It performs the following tasks:

1. Cleans up metadata for all entities in all worlds
2. Logs a message indicating that the plugin has been disabled

```java
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
    getLogger().info("claydouCommunicationPlugin desabilitado.");
}
```

## Event Handlers

### onEntityDeath(EntityDeathEvent event)

This method is called when an entity dies. It cleans up metadata for the entity if it has any Claydou-related metadata.

```java
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
```

### onPlayerInteractEntity(PlayerInteractEntityEvent event)

This method is called when a player right-clicks on an entity. If the entity is a pig, it:

1. Makes the pig face the player
2. Stops the pig's movement
3. Gets the current interaction state
4. Sends a message to the player based on the interaction state
5. Updates the interaction state

```java
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
```

### onEntityTarget(EntityTargetEvent event)

This method is called when an entity targets another entity. If the entity is a pig with Claydou-related metadata, it cancels the targeting.

```java
@EventHandler
public void onEntityTarget(EntityTargetEvent event) {
    // Impedir que o porco interaja com outros alvos enquanto está em diálogo
    if (event.getEntity().getType() == EntityType.PIG) {
        if (event.getEntity().hasMetadata(CLAYDOU_INTERACTION_KEY)) {
            event.setCancelled(true);
        }
    }
}
```

## Command Handler

### onCommand(CommandSender sender, Command command, String label, String[] args)

This method is called when a player executes the "claydou" command. It:

1. Checks if the sender is a player
2. Checks if the player has the required permission
3. Spawns a new pig near the player
4. Sends a success message to the player

```java
@Override
public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("claydou")) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            
            // Check if player has permission
            if (!player.hasPermission("helloclaydou.spawn")) {
                player.sendMessage(ChatColor.RED + "Você não tem permissão para usar este comando.");
                return true;
            }
            
            try {
                Location loc = player.getLocation();
                loc.add(2, 0, 0); // Adicionar 2 blocos de distância do jogador para a localização do novo porco
                player.getWorld().spawnEntity(loc, EntityType.PIG);
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Um novo Claydou foi criado!");
            } catch (Exception e) {
                player.sendMessage(ChatColor.RED + "Ocorreu um erro ao criar o Claydou.");
                getLogger().warning("Erro ao criar Claydou: " + e.getMessage());
            }
            return true;
        } else if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Este comando só pode ser usado por jogadores.");
            return true;
        } else {
            // Handle other types of command senders
            sender.sendMessage("Este comando só pode ser usado por jogadores.");
            return true;
        }
    }
    return false;
}
```

## Utility Methods

### faceEntityToPlayer(Entity entity, Player player)

This method makes an entity face a player by calculating the appropriate yaw angle and teleporting the entity to the same location with the new yaw.

```java
private void faceEntityToPlayer(Entity entity, Player player) {
    Location loc = entity.getLocation();
    Location playerLoc = player.getLocation();

    double dx = playerLoc.getX() - loc.getX();
    double dz = playerLoc.getZ() - loc.getZ();
    float yaw = (float) Math.toDegrees(Math.atan2(dz, dx)) - 90;
    loc.setYaw(yaw);

    entity.teleport(loc);
}
```

### getInteractionState(Entity entity)

This method retrieves the current interaction state of an entity from its metadata.

```java
private int getInteractionState(Entity entity) {
    if (!entity.hasMetadata(CLAYDOU_INTERACTION_KEY)) {
        return 0;
    }
    List<MetadataValue> metadata = entity.getMetadata(CLAYDOU_INTERACTION_KEY);
    if (metadata.isEmpty()) {
        return 0;
    } else {
        return metadata.get(0).asInt();
    }
}
```

### setInteractionState(Entity entity, int state)

This method sets the interaction state of an entity in its metadata.

```java
private void setInteractionState(Entity entity, int state) {
    entity.setMetadata(CLAYDOU_INTERACTION_KEY, new FixedMetadataValue(this, state));
    if (state == 3) {
        entity.setMetadata(HAS_SEEN_PLAYER_KEY, new FixedMetadataValue(this, false));
    }
}
```

### hasSeenPlayer(Entity entity)

This method checks if an entity has seen a player (used in the final interaction state).

```java
private boolean hasSeenPlayer(Entity entity) {
    if (!entity.hasMetadata(HAS_SEEN_PLAYER_KEY)) {
        return false;
    }
    List<MetadataValue> metadata = entity.getMetadata(HAS_SEEN_PLAYER_KEY);
    if (metadata.isEmpty()) {
        return false;
    } else {
        return metadata.get(0).asBoolean();
    }
}
```

### setSeenPlayer(Entity entity)

This method marks an entity as having seen a player.

```java
private void setSeenPlayer(Entity entity) {
    entity.setMetadata(HAS_SEEN_PLAYER_KEY, new FixedMetadataValue(this, true));
}
```

## Configuration

The plugin currently does not use a configuration file. All settings are hardcoded in the plugin. Future versions may implement configuration options as demonstrated in the `config_example.yml` file.

## Performance Considerations

1. **Metadata Cleanup**: The plugin cleans up metadata when entities die or when the plugin is disabled to prevent memory leaks.
2. **Entity Teleportation**: The `faceEntityToPlayer()` method uses teleportation to change the entity's direction, which could cause visual glitches and is less efficient than using entity-specific methods.
3. **Exception Handling**: The plugin includes exception handling in the `onCommand()` method to prevent crashes.

## Security Considerations

1. **Permission Checks**: The plugin checks if players have the required permission before allowing them to spawn Claydou pigs.
2. **Command Sender Validation**: The plugin validates the command sender type before executing commands.

## Future Implementation Plans

See [FuturePlans.md](FuturePlans.md) for details on planned improvements and new features.