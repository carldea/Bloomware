package me.offeex.bloomware.client.module.modules.player;

import me.offeex.bloomware.client.module.Module;
import net.minecraft.network.packet.c2s.play.ClientStatusC2SPacket;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", "Allows you to respawn automatically", Category.PLAYER, false);
    }

    @Override
    public void onTick() {
        if (mc.getNetworkHandler() != null) {
            if (mc.player.isDead()) {
                mc.getNetworkHandler().sendPacket(new ClientStatusC2SPacket(ClientStatusC2SPacket.Mode.PERFORM_RESPAWN));
            }
        }
    }
}
