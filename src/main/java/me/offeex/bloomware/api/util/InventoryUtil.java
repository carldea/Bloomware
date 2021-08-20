package me.offeex.bloomware.api.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;

public class InventoryUtil {
    static MinecraftClient mc = MinecraftClient.getInstance();

    public static int findItemInHotbar(Item item) {
        for (int slot = 0; slot <= 8; slot++) {
            assert mc.player != null;
            if (mc.player.inventory.getStack(slot).getItem().equals(item)) {
                return slot;
            }
        }
        return -1;
    }
}
