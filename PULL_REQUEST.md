# HelloClaydou Plugin Comprehensive Improvements

## Description
This pull request introduces significant improvements to the HelloClaydou plugin, enhancing its functionality, reliability, maintainability, performance, and security.

The main changes include:
- Added internationalization support with translations for 5 languages
- Fixed several bugs and potential NullPointerExceptions
- Improved code organization and documentation
- Added configuration options for customization
- Enhanced error handling and optimized performance
- Strengthened security with permission checks and player limits
- Fixed invalid XML in pom.xml

## Motivation and Context
The plugin previously had several issues that could cause errors during runtime, and it lacked proper documentation and configuration options. These changes make the plugin more robust, user-friendly, and maintainable.

The internationalization feature allows the plugin to be used by a wider audience, while the improved error handling and bug fixes ensure a smoother experience for all users.

## Changes in Detail

### Internationalization
- Added support for Portuguese, English, Spanish, Russian, and Hebrew languages
- Created a LanguageManager class to handle translations
- Implemented language files in YAML format
- Added configuration option to select the default language

### Bug Fixes
- Fixed NullPointerException during plugin initialization
- Improved entity targeting logic to prevent issues during conversations
- Fixed conversation state handling to properly reset after completion
- Added proper command registration in plugin.yml
- Fixed invalid XML in pom.xml (changed `<n>HelloClaydou</n>` to `<name>HelloClaydou</name>`)
- Added null checks in `getInteractionState()`, `hasSeenPlayer()`, and other methods
- Added validation for entity, player, and location objects throughout the code

### Code Improvements
- Added comprehensive documentation for all classes and methods
- Created helper methods for common operations
- Improved code organization and readability
- Enhanced error handling with proper logging
- Added specific exception handling for IllegalArgumentException
- Added more descriptive error messages for better debugging

### Configuration
- Added config.yml with options for language selection and other settings
- Made spawn distance configurable
- Added debug mode option

### Security Enhancements
- Implemented proper permission checks for commands
- Added a maximum limit of pigs that a player can spawn (5)
- Added a method to count existing pigs per player

### Performance Optimizations
- Created static ZERO_VELOCITY Vector constant to avoid creating new Vector objects
- Reduced object creation in the faceEntityToPlayer method
- Improved code structure with early returns
- Better variable naming and organization
- Optimized entity iteration in onDisable()
- Combined metadata checks to reduce redundancy
- Implemented metadata cleanup to prevent memory leaks

## Benefits
- **Improved Stability**: The addition of null checks and proper validation prevents crashes from NullPointerExceptions
- **Better Performance**: Optimized metadata handling and entity iteration reduces server load
- **Enhanced Security**: More specific exception handling and player limits prevent abuse
- **Consistent Behavior**: Proper initialization of metadata ensures all pigs behave as expected
- **Wider Accessibility**: Internationalization support makes the plugin accessible to non-English speakers

## How Has This Been Tested?
The changes have been tested in a development environment to ensure:
- The plugin loads correctly without errors
- Internationalization works as expected with all supported languages
- Entity interactions function properly
- Command execution works correctly
- Configuration options are applied properly
- Player limits and permissions work as intended

## Types of Changes
- [x] Bug fix (non-breaking change which fixes an issue)
- [x] New feature (non-breaking change which adds functionality)
- [x] Documentation update
- [x] Performance enhancement
- [x] Security enhancement

## Checklist
- [x] My code follows the code style of this project
- [x] I have added/updated documentation as needed
- [x] I have updated the CHANGELOG.md file
- [x] All new and existing tests passed
