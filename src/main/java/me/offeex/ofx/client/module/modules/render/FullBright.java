package me.offeex.ofx.client.module.modules.render;

import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.setting.settings.BooleanSetting;
import me.offeex.ofx.client.setting.settings.ModeSetting;
import me.offeex.ofx.client.setting.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class FullBright extends Module {

    public FullBright() {
        super("FullBright", "Boosts your brightness", Category.RENDER, false,
                new NumberSetting("Gamma", 16, 1, 16, 1));
    }

    private double oldBrightness;

    @Override
    public void onEnable() {
        oldBrightness = mc.options.gamma;
        mc.options.gamma = ((NumberSetting) this.getSetting(0)).getValue();
    }

    @Override
    public void onDisable() {
        mc.options.gamma = oldBrightness;
    }
}
