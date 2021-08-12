package me.offeex.bloomware.client.module.modules.client;

import me.offeex.bloomware.client.command.CommandManager;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.module.ModuleManager;

public class ChatNotifier extends Module {
    public ChatNotifier() {
        super("ChatNotifier", "Sends information about toggling modules to chat", Category.CLIENT, false);
    }

    public void sendMsg(String msg) {
        if (ModuleManager.getModule("ChatNotifier").isEnabled()) {
            CommandManager.addChatMessage(msg);
        }
    }
}
