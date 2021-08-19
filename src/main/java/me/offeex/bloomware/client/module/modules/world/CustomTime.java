package me.offeex.bloomware.client.module.modules.world;

import com.google.common.eventbus.Subscribe;
import me.offeex.bloomware.api.event.events.EventPacket;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;

public class CustomTime extends Module {

    private final Setting<Number> time = register("Time", 18000, 0, 24000, 1);

    public CustomTime() {
        super("CustomTime", "Changes world time you want.", Category.WORLD, false);
    }

    private long oldTime = 0;

    @Override
    public void onEnable() {
        oldTime = mc.world.getTime();
    }

    @Override
    public void onTick() {
        mc.world.setTimeOfDay(time.getValue().intValue());
    }

    @Override
    public void onDisable() {
        mc.world.setTimeOfDay(oldTime);
    }

    // Fixed by @author https://github.com/fuckyouthinkimboogieman on 22 July of 2021
    @Subscribe
    public void onPacket(EventPacket.Receive event) {
        if (event.getPacket() instanceof WorldTimeUpdateS2CPacket) {
            event.setCancelled(true);
        }
    }
}