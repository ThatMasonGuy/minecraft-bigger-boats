# Bigger Boats

A Fabric mod for Minecraft `1.20` and newer that lets up to four players ride
in a normal vanilla boat.

The mod is primarily server-side. Install it on a Fabric server and unmodded
clients can still join and ride. Installing the same jar on the client improves
the visual experience by spacing the extra passengers and stretching the boat
model when a third or fourth passenger is aboard.

## Install Modes

### Server Only

- Required for gameplay on multiplayer servers.
- Raises the boat passenger limit from two to four.
- Does not add recipes, items, blocks, or custom boat entities.
- Vanilla clients can connect, but extra riders may overlap visually because the
  vanilla client still renders two-seat boat geometry.

### Optional Client

- Keeps one- and two-passenger boats visually vanilla.
- Adds cleaner seat spacing for three and four passengers.
- Stretches the rendered boat length for three and four passengers.
- Shrinks the rendered boat back to normal when passengers leave.

## Behavior

- Normal boats remain normal with one or two riders.
- A third rider can enter an existing boat when the server has the mod.
- A fourth rider can enter the same boat.
- The first passenger remains the controlling passenger, matching vanilla boat
  behavior.
- The implementation changes vanilla boat behavior in place; it does not create
  a separate bigger boat item.

## Compatibility Notes

The current version targets the same multi-version profile pipeline as the
donor Lifetime Stat Tracker repo:

- `1.20-1.20.4`
- `1.20.5-1.21.10`
- `1.21.11`
- `26.1-26.1.2`
- `26.2`
- `26.3-snapshot-1`

Minecraft's boat classes moved across these ranges, so Bigger Boats uses small
compatibility overlays for the different package layouts. See
`COMPATIBILITY.md` for details.

## Build

```powershell
.\gradlew.bat build --no-daemon --console=plain
.\gradlew.bat buildAllVersions --no-daemon --console=plain
```

Focused profile build:

```powershell
.\gradlew.bat build "-Pminecraft_version_profile=1.21.11" --no-daemon --console=plain
```

Full validation, including client and dedicated-server smoke launches:

```powershell
.\gradlew.bat ciValidation --no-daemon --console=plain
```
