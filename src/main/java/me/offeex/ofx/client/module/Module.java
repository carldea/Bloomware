package me.offeex.ofx.client.module;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.event.events.EventDrawOverlay;
//import me.offeex.ofx.client.gui.impl.hud.element.HudElement;
import me.offeex.ofx.client.setting.Setting;
import me.offeex.ofx.client.setting.settings.BooleanSetting;
import me.offeex.ofx.client.setting.settings.KeybindSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Module {
	
	protected static final MinecraftClient mc = MinecraftClient.getInstance();
	public final static int KEY_UNBOUND = -2;

	public String name, description;
	public double version;
	public KeybindSetting keyCode = new KeybindSetting(0);
	public BooleanSetting shown;
	private final Category category;
	private boolean enabled, hidden;
	public List<Setting> settings = new ArrayList<>();
	private final Component component = null;
	public int x = 10, y = 100, width, height = y + 4;

	public Module(String name, String description, int key, Category category, boolean hidden, Setting... s) {
		this.name = name;
		this.shown = new BooleanSetting("Show in arraylist", true);
		this.description = description;
		keyCode.code = key;
		settings = Arrays.asList(s);
		this.category = category;
		//settings.add(shown);
		this.enabled = false;
		this.hidden = hidden;
		//if (getCategory().equals(Category.HUD)) {
			//component = new HudElement(this, x, y, width, height);
		//}
	}
	
	//public void addSettings(Setting... settings) {
	//	this.settings.addAll(Arrays.asList(settings));
	//	this.settings.sort(Comparator.comparingInt(s -> s == keyCode ? 1 : 0));
	//}

	public enum Category {
		COMBAT("Combat", Color.decode("#ff2e2e"), 0),
		MOVEMENT("Movement", Color.decode("#edd628"),1),
		PLAYER("Player", Color.decode("#23e823"),2),
		RENDER("Render", Color.decode("#23e1e8"),3),
		MISCELLANEOUS("Misc", Color.decode("#526cff"),4),
		CLIENT("Client", Color.white, 5),
		HUD("HUD", Color.white, 6);

		private final String name;
		private final int categoryIndex;
		private final Color color;

		public Module.Category getCategoryByIndex(int index) {
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

		public String getName() {
			return name;
		}

		public int getCategoryIndex() {
			return categoryIndex;
		}

		public Color getColor() {
			return color;
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
		
//		 if(Bloomware.saveLoad != null) {
//				Bloomware.saveLoad.save();
//		 }
	}

	public void toggle() {
		if (enabled) disable();
		else enable();

//		if(Bloomware.saveLoad != null) {
//			Bloomware.saveLoad.save();
//		}
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;

//		if(Bloomware.saveLoad != null) {
//			Bloomware.saveLoad.save();
//		}
	}

	public void enable() {
		try {
			Bloomware.configManager.saveConfig(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bloomware.moduleNotifier.setMessage(this.name + " enabled!");
		Bloomware.chatNotifier.sendMsg(this.name + " enabled!");
		setEnabled(true);
		onEnable();
	}

	public void disable() {
		try {
			Bloomware.configManager.saveConfig(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bloomware.moduleNotifier.setMessage(this.name + " disabled!");
		Bloomware.chatNotifier.sendMsg(this.name + " disabled!");
		setEnabled(false);
		onDisable();
	}

	public void onEnable() {
	}

	public void onDisable() {
	}

	public void onTick() {
	}

	public List<Setting> getSettings() {
		return settings;
	}

	public Setting getSetting(int s) {
		return settings.get(s);
	}

	public int getCountOfSettings() {
		return settings.size() + 1;
	}

	public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {}
//	public void mouseClicked(double mouseX, double mouseY, int mouseButton) {}
//	public void mouseReleased(double mouseX, double mouseY, int mouseButton) {}


	public Component getComponent() {
		return component;
	}
}
