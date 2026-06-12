# Compatibility

Scope: Bigger Boats source compatibility from Minecraft `1.20` onward using the
same version-profile model as Lifetime Stat Tracker.

## Supported Release Profiles

| Profile | Compile anchor | Runtime versions | Compat group |
| --- | --- | --- | --- |
| `1.20-1.20.4` | `1.20` | `1.20` through `1.20.4` | `1.20-1.20.4` |
| `1.20.5-1.21.10` | `1.21.10` | `1.20.5` through `1.21.10` | `1.20.5-1.21.10` |
| `1.21.11` | `1.21.11` | `1.21.11` | `1.21.11` |
| `26.1-26.2-pre-3` | `26.2-pre-3` | `26.1` through `26.2-pre-3` | `26.x` |

## Drift Surfaces

- `1.20.x` uses `net.minecraft.world.entity.vehicle.Boat` directly.
- `1.20.5-1.21.10` uses
  `net.minecraft.world.entity.vehicle.AbstractBoat`.
- `1.21.11+` uses
  `net.minecraft.world.entity.vehicle.boat.AbstractBoat`.
- Newer clients use render states through
  `net.minecraft.client.renderer.entity.AbstractBoatRenderer`; `1.20.x` renders
  directly through `BoatRenderer#render`.
- The experimental `26.x` lane changes some renderer package names and uses the
  non-remap build lane inherited from the donor pipeline.

## Server-Only Contract

The server-side mod must:

- allow up to four passengers on vanilla boats
- avoid adding new entities, items, recipes, or blocks
- keep the first rider as the controlling passenger
- run without loading client-only classes

Unmodded clients are expected to receive the server passenger list, but their
local renderer remains vanilla. Visual overlap is acceptable in server-only
mode.

## Optional Client Contract

The client-side mod may:

- preserve exact vanilla positions for one or two passengers
- spread three or four passengers along the boat length
- scale the rendered model length for three or four passengers
- shrink the rendered model when passengers leave

The client-side mod must not be required for server join compatibility.
