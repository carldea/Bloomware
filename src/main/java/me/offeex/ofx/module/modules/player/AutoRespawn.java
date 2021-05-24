package me.offeex.ofx.module.modules.player;

import me.offeex.ofx.Main;
import me.offeex.ofx.api.event.events.PacketEvent;
import me.offeex.ofx.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.packet.c2s.play.ClientStatusC2SPacket;
import org.lwjgl.glfw.GLFW;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("Auto Respawn", "Allows you to respawn automatically", GLFW.GLFW_KEY_UNKNOWN, Category.PLAYER, false);
    }

    @Override
    public void onEnable() {
        Main.EVENTBUS.subscribe(listener);
    }

    @EventHandler
    private final Listener<PacketEvent> listener = new Listener<>(event -> {
        assert mc.player != null;
        if (mc.getNetworkHandler() != null) {
            if (mc.player.isDead()) {
                mc.getNetworkHandler().sendPacket(new ClientStatusC2SPacket(ClientStatusC2SPacket.Mode.PERFORM_RESPAWN));
            }
        }
    });

    @Override
    public void onDisable() {
        Main.EVENTBUS.unsubscribe(listener);
    }
}
