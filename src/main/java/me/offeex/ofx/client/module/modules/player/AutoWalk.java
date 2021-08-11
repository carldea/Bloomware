package me.offeex.ofx.client.module.modules.player;

import me.offeex.ofx.client.module.Module;
import org.lwjgl.glfw.GLFW;

public class AutoWalk extends Module {
    public AutoWalk() {
        super("AutoWalk", "Allows you to go automatically", GLFW.GLFW_KEY_UNKNOWN, Category.PLAYER, false);
    }

    @Override
    public void onTick() {
        mc.options.keyForward.setPressed(true);
    }

    @Override
    public void onDisable() {
        mc.options.keyForward.setPressed(false);
    }
}
