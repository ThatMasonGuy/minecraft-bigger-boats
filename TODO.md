# TODO

## Current Checkpoint

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
- The supplied `bigger-boats.jpg` is packaged as the Fabric mod icon.

## Research Conclusions

- Server-only support can make four riders function for unmodded clients by
  changing vanilla boat capacity on the server.
- Server-only support cannot make unmodded clients render the stretched boat or
  polished four-seat layout. Those are client-rendering concerns.
- Optional client install should improve visuals only; it must not be required
  for joining a Bigger Boats server.

## Next Verification

1. Run focused client smoke for `1.21.11`.
2. Run full `ciValidation` in GitHub Actions.
3. Backfill `gradle/smoke-tests.json` only with real Bigger Boats smoke evidence.

## Release Prep

- Modrinth project id `AqXkZezn` is recorded in `gradle.properties`.
- The live Modrinth page copy, license, and icon were updated on 2026-06-13.
- Modrinth accepted the `client_side=optional` and `server_side=required`
  PATCH requests, but the draft project currently reads back as
  `project_type=project`, `client_side=unknown`, and `server_side=unknown`.
  Recheck those fields after the project type/status is corrected in Modrinth.
- Keep release notes in `gradle/release-notes/0.1.0.md`.
- Keep public project-page copy in `gradle/modrinth-project-pages.md`.
- After a successful real publish, tag the exact publish workflow commit.
