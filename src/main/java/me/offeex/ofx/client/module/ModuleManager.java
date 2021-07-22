package me.offeex.ofx.client.module;

import java.util.*;

import com.google.common.eventbus.Subscribe;
import org.lwjgl.glfw.GLFW;

import me.offeex.ofx.api.event.events.EventKeyPress;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.reflections.Reflections;

public class ModuleManager {

	public static ArrayList<Module> modules;

	public ModuleManager() {
		modules = new ArrayList<>();

		// Iterating every module and adding it in "modules" list
		Set<Class<? extends Module>> reflections = new Reflections("me.offeex.ofx.client.module.modules").getSubTypesOf(Module.class);
		reflections.forEach(aClass -> {
			try {
				Module m = aClass.newInstance();
				modules.add(m);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});

	}
	
	public static boolean isModuleEnabled(String name) {
		Module m = modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		assert m != null;
		return m.isEnabled();
	}
	
	public Module getModule(String name) {
		return modules.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
	
	public static ArrayList<Module> getModules() {
		return modules;
	}
	
	public static List<Module> getModulesByCategory(Module.Category c) {
		List<Module> modules = new ArrayList<Module>();

		modules.forEach(module -> {
			if (module.getCategory().equals(c)) modules.add(module);
		});

		return modules;
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
