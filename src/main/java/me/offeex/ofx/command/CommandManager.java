package me.offeex.ofx.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import me.offeex.ofx.Main;
import me.offeex.ofx.api.event.events.EventKeyPress;
import me.offeex.ofx.api.util.TextFormatting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.reflections.Reflections;

public class CommandManager {
	
	public static List<Command> commands = new ArrayList<Command>();
	public static String prefix = "$";
	public boolean commandFound = false;

	public static String ARROW = "" + TextFormatting.GRAY + TextFormatting.BOLD + " ➜ ";
	public static String USAGE = "" + TextFormatting.GRAY + TextFormatting.BOLD + "Usage: ";
	
	public CommandManager() {
		Main.EVENTBUS.subscribe(listener);
		register();
	}

	public void register() {
		// Iterating every command and adding it in "commands" list
		Set<Class<? extends Command>> reflections = new Reflections("me.offeex.ofx.command.commands").getSubTypesOf(Command.class);
		reflections.forEach(aClass -> {
			try {
				CommandManager.commands.add(aClass.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
	}

	public static void addChatMessage(String message) {
		Text textComponentString = new LiteralText(message);
		String prefix = "" + TextFormatting.BLACK + TextFormatting.BOLD + "<" + TextFormatting.GREEN + TextFormatting.BOLD + "O" + TextFormatting.AQUA + TextFormatting.BOLD + "F" + TextFormatting.LIGHT_PURPLE + TextFormatting.BOLD + "X" + TextFormatting.BLACK + TextFormatting.BOLD + "> ";
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText( prefix + TextFormatting.GRAY + TextFormatting.BOLD + "▸ ").append(textComponentString));
	}
	
	public static void callCommandReturn(String input) {
        String message = input;
        
        if(!message.startsWith(prefix))
        	return;
        
        message = message.substring(prefix.length());
        if(message.split(" ").length > 0) {
        	boolean commandFound = false;
        	String commandName = message.split(" ")[0];
        	for(Command c : commands) {
        		if(c.aliases.contains(commandName) || c.name.equalsIgnoreCase(commandName)) {
	        		c.onCommand(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length), message);
	        		commandFound = true;
	        		break;
        		}
        	}
        	if(!commandFound) {
        		addChatMessage(TextFormatting.DARK_RED + "command isn't exist. Type " + TextFormatting.RED + TextFormatting.ITALIC + prefix + "help " + TextFormatting.RESET + "" + TextFormatting.DARK_RED + "to see all commands.");
        	}
        }
    }
	
	@EventHandler
	private final Listener<EventKeyPress> listener = new Listener<>(e -> {
		if(InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), prefix.charAt(0)))
		if (prefix.length() == 1) {
                MinecraftClient.getInstance().openScreen(new ChatScreen(""));
            }
	});

	public static void setCommandPrefix(String pre) {
        prefix = pre;
        
//        if(Main.saveLoad != null) {
//			Main.saveLoad.save();
//		}
    }
	public static String getPrefix() {
		return prefix;
	}
	
}