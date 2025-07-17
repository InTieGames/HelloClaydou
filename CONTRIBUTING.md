# Contributing to HelloClaydou

Thank you for your interest in contributing to the HelloClaydou plugin! This document provides guidelines and instructions for contributing to this project.

## Code of Conduct

By participating in this project, you agree to maintain a respectful and inclusive environment for everyone.

## How to Contribute

### Reporting Bugs

If you find a bug in the plugin, please create an issue with the following information:

1. A clear, descriptive title
2. Steps to reproduce the bug
3. Expected behavior
4. Actual behavior
5. Server information (Bukkit/Spigot version, Java version, etc.)
6. Plugin version
7. Any relevant logs or error messages

### Suggesting Enhancements

If you have an idea for an enhancement, please create an issue with the following information:

1. A clear, descriptive title
2. A detailed description of the enhancement
3. Why this enhancement would be useful
4. Any implementation ideas you may have

### Pull Requests

We welcome pull requests! To submit a pull request:

1. Fork the repository
2. Create a new branch for your changes
3. Make your changes
4. Test your changes thoroughly
5. Submit a pull request with a clear description of the changes

#### Pull Request Guidelines

- Follow the existing code style
- Include comments in your code where necessary
- Update documentation if needed
- Add tests for new features
- Ensure all tests pass
- Keep pull requests focused on a single topic

## Development Setup

### Prerequisites

- Java 8 or higher
- Maven
- Git
- IDE of your choice (IntelliJ IDEA, Eclipse, etc.)

### Setting Up the Development Environment

1. Fork the repository on GitHub
2. Clone your fork locally:
   ```
   git clone https://github.com/yourusername/HelloClaydou.git
   ```
3. Set up the upstream remote:
   ```
   git remote add upstream https://github.com/originalowner/HelloClaydou.git
   ```
4. Import the project into your IDE
5. Build the project with Maven:
   ```
   mvn clean package
   ```

## Coding Standards

### Java Code Style

- Use 4 spaces for indentation (not tabs)
- Follow Java naming conventions:
  - `camelCase` for variables and methods
  - `PascalCase` for classes
  - `UPPER_SNAKE_CASE` for constants
- Keep methods short and focused
- Add JavaDoc comments for public methods and classes

### Commit Messages

- Use the present tense ("Add feature" not "Added feature")
- Use the imperative mood ("Move cursor to..." not "Moves cursor to...")
- Limit the first line to 72 characters or less
- Reference issues and pull requests after the first line

## Testing

- Test your changes on a local Bukkit/Spigot server
- Ensure compatibility with the supported Minecraft versions
- Check for any performance issues
- Verify that permissions work correctly

## Documentation

- Update the README.md if necessary
- Document new features in the appropriate files
- Update JavaDoc comments for modified code
- Consider adding examples for complex features

## Release Process

The maintainers will handle the release process, which typically includes:

1. Updating the version number
2. Building the release JAR
3. Creating a new release on GitHub
4. Updating the documentation

## Questions?

If you have any questions about contributing, please create an issue with your question.

Thank you for contributing to HelloClaydou!