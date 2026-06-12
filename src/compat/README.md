# Compatibility Sources

Compatibility-specific source lives under:

```text
src/compat/<compat_group>/main/java/
src/compat/<compat_group>/client/java/
```

Bigger Boats currently uses overlays for Minecraft's boat class/package splits:

- `1.20-1.20.4`
- `1.20.5-1.21.10`
- `1.21.11`
- `26.x`

Shared behavior belongs in `src/main/java` and `src/client/java`.
