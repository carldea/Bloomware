package me.offeex.bloomware.client.module.modules.misc;

import com.google.common.eventbus.Subscribe;
import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.api.event.events.EventPacket;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import me.offeex.bloomware.mixins.IChatMessageC2SPacket;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

import java.util.ArrayList;
import java.util.Arrays;

public class BetterChat extends Module {
    private final ArrayList<String> forbiddenSymbols = new ArrayList<>();
    private final Setting<Boolean> greenChat = register("GreenChat", true);
    private final Setting<Boolean> chatSuffix = register("ChatSuffix", true);

    public BetterChat() {
        super("BetterChat", "Improves your chat.", Category.MISC, true);
        forbiddenSymbols.addAll(Arrays.asList(".", ";", ">", "$", "/"));
    }

    @Subscribe
    public void onPacketSend(EventPacket.Send event) {
        if (event.getPacket() instanceof ChatMessageC2SPacket) {
            IChatMessageC2SPacket packet = (IChatMessageC2SPacket) event.getPacket();
            String message = "";
            if (!forbiddenSymbols.contains(packet.getChatMessage().substring(0, 0)) && greenChat.getValue()) {
                message += "> " + packet.getChatMessage();
            }
            if (chatSuffix.getValue()) {
                if (greenChat.getValue()) {
                    message += " : " + Bloomware.name;
                } else {
                    message += packet.getChatMessage() + " : " + Bloomware.name;
                }
            }
            packet.setChatMessage(message);
        }
    }
}
