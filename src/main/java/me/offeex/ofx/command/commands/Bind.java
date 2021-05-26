package me.offeex.ofx.command.commands;

import me.offeex.ofx.api.util.TextFormatting;
import me.offeex.ofx.command.Command;
import me.offeex.ofx.command.CommandManager;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.ModuleManager;
import org.lwjgl.glfw.GLFW;

public class Bind extends Command {
    public Bind() {
        super("bind", "set bind of module", "module", "b");
    }

    public void onCommand(String[] args, String command) {
        try {
            if (args[0].equals("set")) {
                boolean bug = false;
                if(args.length == 3) {
                String module = args[1];
                String key = args[2];
                int rawKey = GLFW.class.getField("GLFW_KEY_" + key.toUpperCase()).getInt(null);
                for (Module m : ModuleManager.getModules()) {
                    if (m.getName().equalsIgnoreCase(module)) {
                        m.setKey(rawKey);
                        CommandManager.addChatMessage(TextFormatting.GREEN + m.getName() + " " + TextFormatting.GRAY + "was set to " + TextFormatting.GREEN + key.toUpperCase());
                        return;
                    }
                    else bug = true;
                }
            }
                if (bug)
                    CommandManager.addChatMessage(TextFormatting.DARK_RED + "module is not exist");
        }
            if (args.length != 3 || !args[0].equalsIgnoreCase("set"))
                CommandManager.addChatMessage(CommandManager.USAGE + TextFormatting.RESET + TextFormatting.GOLD + CommandManager.getPrefix() + "bind set <module> <key>");

            if (args[0].equals("reset")) {
                if (args.length == 2) {
                boolean bug = true;
                String module = args[1];
                for (Module m : ModuleManager.getModules()) {
                    if (m.getName().equalsIgnoreCase(module)) {
                        bug = false;
                        m.setKey(0);
                        CommandManager.addChatMessage(TextFormatting.GREEN + m.getName() + " " + TextFormatting.GRAY + "was reset");
                    }
                }
                    if (bug)
                        CommandManager.addChatMessage(TextFormatting.DARK_RED + "module is not exist");
            }
                if (args.length != 2)
                    CommandManager.addChatMessage(CommandManager.USAGE + TextFormatting.RESET + TextFormatting.GOLD + CommandManager.getPrefix() + "bind reset <module>");
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            CommandManager.addChatMessage(TextFormatting.DARK_RED + "key is not exist");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            CommandManager.addChatMessage(CommandManager.USAGE + TextFormatting.RESET + TextFormatting.GOLD + CommandManager.getPrefix() + "bind set <module> <key> / " + CommandManager.getPrefix() + "bind reset <module>");
        }
    }
}