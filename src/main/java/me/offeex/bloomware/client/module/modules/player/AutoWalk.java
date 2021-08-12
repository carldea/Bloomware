package me.offeex.bloomware.client.module.modules.player;

import me.offeex.bloomware.client.module.Module;

public class AutoWalk extends Module {
    public AutoWalk() {
        super("AutoWalk", "Allows you to go automatically", Category.PLAYER, false);
    }

    @Override
    public void onTick() {
        mc.options.keyForward.setPressed(true);
    }

    @Override
    public void onDisable() {
        mc.options.keyForward.setPressed(false);
    }
}
