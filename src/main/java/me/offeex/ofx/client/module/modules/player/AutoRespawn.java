package me.offeex.ofx.client.module.modules.player;

import me.offeex.ofx.client.module.Module;
import net.minecraft.network.packet.c2s.play.ClientStatusC2SPacket;
import org.lwjgl.glfw.GLFW;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", "Allows you to respawn automatically", GLFW.GLFW_KEY_UNKNOWN, Category.PLAYER, false);
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
