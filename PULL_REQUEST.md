# HelloClaydou Plugin Enhancements

## Description
This pull request introduces several significant improvements to the HelloClaydou plugin, enhancing its functionality, reliability, and maintainability.

The main changes include:
- Added internationalization support with translations for 5 languages
- Fixed several bugs and potential NullPointerExceptions
- Improved code organization and documentation
- Added configuration options for customization
- Enhanced error handling and optimized performance

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

### Code Improvements
- Added comprehensive documentation for all classes and methods
- Created helper methods for common operations
- Improved code organization and readability
- Enhanced error handling with proper logging

### Configuration
- Added config.yml with options for language selection and other settings
- Made spawn distance configurable
- Added debug mode option

## How Has This Been Tested?
The changes have been tested in a development environment to ensure:
- The plugin loads correctly without errors
- Internationalization works as expected with all supported languages
- Entity interactions function properly
- Command execution works correctly
- Configuration options are applied properly

## Screenshots (if appropriate)
N/A

## Types of Changes
- [x] Bug fix (non-breaking change which fixes an issue)
- [x] New feature (non-breaking change which adds functionality)
- [x] Documentation update
- [x] Performance enhancement

## Checklist
- [x] My code follows the code style of this project
- [x] I have added/updated documentation as needed
- [x] I have updated the CHANGELOG.md file
- [x] All new and existing tests passed