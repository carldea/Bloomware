package me.offeex.ofx.client.command.commands;

import me.offeex.ofx.client.command.Command;
import me.offeex.ofx.client.command.CommandManager;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.ModuleManager;
import net.minecraft.util.Formatting;

public class Changelog extends Command {
    public Changelog() {
        super("changelog", "shows last updated/added features", "", "ch");
    }

    @Override
    public void onCommand(String[] args, String command) {
        for (Module m : ModuleManager.getModules()) {
            if (m.version == 0.1) {
                CommandManager.addChatMessage(Formatting.RED + m.getName() + CommandManager.ARROW + Formatting.RESET + Formatting.YELLOW + Formatting.ITALIC + m.getDescription());
            }
        }
    }
}
