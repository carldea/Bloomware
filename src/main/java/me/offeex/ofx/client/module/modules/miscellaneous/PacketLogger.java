package me.offeex.ofx.client.module.modules.miscellaneous;

import com.google.common.eventbus.Subscribe;
import me.offeex.ofx.api.event.events.EventPacket;
import me.offeex.ofx.client.command.CommandManager;
import me.offeex.ofx.client.module.Module;

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
