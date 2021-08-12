package me.offeex.bloomware.client.command.commands;

import me.offeex.bloomware.client.command.Command;
import me.offeex.bloomware.client.command.CommandManager;

public class Help extends Command {
	
	public Help() {
		super("help", "Shows list of commands.", "no","h");
	}

	@Override
	public void onCommand(String[] args, String command) {
		String commandsList = "Commands (" + CommandManager.commands.size() + "): ";

		for (Command cmd : CommandManager.commands) {
			commandsList += cmd.name + ", ";
		}

		commandsList += ".";

		CommandManager.addChatMessage(commandsList.substring(0, commandsList.length() - 3) + ".");
	}
}