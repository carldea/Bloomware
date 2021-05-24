package me.offeex.ofx.module.modules.render;

import me.offeex.ofx.Main;
import me.offeex.ofx.hud.component.components.ModuleNotifier;
import me.offeex.ofx.module.Module;
import org.lwjgl.glfw.GLFW;

public class FullBright extends Module {
    public FullBright() {
        super("Full Bright", "Boosts your brightness", GLFW.GLFW_KEY_B, Category.RENDER, false);
    }

    private double oldBrightness;

    @Override
    public void onEnable() {
        oldBrightness = mc.options.gamma;
        mc.options.gamma = 300;
    }

    @Override
    public void onDisable() {
        mc.options.gamma = oldBrightness;
    }
}
