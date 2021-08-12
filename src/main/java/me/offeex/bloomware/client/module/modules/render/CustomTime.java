package me.offeex.bloomware.client.module.modules.render;

import com.google.common.eventbus.Subscribe;
import me.offeex.bloomware.api.event.events.EventPacket;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.settings.NumberSetting;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;

public class CustomTime extends Module {
    public CustomTime() {
        super("CustomTime", "Changes world time you want.", Category.RENDER, false,
                new NumberSetting("Time", 18000, 0, 24000, 1));
    }

    private long oldTime = 0;

    @Override
    public void onEnable() {
        oldTime = mc.world.getTime();
    }

    @Override
    public void onTick() {
        mc.world.setTimeOfDay((int) ((NumberSetting) this.getSetting(0)).getValue());
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
