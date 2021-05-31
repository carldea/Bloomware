package me.offeex.ofx.module.modules.movement;

import me.offeex.ofx.Main;
import me.offeex.ofx.api.event.events.EventKeyPress;
import me.offeex.ofx.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import org.lwjgl.glfw.GLFW;

public class Speed extends Module {
    public Speed() {
        super("Speed", "Makes u faster", GLFW.GLFW_KEY_G, Category.MOVEMENT, false);
    }

    @Override
    public void onTick() {
        assert mc.player != null;
        if (mc.player.isSprinting()) {
            mc.player.jump();
            mc.player.forwardSpeed = 0.01f;
        }
    }
}
