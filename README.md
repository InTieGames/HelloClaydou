# HelloClaydou - Interactive Pig Plugin

## Overview
HelloClaydou is a Bukkit/Spigot plugin that adds interactive pig entities to your Minecraft server. These special pigs, called "Claydou", can have conversations with players through a state-based interaction system.

## Features
- Spawn interactive Claydou pigs using the `/claydou` command
- Engage in simple conversations with the pigs by right-clicking on them
- Pigs will face the player during conversations
- Pigs won't target other entities while in a conversation
- Conversation progresses through multiple states with different messages
- Multi-language support (Portuguese, English, Spanish, Russian, and Hebrew)

## Installation
1. Download the HelloClaydou.jar file
2. Place the JAR file in your server's `plugins` folder
3. Restart your server or use a plugin manager to load the plugin
4. The plugin will generate configuration and language files on first run
5. Verify the plugin is loaded by checking the server console for the plugin enabled message

## Usage

### Commands
- `/claydou` - Spawns a new Claydou pig near the player
  - Permission: None (available to all players)
  - Can only be used by players, not from the console

### Interacting with Claydou
1. Spawn a Claydou pig using the `/claydou` command
2. Right-click on the pig to start a conversation
3. Continue right-clicking to progress through the conversation
4. The conversation will reset after completing all states

## Conversation Flow
The Claydou pigs have a simple conversation system with 4 states:
1. **State 0**: Player says "Olá Claydou" (white text)
2. **State 1**: Pig responds "Olá que bom ver você!!" (light purple text)
3. **State 2**: Player says "Olá Claydou" again (white text)
4. **State 3**: Pig responds with a farewell message and the conversation resets

## Technical Details
- The plugin uses entity metadata to store the conversation state
- Pigs are prevented from targeting other entities while in a conversation
- The plugin handles entity targeting events to control pig behavior
- Conversation state is preserved as long as the pig entity exists

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

## Dependencies
- Bukkit/Spigot API 1.20 or higher

## License
This plugin is provided as-is with no explicit license. All rights reserved.

## Support
For issues, suggestions, or contributions, please contact the plugin author.
