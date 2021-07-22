package me.offeex.ofx.client.command.commands;

import me.offeex.ofx.client.command.Command;
import me.offeex.ofx.client.command.CommandManager;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.ModuleManager;

public class Changelog extends Command {
    public Changelog() {
        super("changelog", "shows last updated/added features", "", "ch");
    }

    @Override
    public void onCommand(String[] args, String command) {
        for (Module m : ModuleManager.getModules()) {
            if (m.version == 0.1) {
                CommandManager.addChatMessage(TextFormatting.RED + m.getName() + CommandManager.ARROW + TextFormatting.RESET + TextFormatting.YELLOW + TextFormatting.ITALIC + m.getDescription());
            }
        }
    }
}
