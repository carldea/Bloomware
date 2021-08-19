package me.offeex.bloomware.client.command.commands;

import me.offeex.bloomware.client.command.Command;
import me.offeex.bloomware.client.command.CommandManager;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.module.ModuleManager;
import net.minecraft.util.Formatting;

public class Toggle extends Command {
	
	public Toggle() {
		super("toggle", "Toggles a module by name.", "module", "t");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if (args.length > 0) {
			String moduleName = args[0]; 
			boolean moduleFound = false;
			for (Module module : ModuleManager.getModules()) {
				String moduleIn = module.name;
				moduleIn = moduleIn.replaceAll("\\s", "");
				if (moduleIn.equalsIgnoreCase(moduleName)) {
					module.toggle();
					CommandManager.addChatMessage(module.name + " " + (module.isEnabled() ? Formatting.GREEN + "enabled" + Formatting.GRAY + "." : Formatting.RED + "disabled" + Formatting.GRAY + "."));
					moduleFound = true;
					break;
				}
			}
			if (!moduleFound) {
				CommandManager.addChatMessage(Formatting.DARK_RED + "module not found.");
			}
		} else {
			CommandManager.addChatMessage(CommandManager.USAGE + Formatting.RESET + Formatting.GOLD + CommandManager.prefix + "toggle <module>");
		}
	}

}