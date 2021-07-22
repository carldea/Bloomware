package me.offeex.ofx.client.command.commands;

import me.offeex.ofx.client.command.Command;
import me.offeex.ofx.client.command.CommandManager;
import net.minecraft.util.Formatting;

public class Prefix extends Command {
	
	public Prefix() {
		super("prefix", "change the prefix", "key", "p");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 1) {
			String key = args[0];
			CommandManager.setCommandPrefix(key);
			CommandManager.addChatMessage(Formatting.GREEN + "prefix " + Formatting.GRAY + "was set to " + Formatting.GREEN + CommandManager.prefix);
		}
		else
			CommandManager.addChatMessage(CommandManager.USAGE + Formatting.RESET + Formatting.GOLD + CommandManager.prefix + "prefix <key>");
	}
}
