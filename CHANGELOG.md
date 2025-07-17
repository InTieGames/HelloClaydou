# Changelog

All notable changes to the HelloClaydou plugin will be documented in this file.

## [1.5] - 2025-07-16

### Fixed
- Fixed missing command definition in plugin.yml
- Fixed potential NullPointerExceptions in metadata handling by adding proper checks with `entity.hasMetadata()`
- Fixed incomplete command sender handling in the `onCommand()` method
- Fixed invalid XML tag in pom.xml (`<n>HelloClaydou</n>` replaced with `<name>HelloClaydou</name>`)

### Added
- Added permission definition in plugin.yml for the "claydou" command
- Added permission check in the `onCommand()` method
- Added exception handling with try-catch blocks in the `onCommand()` method
- Added metadata cleanup in the `onDisable()` method to prevent memory leaks
- Added `EntityDeathEvent` handler to clean up metadata when entities are removed

### Security
- Implemented proper permission checks for commands
- Improved exception handling to prevent unexpected behavior

### Performance
- Implemented metadata cleanup to prevent memory leaks