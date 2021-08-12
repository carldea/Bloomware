package me.offeex.bloomware.client.command.commands;

import me.offeex.bloomware.client.command.Command;
import me.offeex.bloomware.client.command.CommandManager;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.module.ModuleManager;
import net.minecraft.util.Formatting;

public class ModuleList extends Command {
	
	public ModuleList() {
		super("modulelist", "gets a list of the modules.",  "", "ml");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) CommandManager.addChatMessage(CommandManager.USAGE + Formatting.RESET + Formatting.GOLD + CommandManager.prefix + "modulelist");

		if(args.length == 0) {
			StringBuilder list = new StringBuilder();
			for (Module module : ModuleManager.getModules()) {
				switch (module.getCategory()) {
					case COMBAT:
						list.append(Formatting.RED).append(module.getName()).append(", ");
						break;
					case MOVEMENT:
						list.append(Formatting.YELLOW).append(module.getName()).append(", ");
						break;
					case PLAYER:
						list.append(Formatting.GREEN).append(module.getName()).append(", ");
						break;
					case RENDER:
						list.append(Formatting.AQUA).append(module.getName()).append(", ");
						break;
					case MISC:
						list.append(Formatting.BLUE).append(module.getName()).append(", ");
						break;
					case CLIENT:
						list.append(Formatting.GRAY).append(module.getName()).append(", ");
						break;
				}
			}
			CommandManager.addChatMessage(Formatting.RED + list.toString());
		}
	}
}
