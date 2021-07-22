package me.offeex.ofx.client.module.modules.client;

import me.offeex.ofx.client.command.CommandManager;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.ModuleManager;
import org.lwjgl.glfw.GLFW;

public class ChatNotifier extends Module {
    public ChatNotifier() {
        super("Chat Notifier", "Sends information about toggling modules to chat", GLFW.GLFW_KEY_UNKNOWN, Category.CLIENT, false);
    }

    public void sendMsg(String msg) {
        if (ModuleManager.isModuleEnabled("Chat Notifier")) {
            CommandManager.addChatMessage(msg);
        }
    }
}
