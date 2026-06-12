# Changelog

## 0.1.0 - Unreleased

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
- The Modrinth project currently reads back as `project_type=project`, which
  prevents the desired side-support flags from sticking while it remains in
  that hosted state.
