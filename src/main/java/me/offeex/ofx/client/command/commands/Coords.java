package me.offeex.ofx.client.command.commands;

import me.offeex.ofx.api.util.TextFormatting;
import me.offeex.ofx.client.command.Command;
import me.offeex.ofx.client.command.CommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Clipboard;

public class Coords extends Command {

    public Coords() {
        super("coords", "Copy and shows your coords", "no", "coordinates");
    }

    @Override
    public void onCommand(String[] args, String command) {
        assert MinecraftClient.getInstance().player != null;
        int x = (int) MinecraftClient.getInstance().player.getX();
        int y = (int) MinecraftClient.getInstance().player.getY();
        int z = (int) MinecraftClient.getInstance().player.getZ();
        Clipboard clipboard = new Clipboard();
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("show")) {
                CommandManager.addChatMessage("Your current coords:" + CommandManager.ARROW + TextFormatting.GREEN + TextFormatting.BOLD + x + " " + TextFormatting.AQUA + TextFormatting.BOLD + y + " " + TextFormatting.LIGHT_PURPLE + TextFormatting.BOLD + z);
            }
            if (args[0].equalsIgnoreCase("copy")) {
                clipboard.setClipboard(127, x + " " + z);
            }
        }
        if (args.length != 1) {
            CommandManager.addChatMessage(CommandManager.USAGE + TextFormatting.RESET + TextFormatting.GOLD + CommandManager.prefix + "coords show/copy");
        }
    }
}
