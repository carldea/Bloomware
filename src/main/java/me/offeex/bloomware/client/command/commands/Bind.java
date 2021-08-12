package me.offeex.bloomware.client.command.commands;

import me.offeex.bloomware.client.command.Command;
import me.offeex.bloomware.client.command.CommandManager;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.module.ModuleManager;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Formatting;

public class Bind extends Command {
    public Bind() {
        super("bind", "set bind of module", "module", "b");
    }

    public void onCommand(String[] args, String command) {
        boolean found = false;

        if (args.length == 0) {
            CommandManager.addChatMessage(Formatting.DARK_RED + "Invalid Syntax");
            return;
        }

        for (Module m : ModuleManager.getModules()) {
            if (m.getName().equalsIgnoreCase(args[0])) {
                found = true;
                try {
                    if (args[1].equalsIgnoreCase("null") || args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("unbind")) {
                        m.setKey(-1);
                        CommandManager.addChatMessage(Formatting.GREEN + m.getName() + " was unbound");
                    } else {
                        m.setKey(InputUtil.fromTranslationKey("key.keyboard." + args[1].toLowerCase()).getCode());
                        CommandManager.addChatMessage(Formatting.GREEN + m.getName() + " was bound to " + args[1].toUpperCase() + "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    CommandManager.addChatMessage(Formatting.RED + "Invalid Syntax. Usage: " + CommandManager.prefix + "bind <Module> <Key>");
                }

                break;
            }
        }

        if (!found) {
            CommandManager.addChatMessage(Formatting.RED + "Module not found.");
        }
    }
}