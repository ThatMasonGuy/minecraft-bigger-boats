package tempeststudios.biggerboats;

public final class BiggerBoatsGeometry {
    public static final int VANILLA_PASSENGERS = 2;
    public static final int MAX_PASSENGERS = 4;

    private BiggerBoatsGeometry() {
    }

    public static float seatOffset(int passengerIndex, int passengerCount) {
        int clampedCount = clamp(passengerCount, 1, MAX_PASSENGERS);
        int clampedIndex = clamp(passengerIndex, 0, clampedCount - 1);

        if (clampedCount == 1) {
            return 0.0F;
        }
        if (clampedCount == 2) {
            return clampedIndex == 0 ? 0.2F : -0.6F;
        }
        if (clampedCount == 3) {
            return new float[]{0.55F, -0.15F, -0.85F}[clampedIndex];
        }
        return new float[]{0.75F, 0.25F, -0.35F, -0.95F}[clampedIndex];
    }

    public static float boatLengthScale(int passengerCount) {
        int clampedCount = clamp(passengerCount, 0, MAX_PASSENGERS);
        if (clampedCount <= VANILLA_PASSENGERS) {
            return 1.0F;
        }
        return 1.0F + ((clampedCount - VANILLA_PASSENGERS) * 0.25F);
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
