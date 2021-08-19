package me.offeex.bloomware.client.module.modules.player;

import me.offeex.bloomware.api.util.InventoryUtil;
import me.offeex.bloomware.client.module.Module;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

public class MiddleClickPearl extends Module {
    public MiddleClickPearl() {
        super("MiddleClickPearl", "Allows you to use e-pearls when you click middle button.", Category.PLAYER, false);
    }

    @Override
    public void onTick() {
        if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_MIDDLE) == GLFW.GLFW_PRESS) {
            int slotPearl = InventoryUtil.findItemInHotbar(Items.ENDER_PEARL);
            int oldSlot;
            if (slotPearl != -1) {
                oldSlot = mc.player.inventory.selectedSlot;
                mc.player.inventory.selectedSlot = slotPearl;
                mc.interactionManager.interactItem(mc.player, mc.world, Hand.MAIN_HAND);
                mc.player.inventory.selectedSlot = oldSlot;
            }
        }
    }
}
