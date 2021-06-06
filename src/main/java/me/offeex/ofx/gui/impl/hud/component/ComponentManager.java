package me.offeex.ofx.gui.impl.hud.component;

import me.offeex.ofx.command.CommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Set;

public class ComponentManager {

	private static ArrayList<Component> components;

	public ComponentManager() {
		components = new ArrayList<>();

		// Iterating every module and adding it in "modules" list
		Set<Class<? extends Component>> reflections = new Reflections("me.offeex.ofx.gui.impl.hud.component.components").getSubTypesOf(Component.class);
		reflections.forEach(aClass -> {
			try {
					ComponentManager.components.add(aClass.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
	}
	
	public static boolean isComponentEnabled(String name) {
		Component c = components.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		assert c != null;
		return c.isEnabled();
	}
	
	public Component getComponent (String name) {
		for (Component c : ComponentManager.components) {
			if(c.getName().equalsIgnoreCase(name)) {
				return c;
			}
		}
		return null;
	}
	
	public static ArrayList<Component> getComponents() {
		return components;
	}
	
	public static Component getComponentByName(String name) {
		return components.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
	
	public static void addChatMessage(String message) {
		Text textComponentString = new LiteralText(message);
		//message = TextFormatting.AQUA + "@" + TextFormatting.ITALIC + Main.name + TextFormatting.GRAY + ": " + message;
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText(CommandManager.getPrefix()).append(textComponentString));
		
		
	}
	
}
