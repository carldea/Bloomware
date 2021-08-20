package me.offeex.bloomware.client.module.modules.movement;

import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;

import java.util.ArrayList;
import java.util.Arrays;

public class AutoWalk extends Module {
    public AutoWalk() {
        super("AutoWalk", "Allows you to go automatically", Category.MOVEMENT, false);
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
