# HelloClaydou

A Minecraft Bukkit/Spigot plugin that adds interactive pigs named "Claydou" to your server. These special pigs respond to player interactions with unique dialogue sequences.

## Features

- **Interactive Pigs**: Right-click on a pig to initiate a conversation with Claydou
- **Dialogue System**: Pigs remember conversation state and respond differently based on previous interactions
- **Simple Command**: Use `/claydou` to spawn a new Claydou pig
- **Permission System**: Control who can spawn Claydou pigs with permissions

## Installation

1. Download the latest release JAR file from the [Releases](https://github.com/yourusername/HelloClaydou/releases) page
2. Place the JAR file in your server's `plugins` folder
3. Restart your server or use a plugin manager to load the plugin
4. Verify installation by checking the server console for the message "claydouCommunicationPlugin habilitado"

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

1. **Initial Greeting**: "Olá Claydou"
2. **Friendly Response**: "Olá que bom ver você!!"
3. **Repeat Greeting**: "Olá Claydou"
4. **Farewell**: "Oi, já falei contigo, tenha um bom dia!"

### Metadata

The plugin uses Bukkit's metadata system to store conversation state:

- `claydou_interaction`: Stores the current conversation state (0-3)
- `has_seen_player`: Tracks whether the pig has already given its farewell message

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

## License

This project is licensed under the [MIT License](LICENSE).

## Changelog

See [CHANGELOG.md](CHANGELOG.md) for a list of changes in each version.