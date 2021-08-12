package me.offeex.ofx.client.module.modules.client;

import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.setting.settings.NumberSetting;

public class Colors extends Module {
    public Colors() {
        super("Colors", "Configures colors", Category.CLIENT, true,
                new NumberSetting("TextRed", 153, 1, 255, 1),
                new NumberSetting("TextGreen", 11, 1, 255, 1),
                new NumberSetting("TextBlue", 87, 1, 255, 1),
                new NumberSetting("SliderRed", 153, 1, 255, 1),
                new NumberSetting("SliderGreen", 11, 1, 255, 1),
                new NumberSetting("SliderBlue", 87, 1, 255, 1),
                new NumberSetting("HudRed", 249, 1, 255, 1),
                new NumberSetting("HudGreen", 47, 1, 255, 1),
                new NumberSetting("HudBlue", 154, 1, 255, 1));
    }

    @Override
    public void onEnable() {
        this.disable();
    }
}
