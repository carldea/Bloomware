package me.offeex.bloomware.client.module.modules.player;

import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

import java.util.Objects;

public class NoFall extends Module {
    private final Setting<Number> fallDistance = register("FallDistance", 3.0, 3.0, 256.0, 1.0);

    public NoFall() {
        super("NoFall", "Allows you not to take damage when you fall.", Category.PLAYER, false);
    }

    @Override
    public void onTick() {
        if (mc.player != null) {
            if (mc.player.fallDistance >= fallDistance.getValue().intValue()) {
                Objects.requireNonNull(mc.getNetworkHandler()).sendPacket(new PlayerMoveC2SPacket(true));
            }
        }
    }
}
