package me.offeex.bloomware.client.module.modules.render;

import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;

public class ExtraTab extends Module {

    public final Setting<Number> count = register("MaxPlayers", 80, 80, 1000, 1);

    public ExtraTab() {
        super("ExtraTab", "Allows you to see more players in tab", Category.RENDER, false);
    }
}
