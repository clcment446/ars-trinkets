# Agent Instructions — Ars Nouveau

## Asking for Clarification and Validation

Use bash `read` prompts for interactive clarification or validation steps. Never silently assume — ask.

### Clarification template

```bash
read -rp "Which subsystem are you targeting? (api/client/common/setup): " SUBSYSTEM
echo "Targeting: $SUBSYSTEM"
```

### Validation template (y/n)

```bash
read -rp "Proceed with reindex of $SUBSYSTEM? [y/N]: " CONFIRM
case "$CONFIRM" in
  [yY]|[yY][eE][sS]) echo "Confirmed." ;;
  *) echo "Aborted."; exit 0 ;;
esac
```

### Multi-choice template

```bash
echo "Select action:"
echo "  1) Query graph"
echo "  2) Update graph (incremental)"
echo "  3) Full rebuild"
read -rp "Choice [1-3]: " CHOICE
```

Use these patterns before destructive operations (full rebuild, file deletion) and when the user's intent is ambiguous.

## General Rules

- **Read before edit**: Read the full file before making changes.
- **Grep over cat**: Use the Grep tool to locate symbols across the codebase; never cat an entire file to find a class.
- **No large dumps**: Do not print raw JSON from graphify output files into chat — summarize instead.

## Testing Exceptions

- For player-level bonus glyph slot changes, do not add automated tests unless the user explicitly requests them.

## Release Procedure

Releases are cut from the `1.21.x` branch. Do not tag from a dirty tree, and do not release without a green build.

1. **Pick the version** (`MAJOR.MINOR.PATCH`): new content or features bump the minor, fixes only bump the patch.
2. **Bump** `mod_version` in `gradle.properties` (the mod's `neoforge.mods.toml` and the jar name are templated from it).
3. **Write the notes in two places**:
   - `CHANGELOG.md`: a `# Ars Trinkets X.Y.Z` section at the top.
   - The in-game changelog: add a `Release("X.Y.Z", <line count>)` at the top of `Changelog.RELEASES`
     (`src/main/java/com/c446/ars_trinkets/changelog/Changelog.java`) and the matching
     `ars_trinkets.changelog.X_Y_Z.line<N>` keys plus `ars_trinkets.page.changelog_X_Y_Z` in
     `src/main/resources/assets/ars_trinkets/lang/en_us.json`. This feeds both the one-time login message and the
     "Ars Trinkets Changelog" section of the Ars Nouveau book. The version string must match `mod_version`
     exactly or the login message is skipped.
4. **Regenerate data** if recipes, models or tags changed (`bash gradlew runData`), and commit `src/generated`.
5. **Build and test**: `bash gradlew build` must pass. The jar is `build/libs/ars_trinkets-1.21.1-X.Y.Z.jar`.
   Smoke-test it with `bash gradlew runPrismClient` when the change touches items, recipes or the config.
6. **Commit** everything with a message like `chore: release X.Y.Z`, then push `1.21.x`.
7. **Tag and publish**: `git tag -a X.Y.Z -m "Ars Trinkets X.Y.Z"` and `git push origin X.Y.Z` (tags have no `v` prefix, like `3.0.0`). Create the GitHub
   release from that tag, paste the `CHANGELOG.md` section as the notes, and attach the jar.
8. **Afterwards** leave `CHANGELOG.md` ready for the next version (add the new top section as work lands).

Never publish the jar to Modrinth/CurseForge or create a GitHub release without the user asking for it.
