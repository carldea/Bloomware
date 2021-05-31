package me.offeex.ofx.command.commands;

import me.offeex.ofx.api.util.TextFormatting;
import me.offeex.ofx.command.Command;
import me.offeex.ofx.command.CommandManager;
import me.offeex.ofx.module.ModuleManager;

public class Prefix extends Command {
	
	public Prefix() {
		super("prefix", "change the prefix", "key", "p");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 1) {
			String key = args[0];
			CommandManager.setCommandPrefix(key);
			CommandManager.addChatMessage(TextFormatting.GREEN + "prefix " + TextFormatting.GRAY + "was set to " + TextFormatting.GREEN + CommandManager.prefix);
		}
		else
			CommandManager.addChatMessage(CommandManager.USAGE + TextFormatting.RESET + TextFormatting.GOLD + CommandManager.prefix + "prefix <key>");
	}
}
