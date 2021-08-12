package me.offeex.bloomware.client.module.modules.misc;

import com.google.common.eventbus.Subscribe;
import me.offeex.bloomware.api.event.events.EventPacket;
import me.offeex.bloomware.client.command.CommandManager;
import me.offeex.bloomware.client.module.Module;

public class PacketLogger extends Module {
    public PacketLogger() {
        super("PacketLogger", "", Category.MISC, true);
    }

    @Subscribe
    public void onPacketReceive(EventPacket.Receive eventPacket) {
        CommandManager.addChatMessage("Received packet -> " + eventPacket.getPacket().toString());
    }

    @Subscribe
    public void onPacketSend(EventPacket.Send event) {
        CommandManager.addChatMessage("Sent packet -> " + event.getPacket().toString());
    }
}
