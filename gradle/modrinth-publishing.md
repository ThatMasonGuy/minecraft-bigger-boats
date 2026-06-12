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

## Publish History

- 2026-06-13: `1.0.0` was published by GitHub Actions run `27429801346` from
  commit `61113dbe297c96f7b132dfd1ce77d010dc519b7f` with `dry_run=false`,
  `version_type=release`, and `requested_status=listed`.
- Modrinth version ids:
  - `1.0.0+mc1.20-1.20.4`: `7VszMwsz`
  - `1.0.0+mc1.20.5-1.21.10`: `Wn7e3XTl`
  - `1.0.0+mc1.21.11`: `sb9snz5O`
  - `1.0.0+mc26.1-26.2-pre-3`: `7SOT3TOv`

## Release Notes

Per-version Modrinth changelogs live in:

```text
gradle/release-notes/<mod_version>.md
```

For the initial release, keep
`gradle/release-notes/1.0.0.md` focused on user-visible behavior and install
notes.
