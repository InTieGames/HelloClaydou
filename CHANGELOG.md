# Changelog

## [1.5] - 2023-07-15

### Added
- Multi-language support with translations for Portuguese, English, Spanish, Russian, and Hebrew
- Configuration file (config.yml) for customizing plugin settings
- Language files in the lang/ directory
- Helper methods for metadata handling
- Comprehensive documentation for all classes and methods
- Detailed README.md with installation and usage instructions

### Changed
- Improved error handling in the onCommand method
- Optimized entity targeting logic
- Enhanced player interaction flow
- Better code organization with helper methods
- Updated plugin.yml with proper command registration

### Fixed
- NullPointerException during plugin initialization
- Potential NullPointerExceptions in metadata handling
- Entity targeting issues when pigs are in conversation
- Conversation state not properly resetting
- Command registration in plugin.yml

### Optimized
- Created static ZERO_VELOCITY Vector constant to avoid creating new Vector objects
- Reduced object creation in the faceEntityToPlayer method
- Improved code structure with early returns
- Better variable naming and organization