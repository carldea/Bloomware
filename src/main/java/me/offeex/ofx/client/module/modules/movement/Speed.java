package me.offeex.ofx.client.module.modules.movement;

import me.offeex.ofx.client.module.Module;
import org.lwjgl.glfw.GLFW;

public class Speed extends Module {
    public Speed() {
        super("Speed", "Makes u faster", GLFW.GLFW_KEY_G, Category.MOVEMENT, false);
    }

    @Override
    public void onTick() {
        if (mc.player.isSprinting()) {
            mc.player.jump();
            mc.player.forwardSpeed = 0.01f;
        }
    }
}
