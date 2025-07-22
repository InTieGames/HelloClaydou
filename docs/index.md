# HelloClaydou Documentation

Welcome to the HelloClaydou plugin documentation. This documentation provides comprehensive information about the HelloClaydou plugin, its features, implementation details, and future development plans.

## Overview

HelloClaydou is a Minecraft Bukkit/Spigot plugin that adds interactive pigs named "Claydou" to your server. These special pigs respond to player interactions with unique dialogue sequences, creating an engaging experience for players.

## Documentation Sections

### General Documentation

- [README](../README.md) - General information about the plugin, features, installation, and usage
- [CHANGELOG](../CHANGELOG.md) - History of changes and updates to the plugin
- [SECURITY](../SECURITY.md) - Security considerations and vulnerability reporting
- [CONTRIBUTING](../CONTRIBUTING.md) - Guidelines for contributing to the project

### Technical Documentation

- [API Documentation](API.md) - Information about the plugin's API for developers
- [Implementation Details](Implementation.md) - Detailed explanation of how the plugin works
- [Future Development Plans](FuturePlans.md) - Planned features and improvements

### Configuration

- [Configuration Example](../config_example.yml) - Example configuration file with explanations

## Plugin Features

- **Interactive Pigs**: Right-click on a pig to initiate a conversation with Claydou
- **Dialogue System**: Pigs remember conversation state and respond differently based on previous interactions
- **Simple Command**: Use `/claydou` to spawn a new Claydou pig
- **Permission System**: Control who can spawn Claydou pigs with permissions

## Recent Improvements

The plugin has recently undergone several improvements:

1. **Bug Fixes**:
   - Fixed missing command definition in plugin.yml
   - Fixed potential NullPointerExceptions in metadata handling
   - Fixed incomplete command sender handling
   - Fixed invalid XML tag in pom.xml

2. **Security Enhancements**:
   - Implemented proper permission checks for commands
   - Improved exception handling

3. **Performance Optimizations**:
   - Implemented metadata cleanup to prevent memory leaks
   - Added EntityDeathEvent handler to clean up metadata when entities die

## Getting Started

To get started with the HelloClaydou plugin, please refer to the [README](../README.md) for installation instructions and basic usage.

## For Developers

If you're a developer interested in extending or modifying the HelloClaydou plugin, please check the following resources:

- [API Documentation](API.md) for information about the plugin's API
- [Implementation Details](Implementation.md) for understanding how the plugin works
- [CONTRIBUTING](../CONTRIBUTING.md) for guidelines on contributing to the project

## Future Plans

We have exciting plans for future versions of the HelloClaydou plugin. Check out our [Future Development Plans](FuturePlans.md) to see what's coming next.

## Support

If you encounter any issues or have questions about the HelloClaydou plugin, please create an issue on our GitHub repository.

## License

This project is licensed under the [MIT License](../LICENSE).

---

*Documentation last updated: July 16, 2025*