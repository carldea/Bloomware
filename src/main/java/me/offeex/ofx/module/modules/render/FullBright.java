package me.offeex.ofx.module.modules.render;

import me.offeex.ofx.Main;
import me.offeex.ofx.hud.component.components.ModuleNotifier;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.setting.Setting;
import me.offeex.ofx.setting.SettingManager;
import me.offeex.ofx.setting.settings.BooleanSetting;
import me.offeex.ofx.setting.settings.ModeSetting;
import org.lwjgl.glfw.GLFW;

public class FullBright extends Module {
    public ModeSetting mode = new ModeSetting("Mode", this, "nigger1", "nigger1", "nigger2", "nigger3");
    public BooleanSetting popbob = new BooleanSetting("Popbob", this, false);

    public FullBright() {
        super("Full Bright", "Boosts your brightness", GLFW.GLFW_KEY_B, Category.RENDER, false);
        this.addSettings(mode, popbob);
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
