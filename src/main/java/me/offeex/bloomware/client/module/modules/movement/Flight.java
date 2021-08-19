package me.offeex.bloomware.client.module.modules.movement;

import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;

public class Flight extends Module {
    private final Setting<Number> speed = register("Speed", 1.0, 0.1, 10.0, 0.1);
    private boolean flyAbility;
    private double flySpeed = 0.0;

    public Flight() {
        super("Flight", "Allows you to fly.", Category.MOVEMENT, false);
    }

    @Override
    public void onTick() {
        mc.player.setOnGround(false);
        mc.player.abilities.setFlySpeed((float) (speed.getValue().doubleValue() / 5));
        mc.player.abilities.flying = true;
    }

    @Override
    public void onDisable() {
        mc.player.abilities.flying = flyAbility;
        mc.player.abilities.setFlySpeed((float) flySpeed);
    }

    @Override
    public void onEnable() {
        flyAbility = mc.player.abilities.flying;
        if (flyAbility) {
            flySpeed = mc.player.abilities.getFlySpeed();
        }
    }
}
