package me.offeex.ofx.command.commands;

import me.offeex.ofx.api.util.TextFormatting;
import me.offeex.ofx.command.Command;
import me.offeex.ofx.command.CommandManager;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.ModuleManager;

public class Toggle extends Command {
	
	public Toggle() {
		super("toggle", "Toggles a module by name.", "module", "t");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) {
			String moduleName = args[0]; 
			boolean moduleFound = false;
			for(Module module : ModuleManager.modules) {
				String moduleIn = module.name;
				moduleIn = moduleIn.replaceAll("\\s", "");
				if(moduleIn.equalsIgnoreCase(moduleName)) {
					module.toggle();
					CommandManager.addChatMessage(module.name + " " + (module.isEnabled() ? TextFormatting.GREEN + "enabled" + TextFormatting.GRAY + "." : TextFormatting.RED + "disabled" + TextFormatting.GRAY + "."));
					moduleFound = true;
					break;
				}
			}
			if(!moduleFound) {
				CommandManager.addChatMessage(TextFormatting.DARK_RED + "module not found.");
			}
		}else {
			CommandManager.addChatMessage(CommandManager.USAGE + TextFormatting.RESET + TextFormatting.GOLD + CommandManager.prefix + "toggle <module>");
		}
	}

}