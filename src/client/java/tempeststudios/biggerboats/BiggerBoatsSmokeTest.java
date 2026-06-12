package tempeststudios.biggerboats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

public final class BiggerBoatsSmokeTest {
    private static final String SMOKE_TEST_PROPERTY = "biggerboats.smokeTest";
    private static final int PASS_AFTER_TICKS = 20;

    private static int ticks;
    private static boolean complete;

    private BiggerBoatsSmokeTest() {
    }

    public static void registerIfEnabled() {
        if (!Boolean.getBoolean(SMOKE_TEST_PROPERTY)) {
            return;
        }

        System.out.println("[BiggerBoats] Automated client smoke test armed.");
        ClientTickEvents.END_CLIENT_TICK.register(BiggerBoatsSmokeTest::tick);
    }

    private static void tick(Minecraft client) {
        if (complete) {
            return;
        }

        ticks++;
        if (ticks < PASS_AFTER_TICKS) {
            return;
        }

        complete = true;
        if (BiggerBoatsGeometry.MAX_PASSENGERS != 4 || BiggerBoatsGeometry.boatLengthScale(4) <= 1.0F) {
            throw new IllegalStateException("Bigger Boats smoke geometry is not configured for four passengers.");
        }

        System.out.println(
                "BIGGERBOATS_SMOKE_TEST_PASS minecraftProfile="
                        + System.getProperty("biggerboats.smokeMinecraftProfile", "unknown")
                        + " gameVersion="
                        + System.getProperty("biggerboats.smokeGameVersion", "unknown")
                        + " releaseProfile="
                        + System.getProperty("biggerboats.smokeReleaseProfile", "unknown")
                        + " installSet="
                        + System.getProperty("biggerboats.smokeInstallSet", "unknown")
                        + " maxPassengers=" + BiggerBoatsGeometry.MAX_PASSENGERS
                        + " injectedMods="
                        + System.getProperty("fabric.addMods", "unknown")
        );
        client.stop();
    }
}
