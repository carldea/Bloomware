package me.offeex.ofx.api.util;

import net.minecraft.client.MinecraftClient;

public class DimensionUtil {
    public static int getDimension() {
        assert MinecraftClient.getInstance().world != null;
        switch (MinecraftClient.getInstance().world.getRegistryKey().getValue().getPath()) {
            case "the_nether": return 1;
            case "the_end":    return 2;
            default:           return 0;
        }
    }
}
