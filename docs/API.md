# HelloClaydou API Documentation

This document provides detailed information about the API of the HelloClaydou plugin, including classes, methods, and events that can be used by other plugins.

## Main Plugin Class

The main plugin class is `com.intiegames.divinecraft.helloclaydou.HelloClaydou`, which extends `JavaPlugin` and implements `Listener` and `CommandExecutor`.

### Public Methods

Currently, the HelloClaydou plugin does not expose any public API methods for other plugins to use. All methods are private or protected and are intended for internal use only.

In future versions, we plan to expose methods for other plugins to interact with Claydou pigs programmatically.

## Metadata Keys

The plugin uses the following metadata keys to store information about Claydou pigs:

- `claydou_interaction`: Stores the current conversation state (0-3)
- `has_seen_player`: Tracks whether the pig has already given its farewell message

Other plugins can check if an entity is a Claydou pig by checking if it has these metadata keys:

```java
public boolean isClaydouPig(Entity entity) {
    return entity.getType() == EntityType.PIG && entity.hasMetadata("claydou_interaction");
}
```

## Events

The plugin listens to the following Bukkit events:

- `PlayerInteractEntityEvent`: Triggered when a player right-clicks on an entity
- `EntityTargetEvent`: Triggered when an entity targets another entity
- `EntityDeathEvent`: Triggered when an entity dies

The plugin does not currently provide any custom events. In future versions, we plan to add custom events for Claydou interactions.

## Commands

The plugin provides the following command:

- `/claydou`: Spawns a new Claydou pig near the player

### Command Parameters

The `/claydou` command does not currently accept any parameters.

## Permissions

The plugin uses the following permission:

- `helloclaydou.spawn`: Allows players to spawn Claydou pigs (default: op)

## Integration with Other Plugins

Currently, the HelloClaydou plugin does not provide specific integration points for other plugins. However, other plugins can interact with Claydou pigs using the Bukkit API and the metadata keys mentioned above.

### Example: Checking if a Player is Interacting with a Claydou Pig

```java
@EventHandler
public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
    Entity entity = event.getRightClicked();
    if (entity.getType() == EntityType.PIG && entity.hasMetadata("claydou_interaction")) {
        // This is a Claydou pig
        int interactionState = 0;
        List<MetadataValue> metadata = entity.getMetadata("claydou_interaction");
        if (!metadata.isEmpty()) {
            interactionState = metadata.get(0).asInt();
        }
        
        // Do something based on the interaction state
        // ...
    }
}
```

## Future API Plans

In future versions, we plan to expand the API to include:

1. Public methods for spawning and managing Claydou pigs
2. Custom events for Claydou interactions
3. API for adding custom dialogue options
4. Integration with other plugins for enhanced functionality

## API Versioning

The API is currently in its initial state and does not follow a specific versioning scheme. In future versions, we will implement proper API versioning to ensure backward compatibility.

## Support

If you have questions about using the HelloClaydou API in your plugin, please create an issue on our GitHub repository.