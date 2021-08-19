package me.offeex.bloomware.client.command.commands;

import me.offeex.bloomware.client.command.Command;
import me.offeex.bloomware.client.command.CommandManager;
import me.offeex.bloomware.client.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Formatting;

import java.util.stream.Collectors;

public class ModuleList extends Command {
	
	public ModuleList() {
		super("modulelist", "gets a list of the modules.",  "", "ml");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) CommandManager.addChatMessage(CommandManager.USAGE + Formatting.RESET + Formatting.GOLD + CommandManager.prefix + "modulelist");

		if(args.length == 0) {
			CommandManager.addChatMessage("Modules: " + Formatting.GRAY + ModuleManager.getModules().stream().map((m) -> m.name).collect(Collectors.joining(", ")));
		}
	}
}
