# Minecraft Version Profiles

This directory contains the multi-version profile metadata used by Gradle. The
profile model is copied from Lifetime Stat Tracker and adapted for Bigger
Boats.

## Goal

Build profiles keep one source tree while letting Gradle swap Minecraft,
Fabric Loader, Fabric API, Loom, Java, metadata, and optional compatibility
source overlays.

Profiles are release compatibility groups. A profile does not have to be one
exact Minecraft patch version; it can represent one compiled jar that is tested
and published for several compatible Minecraft versions.

Exact smoke runtime profiles, such as `1.20.1.properties`, are smoke-launch
inputs only. Do not add them to `supported_minecraft_version_profiles` unless
we intentionally decide to publish more jars.

## Profile Lists

`gradle.properties` currently has:

```properties
minecraft_version_profile=1.21.11
supported_minecraft_version_profiles=1.20-1.20.4,1.20.5-1.21.10,1.21.11,26.1-26.2-pre-3
candidate_minecraft_version_profiles=
smoke_test_required_minecraft_version_profiles=
```

The supported profile list controls build and publish planning. The historical
smoke-required list is intentionally empty until real Bigger Boats smoke runs
are recorded. Live `ciValidation` and `publishValidation` still launch client
and dedicated-server smoke tasks.

## Profile Fields

```properties
profile_id=1.20.5-1.21.10
minecraft_version=<proven_compile_anchor>
minecraft_dependency=>=1.20.5 <=1.21.10
modrinth_game_versions=1.20.5,1.20.6,1.21,1.21.1,1.21.2,1.21.3,1.21.4,1.21.5,1.21.6,1.21.7,1.21.8,1.21.9,1.21.10
compat_group=1.20.5-1.21.10
loader_version=0.18.4
loom_version=1.14-SNAPSHOT
fabric_api_version=<matching Fabric API version>
java_version=21
unobfuscated_minecraft=false
```

- `profile_id` is the release output folder and Modrinth version suffix.
- `minecraft_version` is the compile anchor used by Loom and mappings.
- `minecraft_dependency` is the Fabric Loader dependency range written into
  `fabric.mod.json`.
- `modrinth_game_versions` is the exact set of game versions to publish for the
  jar after live smoke testing.
- `compat_group` selects any version-specific source overlay.
- `java_version` selects the Java compile release and generated Mixin
  compatibility level.
- `unobfuscated_minecraft=true` is expected only for the `26.x` lane.

## Commands

```powershell
.\gradlew.bat printVersionProfile
.\gradlew.bat listVersionProfiles
.\gradlew.bat build "-Pminecraft_version_profile=1.21.11"
.\gradlew.bat buildRelease
.\gradlew.bat buildAllVersions
.\gradlew.bat buildValidationVersions
.\gradlew.bat ciValidation
```

Focused smoke examples:

```powershell
.\gradlew.bat smokeTestSelectedClients "-Pbiggerboats_smoke_profiles=1.21.11" "-Pbiggerboats_smoke_game_versions=1.21.11"
.\gradlew.bat smokeTestSelectedServers "-Pbiggerboats_smoke_profiles=1.21.11" "-Pbiggerboats_smoke_game_versions=1.21.11"
```

## Compatibility Source Layout

```text
src/compat/<compat_group>/main/java/
src/compat/<compat_group>/main/resources/
src/compat/<compat_group>/client/java/
src/compat/<compat_group>/client/resources/
```

Keep shared behavior in `src/main/java` and `src/client/java`. Add compatibility
sources only for target-specific APIs that cannot compile across the intended
range.
