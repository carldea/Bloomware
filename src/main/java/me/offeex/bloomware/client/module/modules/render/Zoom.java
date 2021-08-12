package me.offeex.bloomware.client.module.modules.render;

import me.offeex.bloomware.client.module.Module;

public class Zoom extends Module {
    public Zoom() {
        super("Zoom", "Increases your fov", Category.RENDER, false);
    }

    private double oldFov;

    @Override
    public void onEnable() {
        oldFov = mc.options.fov;
    }

    public void onTick() {
        mc.options.fov = 30;
    }

    @Override
    public void onDisable() {
        mc.options.fov = oldFov;
    }
}
