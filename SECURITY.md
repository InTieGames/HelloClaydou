# Security Policy

## Supported Versions

Currently, only the latest version of the HelloClaydou plugin is supported with security updates.

| Version | Supported          |
| ------- | ------------------ |
| 1.5.x   | :white_check_mark: |
| < 1.5   | :x:                |

## Security Improvements in Version 1.5

### Permission System
- Added proper permission checks for the "claydou" command
- Implemented permission definition in plugin.yml
- Default permission is set to "op" (server operators only)

### Exception Handling
- Added try-catch blocks in the `onCommand()` method to handle exceptions gracefully
- Improved error messages for better troubleshooting

### Metadata Management
- Implemented proper cleanup of metadata when entities are removed
- Added cleanup in the `onDisable()` method to prevent memory leaks
- Added `EntityDeathEvent` handler to clean up metadata when entities die

## Reporting a Vulnerability

If you discover a security vulnerability within the HelloClaydou plugin, please follow these steps:

1. **Do not** disclose the vulnerability publicly
2. Send a detailed report to the project maintainers
3. Include steps to reproduce the vulnerability
4. If possible, include a suggested fix

The maintainers will acknowledge receipt of your vulnerability report and will work on addressing the issue as quickly as possible.

## Security Best Practices for Server Administrators

When using the HelloClaydou plugin, consider the following security best practices:

1. Always keep the plugin updated to the latest version
2. Configure permissions carefully to restrict access to the "claydou" command
3. Monitor server logs for any unusual activity related to the plugin
4. Consider using a permissions management plugin for more granular control

## Configuration Security

The plugin does not currently store sensitive information. However, as a general practice:

1. Ensure your server's configuration files have appropriate file permissions
2. Do not expose your server's configuration directory to public access
3. Regularly backup your server's configuration files