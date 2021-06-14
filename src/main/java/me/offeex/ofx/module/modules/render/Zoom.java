package me.offeex.ofx.module.modules.render;

import me.offeex.ofx.module.Module;
import org.lwjgl.glfw.GLFW;

public class Zoom extends Module {
    public Zoom() {
        super("Zoom", "Increases your fov", GLFW.GLFW_KEY_UNKNOWN, Category.RENDER, false);
    }

    private double oldFov;

    @Override
    public void onEnable() {
        oldFov = mc.options.fov;
        mc.options.fov = 30;
    }

    @Override
    public void onDisable() {
        mc.options.fov = oldFov;
    }
}
