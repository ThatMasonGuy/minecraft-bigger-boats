package tempeststudios.biggerboats;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BiggerBoats implements ModInitializer {
    public static final String MOD_ID = "bigger-boats";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        BiggerBoatsServerSmokeTest.registerIfEnabled();
        LOGGER.info("Bigger Boats initialized with max boat passengers={}.", BiggerBoatsGeometry.MAX_PASSENGERS);
    }
}
