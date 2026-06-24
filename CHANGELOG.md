# Changelog

## 1.0.1 - 2026-06-24

- Added stable Minecraft `26.2` and `26.3-snapshot-1` support profiles while
  keeping the existing `26.x` source compatibility overlay.
- Split the old prerelease-era `26.1-26.2-pre-3` release lane into
  `26.1-26.1.2`, `26.2`, and `26.3-snapshot-1` publish profiles to match
  Fabric API's current per-minor Minecraft dependency predicates.
- Updated the 26.x build lane to Fabric Loader `0.19.3`, Loom `1.17-SNAPSHOT`,
  Fabric API `0.153.0+26.2` for stable `26.2`, and Fabric API `0.153.1+26.3`
  for `26.3-snapshot-1`.
- Updated the Gradle wrapper to `9.5.1` for the current Fabric 26.2 toolchain.
- Verified `1.0.1` locally with `buildAllVersions` plus targeted client and
  dedicated-server smoke for `26.2` and `26.3-snapshot-1`.
- Published `1.0.1` through guarded GitHub Actions run `28088910843` attempt 2
  after the full client and dedicated-server smoke matrix passed.

## 1.0.0 - 2026-06-13

- Scaffolded Bigger Boats from the Lifetime Stat Tracker multi-version Fabric
  pipeline, including Gradle profiles, GitHub Actions build/smoke workflows,
  smoke launcher module, release-jar metadata checks, and guarded Modrinth
  publishing tasks.
- Kept `AGENTS.md` copied word-for-word from the donor repo as requested.
- Added server-authoritative vanilla boat capacity mixins for four passengers.
- Added optional client-side seat positioning and dynamic boat model length
  scaling for three- and four-passenger boats.
- Added client and dedicated-server smoke-test entrypoints with
  `BIGGERBOATS_SMOKE_TEST_PASS` and `BIGGERBOATS_SERVER_SMOKE_TEST_PASS`
  markers.
- Replaced the copied Lifetime Stat Tracker Modrinth project id with Bigger
  Boats project id `AqXkZezn`.
- Replaced the generated placeholder icon with the supplied `bigger-boats.jpg`
  as the packaged mod icon.
- Updated the live Modrinth project page copy, license, and icon.
- Recorded local 1.21.11 dedicated-server smoke evidence proving the server
  sees `maxPassengers=4`.
- Published release jars for all supported compatibility profiles after the
  GitHub publish gate passed client and dedicated-server smoke across the full
  supported matrix.

## Research Notes

- Server-side capacity is viable because vanilla passenger lists are generic
  entity state. The server can accept and sync more passengers than vanilla
  boats normally allow.
- A fully unmodded client cannot be forced by the server to render a longer boat
  model or use custom seat offsets. The optional client install handles that
  visual layer.
- Boat implementation classes split across the supported range:
  `net.minecraft.world.entity.vehicle.Boat` in `1.20.x`,
  `net.minecraft.world.entity.vehicle.AbstractBoat` through `1.21.10`, and
  `net.minecraft.world.entity.vehicle.boat.AbstractBoat` in `1.21.11+`.
- The Modrinth project now reads back as `project_type=mod`,
  `client_side=optional`, and `server_side=required`.
