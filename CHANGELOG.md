# Changelog

All notable changes to the HelloClaydou plugin will be documented in this file.

## [1.5] - 2025-07-16

### Added
- Multi-language support with translations for Portuguese, English, Spanish, Russian, and Hebrew
- Configuration file (config.yml) for customizing plugin settings
- Language files in the lang/ directory
- Helper methods for metadata handling
- Comprehensive documentation for all classes and methods
- Detailed README.md with installation and usage instructions
- Permission definition in plugin.yml for the "claydou" command
- Permission check in the `onCommand()` method
- Exception handling with try-catch blocks in the `onCommand()` method
- Metadata cleanup in the `onDisable()` method to prevent memory leaks
- `EntityDeathEvent` handler to clean up metadata when entities are removed

### Changed
- Improved error handling in the onCommand method
- Optimized entity targeting logic
- Enhanced player interaction flow
- Better code organization with helper methods
- Updated plugin.yml with proper command registration

### Fixed
- NullPointerException during plugin initialization
- Potential NullPointerExceptions in metadata handling by adding proper checks with `entity.hasMetadata()`
- Entity targeting issues when pigs are in conversation
- Conversation state not properly resetting
- Command registration in plugin.yml
- Missing command definition in plugin.yml
- Incomplete command sender handling in the `onCommand()` method
- Invalid XML tag in pom.xml (`<n>HelloClaydou</n>` replaced with `<name>HelloClaydou</name>`)

### Security
- Implemented proper permission checks for commands
- Improved exception handling to prevent unexpected behavior

### Performance
- Created static ZERO_VELOCITY Vector constant to avoid creating new Vector objects
- Reduced object creation in the faceEntityToPlayer method
- Improved code structure with early returns
- Better variable naming and organization
- Implemented metadata cleanup to prevent memory leaks
