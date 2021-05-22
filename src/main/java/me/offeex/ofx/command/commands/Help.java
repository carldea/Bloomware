package me.offeex.ofx.command.commands;

import me.offeex.ofx.api.util.TextFormatting;
import me.offeex.ofx.command.Command;
import me.offeex.ofx.command.CommandManager;

public class Help extends Command {
	
	public Help() {
		super("help", "Shows list of commands.", "no","h");
	}

	@Override
	public void onCommand(String[] args, String command) {
		for (Command cmd : CommandManager.commands) {
			if (cmd.modifier.equals("key"))
				CommandManager.addChatMessage(TextFormatting.RED + CommandManager.getPrefix() + cmd.getName() + TextFormatting.GOLD +" <key>" + CommandManager.ARROW + TextFormatting.RESET + TextFormatting.YELLOW + TextFormatting.ITALIC + cmd.getDescription());
			if (cmd.modifier.equals("module"))
				CommandManager.addChatMessage(TextFormatting.RED + CommandManager.getPrefix() + cmd.getName() + TextFormatting.GOLD +" <module>" + CommandManager.ARROW + TextFormatting.RESET + TextFormatting.YELLOW + TextFormatting.ITALIC + cmd.getDescription());
			if (cmd.modifier.equals(""))
				CommandManager.addChatMessage(TextFormatting.RED + CommandManager.getPrefix() + cmd.getName() + CommandManager.ARROW + TextFormatting.RESET + TextFormatting.YELLOW + TextFormatting.ITALIC + cmd.getDescription());
		}
	}
}