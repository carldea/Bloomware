package me.offeex.bloomware.client.module.modules.render;

import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.settings.NumberSetting;

public class ExtraTab extends Module {
    public ExtraTab() {
        super("ExtraTab", "Allows you to see more players in tab", Category.RENDER, false,
                new NumberSetting("Count", 80, 80, 1000, 1));
    }
}
