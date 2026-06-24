# Smoke Tests

The smoke launcher is inherited from Lifetime Stat Tracker and adapted for one
public Bigger Boats jar.

## Live Gates

`ciValidation` and `publishValidation` run both client and dedicated-server
launch smoke tasks for every supported profile/game-version pair.

Client pass marker:

```text
BIGGERBOATS_SMOKE_TEST_PASS
```

Server pass marker:

```text
BIGGERBOATS_SERVER_SMOKE_TEST_PASS
```

## Historical Matrix

`gradle/smoke-tests.json` starts with pending Bigger Boats records. The copied
Lifetime Stat Tracker evidence was removed from the release gate by setting
`smoke_test_required_minecraft_version_profiles=` until real Bigger Boats smoke
evidence exists.

After a profile passes real client and server smoke launches, update the matrix
with the run id, date, install set, and profile/game-version evidence before
making that historical record publish-blocking again.

## Local Evidence

- 2026-06-13: `smokeTestSelectedServers` passed for profile `1.21.11`, game
  version `1.21.11`, install set `bigger-boats-server-only`, with marker
  `BIGGERBOATS_SERVER_SMOKE_TEST_PASS` and `maxPassengers=4`.
- 2026-06-13: A prior `smokeTestSelected` attempt used incorrect property names
  and began launching the wider default smoke set before it was stopped. Use
  `biggerboats_smoke_profiles` and `biggerboats_smoke_game_versions`.
- 2026-06-24: `buildAllVersions` passed locally for `1.0.1` across supported
  profiles `1.20-1.20.4`, `1.20.5-1.21.10`, `1.21.11`, `26.1-26.1.2`,
  `26.2`, and `26.3-snapshot-1`.
- 2026-06-24: `smokeTestSelectedServers` passed locally for profiles `26.2`
  and `26.3-snapshot-1`, game versions `26.2` and `26.3-snapshot-1`, install
  set `bigger-boats-server-only`, with marker
  `BIGGERBOATS_SERVER_SMOKE_TEST_PASS` and `maxPassengers=4`.
- 2026-06-24: `smokeTestSelectedClients` passed locally for profiles `26.2`
  and `26.3-snapshot-1`, game versions `26.2` and `26.3-snapshot-1`, install
  set `bigger-boats-client-only`, with marker `BIGGERBOATS_SMOKE_TEST_PASS`
  and `maxPassengers=4`.

## Publish Evidence

- 2026-06-24: GitHub Actions run `28088910843` attempt 2 completed the real
  `modrinth publish` workflow with `dry_run=false`, `version_type=release`, and
  `requested_status=listed` from commit
  `fe4617029325685b16b2d83ca85fd6100a52a9ed`.
- The `1.0.1` publish gate built all supported profile jars, ran client and
  dedicated-server smoke for every supported profile/game-version pair, printed
  `BIGGERBOATS_SMOKE_TEST_PASS` and `BIGGERBOATS_SERVER_SMOKE_TEST_PASS`, and
  uploaded the release jars to Modrinth.
- The first attempt of run `28088910843` failed before upload because the
  `1.20.2` client smoke setup hit a transient Loom/Minecraft download error;
  rerunning the failed job succeeded without code changes.
- GitHub Actions run `28088906432` completed the normal `build` workflow for the
  `1.0.1` publish commit.
- Published `1.0.1` Modrinth version ids:
  - `1.0.1+mc1.20-1.20.4`: `aTLCkiqE`
  - `1.0.1+mc1.20.5-1.21.10`: `C13qBFnM`
  - `1.0.1+mc1.21.11`: `638gxoL5`
  - `1.0.1+mc26.1-26.1.2`: `ViIEU5Hi`
  - `1.0.1+mc26.2`: `7jcZxjqL`
  - `1.0.1+mc26.3-snapshot-1`: `gNaGk4dn`
- 2026-06-13: GitHub Actions run `27429801346` completed the real
  `modrinth publish` workflow with `dry_run=false`, `version_type=release`, and
  `requested_status=listed`.
- The publish gate built all supported profile jars, ran client and
  dedicated-server smoke for every supported profile/game-version pair, printed
  `BIGGERBOATS_SMOKE_TEST_PASS` and `BIGGERBOATS_SERVER_SMOKE_TEST_PASS`, and
  uploaded the release jars to Modrinth.
- GitHub Actions run `27429795249` was rerun after a transient Fabric Loom
  download failure and completed the normal `build` workflow successfully.

## Commands

```powershell
.\gradlew.bat smokeTestSelectedClients "-Pbiggerboats_smoke_profiles=1.21.11" "-Pbiggerboats_smoke_game_versions=1.21.11"
.\gradlew.bat smokeTestSelectedServers "-Pbiggerboats_smoke_profiles=1.21.11" "-Pbiggerboats_smoke_game_versions=1.21.11"
.\gradlew.bat smokeTestSelected "-Pbiggerboats_smoke_profiles=1.21.11" "-Pbiggerboats_smoke_game_versions=1.21.11"
.\gradlew.bat ciValidation --no-daemon --console=plain
```

Accepted install sets:

- `bigger-boats-client-only`
- `bigger-boats-server-only`

For Linux/headless CI:

```bash
./gradlew ciValidation -Pbiggerboats_smoke_xvfb=true --no-daemon --console=plain
```
