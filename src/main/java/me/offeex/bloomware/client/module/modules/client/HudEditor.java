package me.offeex.bloomware.client.module.modules.client;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.module.Module;

public class HudEditor extends Module {

    public HudEditor() {
        super("HudEditor", "Edit your HUD", Category.CLIENT, true);
    }

    @Override
    public void onEnable() {
        mc.openScreen(Bloomware.hudEditor);
        disable();
    }
}
