# Compatibility Release Playbook

This repo follows the compatibility-group release model copied from Lifetime
Stat Tracker.

## Profile Rule

Each supported profile should produce one jar from one compile anchor, list the
exact Minecraft versions that jar is intended to support, and pass live smoke
testing before publishing.

## Bigger Boats Drift Points

- `1.20.x`: boat behavior lives directly on `Boat`.
- `1.20.5-1.21.10`: boat behavior lives on `AbstractBoat`.
- `1.21.11+`: boat behavior moved under `vehicle.boat.AbstractBoat`.
- Client render hooks differ between direct entity rendering in `1.20.x` and
  render-state submission in newer versions.

## Promotion Rule

Do not call a profile publish-ready until:

- `buildRelease` passes for the profile
- packaged metadata verifies the Bigger Boats id, icon, licenses, and mixins
- client smoke prints `BIGGERBOATS_SMOKE_TEST_PASS`
- dedicated-server smoke prints `BIGGERBOATS_SERVER_SMOKE_TEST_PASS`
- release notes exist for the active `mod_version`
