package me.offeex.ofx.command.commands;

import me.offeex.ofx.api.util.TextFormatting;
import me.offeex.ofx.command.Command;
import me.offeex.ofx.command.CommandManager;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.ModuleManager;

public class ModuleList extends Command {
	
	public ModuleList() {
		super("modulelist", "gets a list of the modules.",  "", "ml");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 0) {
			StringBuilder list = new StringBuilder();
			for (Module module : ModuleManager.getModules()) {
				if (module.category.equals(Module.Category.COMBAT))
					list.append(TextFormatting.RED).append(module.getName()).append(", ");
				if (module.category.equals(Module.Category.MOVEMENT))
					list.append(TextFormatting.YELLOW).append(module.getName()).append(", ");
				if (module.category.equals(Module.Category.PLAYER))
					list.append(TextFormatting.GREEN).append(module.getName()).append(", ");
				if (module.category.equals(Module.Category.RENDER))
					list.append(TextFormatting.AQUA).append(module.getName()).append(", ");
				if (module.category.equals(Module.Category.MISCELLANEOUS))
					list.append(TextFormatting.BLUE).append(module.getName()).append(", ");
				if (module.category.equals(Module.Category.CLIENT))
					list.append(TextFormatting.GRAY).append(module.getName()).append(", ");
			}
			CommandManager.addChatMessage(TextFormatting.RED + list.toString());
		} if(args.length > 0) CommandManager.addChatMessage(CommandManager.USAGE + TextFormatting.RESET + TextFormatting.GOLD + CommandManager.prefix + "modulelist");
	}
}
