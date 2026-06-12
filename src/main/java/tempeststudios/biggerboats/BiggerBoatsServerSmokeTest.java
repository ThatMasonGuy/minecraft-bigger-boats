package tempeststudios.biggerboats;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

import java.lang.reflect.Method;

public final class BiggerBoatsServerSmokeTest {
    private static final String SMOKE_TEST_PROPERTY = "biggerboats.smokeTest";
    private static final int PASS_AFTER_TICKS = 20;

    private static int ticks;
    private static boolean complete;

    private BiggerBoatsServerSmokeTest() {
    }

    public static void registerIfEnabled() {
        if (!Boolean.getBoolean(SMOKE_TEST_PROPERTY)) {
            return;
        }

        System.out.println("[BiggerBoats] Automated server smoke test armed.");
        ServerTickEvents.END_SERVER_TICK.register(BiggerBoatsServerSmokeTest::tick);
    }

    private static void tick(MinecraftServer server) {
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
                "BIGGERBOATS_SERVER_SMOKE_TEST_PASS minecraftProfile="
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
        stopServer(server);
    }

    private static void stopServer(MinecraftServer server) {
        if (invokeStop(server, "halt", new Class<?>[]{boolean.class}, new Object[]{false})) {
            return;
        }
        if (invokeStop(server, "stopServer", new Class<?>[0], new Object[0])) {
            return;
        }
        if (invokeStop(server, "stop", new Class<?>[0], new Object[0])) {
            return;
        }
        throw new IllegalStateException("Server smoke test could not stop the Minecraft server.");
    }

    private static boolean invokeStop(
            MinecraftServer server,
            String methodName,
            Class<?>[] parameterTypes,
            Object[] arguments) {
        try {
            Method method = findMethod(methodName, parameterTypes);
            method.invoke(server, arguments);
            return true;
        } catch (Throwable ignored) {
            return false;
        }
    }

    private static Method findMethod(String methodName, Class<?>[] parameterTypes) throws NoSuchMethodException {
        try {
            return MinecraftServer.class.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException ignored) {
            Method method = MinecraftServer.class.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method;
        }
    }
}
