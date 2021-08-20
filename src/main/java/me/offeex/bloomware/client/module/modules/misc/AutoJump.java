package me.offeex.bloomware.client.module.modules.misc;

import me.offeex.bloomware.client.module.Module;

public class AutoJump extends Module {

    public AutoJump() {
        super("AutoJump", "Automatically jumps", Category.MOVEMENT, false);
    }

    @Override
    public void onDisable() {
        mc.options.keyJump.setPressed(false);
    }

    @Override
    public void onTick() {
        if (mc.player.isOnGround() && mc.player != null) {
            mc.options.keyJump.setPressed(true);
        }
    }
}
