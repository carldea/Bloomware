package me.offeex.ofx.command.commands;

import me.offeex.ofx.api.util.TextFormatting;
import me.offeex.ofx.command.Command;
import me.offeex.ofx.command.CommandManager;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.ModuleManager;

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
