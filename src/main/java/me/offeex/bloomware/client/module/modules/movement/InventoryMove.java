package me.offeex.bloomware.client.module.modules.movement;

import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class InventoryMove extends Module {

    private final Setting<Number> speed = register("RotationSpeed", 0.5, 0, 1, 0.1);

    public InventoryMove() {
        super("InventoryMove", "Allows you to move when GUI is open", Category.MOVEMENT, false);
    }

    @Override
    public void onTick() { // Rotations in Inventory menu
        if (mc.player == null || mc.currentScreen == null || mc.currentScreen instanceof ChatScreen) return;

        if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_LEFT)) {
            mc.player.yaw = (float) (mc.player.yaw - speed.getValue().floatValue());
        }
        if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT)) {
            mc.player.yaw = (float) (mc.player.yaw + speed.getValue().floatValue());
        }
        if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_UP)) {
            mc.player.pitch = (float) (mc.player.pitch - speed.getValue().floatValue());
        }
        if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_DOWN)) {
            mc.player.pitch = (float) (mc.player.pitch + speed.getValue().floatValue());
        }
    }
}
