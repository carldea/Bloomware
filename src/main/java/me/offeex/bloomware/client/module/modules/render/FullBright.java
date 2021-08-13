package me.offeex.bloomware.client.module.modules.render;

import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.settings.NumberSetting;

public class FullBright extends Module {

    public FullBright() {
        super("FullBright", "Boosts your brightness", Category.RENDER, false,
                //Гамма от 3 до 16 одна и таже и НЕ МЕНЯЕТСЯ вопрос: нахуя так много?
                new NumberSetting("Gamma", 16, 1, 16, 1));
    }

    private double oldBrightness;
    private double newBrightness;

    @Override
    public void onEnable() {
        oldBrightness = mc.options.gamma;
        newBrightness = ((NumberSetting) this.getSetting(0)).getValue();
    }

    @Override
    public void onDisable() {
        mc.options.gamma = oldBrightness;
    }

    @Override
    public void onTick() {
        if(this.isEnabled()) {
            newBrightness = ((NumberSetting) this.getSetting(0)).getValue();
            if (mc.options.gamma != newBrightness) {
                mc.options.gamma = newBrightness;
            }
        }
    }
}
