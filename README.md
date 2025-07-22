# HelloClaydou - Interactive Pig Plugin

## Overview
HelloClaydou is a Minecraft Bukkit/Spigot plugin that adds interactive pig entities to your Minecraft server. These special pigs, called "Claydou", can have conversations with players through a state-based interaction system.

## Features
- **Interactive Pigs**: Right-click on a pig to initiate a conversation with Claydou
- **Dialogue System**: Pigs remember conversation state and respond differently based on previous interactions
- **Simple Command**: Use `/claydou` to spawn a new Claydou pig
- **Permission System**: Control who can spawn Claydou pigs with permissions
- **Multi-language Support**: Available in Portuguese, English, Spanish, Russian, and Hebrew
- **Face Tracking**: Pigs will face the player during conversations
- **Behavior Control**: Pigs won't target other entities while in a conversation

## Installation
1. Download the latest release JAR file from the [Releases](https://github.com/yourusername/HelloClaydou/releases) page
2. Place the JAR file in your server's `plugins` folder
3. Restart your server or use a plugin manager to load the plugin
4. The plugin will generate configuration and language files on first run
5. Verify the plugin is loaded by checking the server console for the plugin enabled message

## Usage

### Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/claydou` | Spawns a new Claydou pig near the player | `helloclaydou.spawn` |

### Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `helloclaydou.spawn` | Allows players to spawn Claydou pigs | op |

### Interacting with Claydou

1. Find a pig in your world or spawn one using the `/claydou` command
2. Right-click on the pig to initiate a conversation
3. Continue right-clicking to progress through the dialogue sequence
4. The pig will remember your conversation and respond differently on subsequent interactions

## Technical Details

### Conversation States

Claydou pigs have four conversation states:

1. **Initial Greeting**: "Olá Claydou" (white text)
2. **Friendly Response**: "Olá que bom ver você!!" (light purple text)
3. **Repeat Greeting**: "Olá Claydou" (white text)
4. **Farewell**: "Oi, já falei contigo, tenha um bom dia!" (light purple text)

### Metadata

The plugin uses Bukkit's metadata system to store conversation state:

- `claydou_interaction`: Stores the current conversation state (0-3)
- `has_seen_player`: Tracks whether the pig has already given its farewell message

## Configuration

### Language Settings
The plugin supports multiple languages. You can change the default language in the `config.yml` file:

```yaml
# Default language for the plugin
# Available options: pt_BR (Portuguese), en_US (English), es_ES (Spanish), ru_RU (Russian), he_IL (Hebrew)
language: pt_BR
```

Language files are located in the `plugins/HelloClaydou/lang/` directory. You can edit these files to customize the messages or add new languages.

### Other Settings
You can also configure other settings in the `config.yml` file:

```yaml
# Debug mode (true/false)
debug: false

# Plugin settings
settings:
  # Distance to spawn the pig from the player (in blocks)
  spawn-distance: 2
```

## Development

### Requirements

- Java 8 or higher
- Maven
- Bukkit/Spigot API 1.20.4 or compatible version

### Building from Source

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/HelloClaydou.git
   ```

2. Navigate to the project directory:
   ```
   cd HelloClaydou
   ```

3. Build with Maven:
   ```
   mvn clean package
   ```

4. The compiled JAR file will be in the `target` directory

## Contributing

Contributions are welcome! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## Security

For security-related information, please see [SECURITY.md](SECURITY.md).

## Dependencies
- Bukkit/Spigot API 1.20 or higher

## License

This project is licensed under the [MIT License](LICENSE).

## Changelog

See [CHANGELOG.md](CHANGELOG.md) for a list of changes in each version.

## Support
For issues, suggestions, or contributions, please contact the plugin author or open an issue on GitHub.
