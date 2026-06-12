# Modrinth Publishing

Publishing uses the guarded Gradle tasks and manual GitHub Actions workflow
copied from Lifetime Stat Tracker.

## Required Setup

`gradle.properties` records the Bigger Boats Modrinth project id:

```properties
modrinth_project_id=AqXkZezn
```

Do not replace this with a donor project id.

Fabric API dependency project id:

```properties
modrinth_fabric_api_project_id=P7dR8mSH
```

## Gradle Tasks

```powershell
.\gradlew.bat publishValidation
.\gradlew.bat prepareModrinthUploads
.\gradlew.bat publishModrinthDryRun
.\gradlew.bat publishModrinth -Pmodrinth_confirm_publish=true
```

`publishValidation` builds supported profiles and runs live client plus
dedicated-server smoke tests before upload planning.

## GitHub Workflow

Use `.github/workflows/modrinth-publish.yml`.

Inputs:

- `dry_run`: keep enabled for validation-only runs
- `version_type`: `release`, `beta`, or `alpha`
- `requested_status`: `listed`, `unlisted`, or `draft`

Real publishes require the repository secret `MODRINTH_TOKEN`.

## Release Notes

Per-version Modrinth changelogs live in:

```text
gradle/release-notes/<mod_version>.md
```

For the initial release, keep
`gradle/release-notes/0.1.0.md` focused on user-visible behavior and install
notes.
