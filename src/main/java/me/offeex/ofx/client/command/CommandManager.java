package me.offeex.ofx.client.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.event.events.EventKeyPress;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.reflections.Reflections;

public class CommandManager {
	
	public static List<Command> commands = new ArrayList<Command>();
	public static String prefix = "$";

	public static String ARROW = "" + Formatting.GRAY + Formatting.BOLD + " ➜ ";
	public static String USAGE = "" + Formatting.GRAY + Formatting.BOLD + "Usage: ";
	
	public CommandManager() {
		Bloomware.EVENTBUS.subscribe(listener);
		register();
	}

	public void register() {
		// Iterating every command and adding it to massive
		Set<Class<? extends Command>> reflections = new Reflections("me.offeex.ofx.client.command.commands").getSubTypesOf(Command.class);
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
		String prefix = "" + Formatting.BLACK + Formatting.BOLD + "<" + Formatting.GREEN + Formatting.BOLD + "O" + Formatting.AQUA + Formatting.BOLD + "F" + Formatting.LIGHT_PURPLE + Formatting.BOLD + "X" + Formatting.BLACK + Formatting.BOLD + "> ";
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText( prefix + Formatting.GRAY + Formatting.BOLD + "▸ ").append(textComponentString));
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
        		addChatMessage(Formatting.DARK_RED + "command doesn't exist. Type " + Formatting.RED + Formatting.ITALIC + prefix + "help " + Formatting.RESET + "" + Formatting.DARK_RED + "to see all commands.");
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
    }
	public static String getPrefix() {
		return prefix;
	}
	
}