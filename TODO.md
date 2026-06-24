# TODO

## Current Checkpoint

- `1.0.1` release prep is in progress for stable Minecraft `26.2` and
  `26.3-snapshot-1` support.
- The old `26.1-26.2-pre-3` prerelease-era publish lane has been replaced for
  active builds with `26.1-26.1.2`, `26.2`, and `26.3-snapshot-1`, all mapped
  to the existing `26.x` source compatibility overlay.
- The 26.x lane now targets Fabric Loader `0.19.3`, Loom `1.17-SNAPSHOT`, and
  Gradle `9.5.1`.
- Local `buildAllVersions` passed for `1.0.1` across all supported profiles.
- Local targeted client and dedicated-server smoke passed for `26.2` and
  `26.3-snapshot-1`, with both smoke markers and `maxPassengers=4`.
- `v1.0.0` was published from commit
  `61113dbe297c96f7b132dfd1ce77d010dc519b7f`.
- GitHub Release `v1.0.0` is live with one jar per compatibility profile.
- GitHub Actions run `27429801346` completed the real Modrinth publish gate.
- GitHub Actions run `27429795249` completed the normal build gate after a
  transient download failure was rerun.
- Bigger Boats has been scaffolded from the Lifetime Stat Tracker build,
  workflow, smoke, and publishing pipeline.
- `AGENTS.md` is copied word-for-word from the donor repo.
- The repo started empty and was not a git repository at scaffold time.
- The first implementation is code-complete enough to compile-test:
  - common Fabric initializer
  - client initializer
  - server and client smoke hooks
  - common boat capacity mixin
  - optional client renderer stretch mixin
  - version-profile-specific boat package overlays
- Default-profile `build`, default-profile `buildRelease`, and
  `buildAllVersions` have passed locally.
- Focused 1.21.11 dedicated-server smoke passed locally with
  `BIGGERBOATS_SERVER_SMOKE_TEST_PASS` and `maxPassengers=4`.
- The real publish workflow passed client and dedicated-server smoke for all
  supported profiles/game versions.
- The supplied `bigger-boats.jpg` is packaged as the Fabric mod icon.

## Research Conclusions

- Server-only support can make four riders function for unmodded clients by
  changing vanilla boat capacity on the server.
- Server-only support cannot make unmodded clients render the stretched boat or
  polished four-seat layout. Those are client-rendering concerns.
- Optional client install should improve visuals only; it must not be required
  for joining a Bigger Boats server.

## Next Verification

1. Run the guarded Modrinth publish workflow for `1.0.1` after local checks
   pass.
2. Backfill `TODO.md`, `CHANGELOG.md`, `gradle/smoke-tests.md`, and publish
   history with the workflow run id, Modrinth version ids, and exact publish
   commit.

## Release Prep

- Modrinth project id `AqXkZezn` is recorded in `gradle.properties`.
- The live Modrinth page copy, license, and icon were updated on 2026-06-13.
- Modrinth reads back `project_type=mod`, `client_side=optional`, and
  `server_side=required`.
- Published Modrinth versions:
  - `1.0.0+mc1.20-1.20.4`: `7VszMwsz`
  - `1.0.0+mc1.20.5-1.21.10`: `Wn7e3XTl`
  - `1.0.0+mc1.21.11`: `sb9snz5O`
  - `1.0.0+mc26.1-26.2-pre-3`: `7SOT3TOv`
- Keep active release notes in `gradle/release-notes/1.0.1.md`.
- Keep the previous `1.0.0` release notes in `gradle/release-notes/1.0.0.md`.
- Keep public project-page copy in `gradle/modrinth-project-pages.md`.
- After a successful real publish, tag the exact publish workflow commit.
