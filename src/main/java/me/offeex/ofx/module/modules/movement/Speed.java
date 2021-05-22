package me.offeex.ofx.module.modules.movement;

import me.offeex.ofx.module.Module;
import org.lwjgl.glfw.GLFW;

public class Speed extends Module {
    public Speed() {
        super("Speed", "Makes u faster", GLFW.GLFW_KEY_G, Category.MOVEMENT, false);
    }
}
