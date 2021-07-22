package me.offeex.ofx.client.module.modules.render;

import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.setting.settings.BooleanSetting;
import me.offeex.ofx.client.setting.settings.ModeSetting;
import me.offeex.ofx.client.setting.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class FullBright extends Module {

    public FullBright() {
        super("Full Bright",
                "Boosts your brightness",
                GLFW.GLFW_KEY_B,
                Category.RENDER,
                false,
                new ModeSetting("Mode", "nigger1", "nigger1", "nigger2", "nigger3"),
                new BooleanSetting("Bypass", false),
                new NumberSetting("Gamma", 16, 1, 16, 1));
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
