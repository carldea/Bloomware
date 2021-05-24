package me.offeex.ofx.module;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import me.offeex.ofx.Main;
import me.offeex.ofx.hud.component.components.ModuleNotifier;
import me.offeex.ofx.setting.Setting;
import me.offeex.ofx.setting.settings.KeybindSetting;
import net.minecraft.client.MinecraftClient;

public class Module {
	
	protected static final MinecraftClient mc = MinecraftClient.getInstance();
//	public static ArrayList<Module> modules;
	
	public String name, description;
	public double version;
	public KeybindSetting keyCode = new KeybindSetting(0);
	public Category category;
	private boolean enabled, hidden;
	public int index;
	public List<Setting> settings = new ArrayList<Setting>();

	public Module(String name, String description, int key, Category category, boolean hidden) {
		super();
		this.name = name;
		this.description = description;
		keyCode.code = key;
		this.addSettings(keyCode);
		this.category = category;
		this.enabled = false;
		this.hidden = hidden;
	}
	public Module(String name, String description, int key, Category category, boolean hidden, double version) {
		super();
		this.name = name;
		this.description = description;
		keyCode.code = key;
		this.addSettings(keyCode);
		this.category = category;
		this.enabled = false;
		this.hidden = hidden;
		this.version = version;
	}
	
	public void addSettings(Setting... settings) {
		this.settings.addAll(Arrays.asList(settings));
		this.settings.sort(Comparator.comparingInt(s -> s == keyCode ? 1 : 0));
	}
	
	public enum Category {
		COMBAT("Combat", Color.decode("#ff2e2e"), 0),
		MOVEMENT("Movement", Color.decode("#edd628"),1),
		PLAYER("Player", Color.decode("#23e823"),2),
		RENDER("Render", Color.decode("#23e1e8"),3),
		MISCELLANEOUS("Misc", Color.decode("#526cff"),4),
		CLIENT("Client", Color.white, 5);

		public String name;
		public int categoryIndex;
		public Color color;

		public Module.Category getCategoryByIndex (int index) {
			Category cat = null;
			for (Module.Category category : Module.Category.values()) {
				if (category.categoryIndex == index)
					cat = category;
			}
			return cat;
		}

		Category(String name, Color color, int index) {
			this.name = name;
			this.color = color;
			this.categoryIndex = index;
		}
	}

	public String getName() {
		return this.name;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	public int getKey() {
		return keyCode.code;
	}
	
	public void setKey(int key) {
		this.keyCode.code = key;
		
		 if(Main.saveLoad != null) {
				Main.saveLoad.save();
		 }
	}

	public void toggle() {
		if (enabled) disable();
		else enable();

		if(Main.saveLoad != null) {
			Main.saveLoad.save();
		}
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;

		if(Main.saveLoad != null) {
			Main.saveLoad.save();
		}
	}

	public void enable() {
		Main.moduleNotifier.setMessage(this.name + " enabled!");
		setEnabled(true);
		onEnable();
	}

	public void disable() {
		Main.moduleNotifier.setMessage(this.name + " disabled!");
		setEnabled(false);
		onDisable();
	}

	public void onEnable() {
	}

	public void onDisable() {
	}

}
