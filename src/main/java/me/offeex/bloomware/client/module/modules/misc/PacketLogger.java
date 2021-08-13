package me.offeex.bloomware.client.module.modules.misc;

import com.google.common.eventbus.Subscribe;
import me.offeex.bloomware.api.event.events.EventPacket;
import me.offeex.bloomware.client.command.CommandManager;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;

public class PacketLogger extends Module {

    private final Setting<Boolean> receive = register("ViewReceived", true);
    private final Setting<Boolean> send = register("ViewSent", true);

    public PacketLogger() {
        super("PacketLogger", "", Category.MISC, true);
    }

    @Subscribe
    public void onPacketReceive(EventPacket.Receive eventPacket) {
        if (receive.getValue()) {
            CommandManager.addChatMessage("Received packet -> " + eventPacket.getPacket().toString());
        }
    }

    @Subscribe
    public void onPacketSend(EventPacket.Send event) {
        if (send.getValue()) {
            CommandManager.addChatMessage("Sent packet -> " + event.getPacket().toString());
        }
    }
}
