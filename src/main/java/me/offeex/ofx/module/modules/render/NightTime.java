package me.offeex.ofx.module.modules.render;

import me.offeex.ofx.Main;
import me.offeex.ofx.api.event.events.PacketEvent;
import me.offeex.ofx.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import org.lwjgl.glfw.GLFW;

public class NightTime extends Module {
    public NightTime() {
        super("Night Time", "Changes world time to night", GLFW.GLFW_KEY_UNKNOWN, Category.RENDER, false);
    }

    private long oldTime = 0;

    @Override
    public void onEnable() {
        assert mc.world != null;
        oldTime = mc.world.getTime();
    }

    @Override
    public void onTick() {
        assert mc.world != null;
        mc.world.setTimeOfDay(18000);
    }

    @Override
    public void onDisable() {
        assert mc.world != null;
        mc.world.setTimeOfDay(oldTime);
    }
}
