# HelloClaydou Release Process

This document outlines the steps followed to release version 1.5 of the HelloClaydou plugin.

## Release Date
July 27, 2025

## Release Process Steps

1. **Preparation**
   - Reviewed the CHANGELOG.md to confirm version 1.5 changes
   - Checked the pom.xml file for configuration issues

2. **Configuration Updates**
   - Fixed the XML tag in pom.xml from `<n>HelloClaydou</n>` to `<name>HelloClaydou</name>`
   - Removed the "-SNAPSHOT" suffix from the version in pom.xml, changing it from "1.5-SNAPSHOT" to "1.5"

3. **Version Control**
   - Committed the changes to pom.xml with a descriptive message
   - Created an annotated git tag "v1.5" to mark the release point

## Notes

- The release artifacts were not rebuilt due to Maven not being available in the environment
- The existing artifacts in the target directory still have the "-SNAPSHOT" suffix
- For a complete release, the artifacts should be rebuilt with the correct version number when Maven becomes available

## Future Releases

For future releases, follow these steps:

1. Update the CHANGELOG.md with the new version and changes
2. Update the version in pom.xml (remove "-SNAPSHOT" suffix)
3. Build the release artifacts with `mvn clean package`
4. Commit the changes with a descriptive message
5. Create an annotated git tag with `git tag -a vX.Y -m "Version X.Y release"`
6. Push the changes and tag to the remote repository with `git push && git push --tags`
7. Create a new release on GitHub or your preferred platform, attaching the built artifacts

## Verification

- Confirm that the git tag exists with `git tag`
- Verify that the pom.xml has the correct version and configuration
- Ensure that the release artifacts are properly built and named