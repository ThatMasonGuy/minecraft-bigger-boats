package tempeststudios.biggerboats;

import net.fabricmc.api.ClientModInitializer;

public final class BiggerBoatsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BiggerBoatsSmokeTest.registerIfEnabled();
    }
}
