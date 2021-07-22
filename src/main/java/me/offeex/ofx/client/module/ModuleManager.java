package me.offeex.ofx.client.module;

import java.util.*;

import me.offeex.ofx.Bloomware;
import org.lwjgl.glfw.GLFW;

import me.offeex.ofx.api.event.events.EventKeyPress;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.reflections.Reflections;

public class ModuleManager {

	public static ArrayList<Module> modules;

	public ModuleManager() {
		Bloomware.EVENTBUS.subscribe(listener);
		
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
	
	public Module getModule (String name) {
		for (Module m : ModuleManager.modules) {
			if(m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}
	
	public static ArrayList<Module> getModules() {
		return modules;
	}
	
	public static List<Module> getModulesByCategory(Module.Category c) {
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : ModuleManager.modules) {
			if(!m.getName().equals("Esp2dHelper")) {
			if(m.getCategory() == c)
				modules.add(m);
			}
		}
		return modules;
	}

	public static void onTick(){
		for(Module mod : modules){
			if(mod.isEnabled()) mod.onTick();
		}
	}

	public static Module getModuleByName(String name) {
		return modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
	
	@EventHandler
	private final Listener<EventKeyPress> listener = new Listener<>(e -> {
		if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_F3))
			return;

		modules.stream().filter(m -> m.getKey() == e.getKey()).forEach(Module::toggle);
	});
	
}
