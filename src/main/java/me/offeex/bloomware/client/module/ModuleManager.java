package me.offeex.bloomware.client.module;

import java.util.*;

import com.google.common.eventbus.Subscribe;
import me.offeex.bloomware.api.event.events.EventKeyPress;
import org.lwjgl.glfw.GLFW;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.reflections.Reflections;

public class ModuleManager {

	public static ArrayList<Module> modules;

	public ModuleManager() {
		modules = new ArrayList<>();

		// Iterating every module and adding it in "modules" list
		Set<Class<? extends Module>> reflections = new Reflections("me.offeex.bloomware.client.module.modules").getSubTypesOf(Module.class);
		reflections.forEach(aClass -> {
			try {
				Module m = aClass.newInstance();
				modules.add(m);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});

	}
	
	public static Module getModule(String name) {
		return modules.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
	
	public static ArrayList<Module> getModules() {
		return modules;
	}
	
	public static List<Module> getModulesByCategory(Module.Category c) {
		List<Module> moduless = new ArrayList<>();

		modules.forEach(module -> {
			if (module.getCategory() == c) moduless.add(module);
		});

		return moduless;
	}

	public static void onTick(){
		if (MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().world != null) {
			modules.forEach(module -> {
				if (module.isEnabled()) module.onTick();
			});
		}
	}

	@Subscribe
	public void onKey(EventKeyPress e) {
		if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_F3))
			return;
		modules.stream().filter(m -> m.getKey() == e.getKey()).forEach(Module::toggle);
	}
	
}
