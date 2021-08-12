package me.offeex.ofx.client.module;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import me.offeex.ofx.Bloomware;
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
	public KeybindSetting key = new KeybindSetting(0);
	public BooleanSetting shown;
	private final Category category;
	private boolean enabled, hidden;
	public List<Setting> settings;
	private final Component component = null;
	public int x = 10, y = 100, width, height = 15;

	public Module(String name, String description, Category category, boolean hidden, Setting... s) {
		this.name = name;
		this.shown = new BooleanSetting("Show in arraylist", true);
		this.description = description;
		settings = Arrays.asList(s);
		this.category = category;
		this.enabled = false;
		this.hidden = hidden;
	}

	public enum Category {
		COMBAT("Combat", Color.decode("#ff2e2e")),
		MOVEMENT("Movement", Color.decode("#edd628")),
		PLAYER("Player", Color.decode("#23e823")),
		RENDER("Render", Color.decode("#23e1e8")),
		EXPLOIT("Exploit", Color.decode("#df3beb")),
		WORLD("World", Color.decode("#58eb3b")),
		MISC("Misc", Color.decode("#526cff")),
		CLIENT("Client", Color.white),
		HUD("HUD", Color.white);

		private final String name;
		private final Color color;

		Category(String name, Color color) {
			this.name = name;
			this.color = color;
		}

		public String getName() {
			return name;
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

	public boolean isHidden() {
		return hidden;
	}
	
	public int getKey() {
		return key.code;
	}
	
	public void setKey(int key) {
		this.key.code = key;
	}

	public void toggle() {
		try {
			Bloomware.configManager.saveConfig(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (enabled) disable();
		else enable();
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void enable() {
		Bloomware.moduleNotifier.setMessage(this.name + " enabled!");
		Bloomware.chatNotifier.sendMsg(this.name + " enabled!");
		Bloomware.EVENTBUS.register(this);
		setEnabled(true);
		onEnable();
	}

	public void disable() {
		Bloomware.moduleNotifier.setMessage(this.name + " disabled!");
		Bloomware.chatNotifier.sendMsg(this.name + " disabled!");
		Bloomware.EVENTBUS.unregister(this);
		setEnabled(false);
		onDisable();
	}

	public void onEnable() {}

	public void onDisable() {}

	public void onTick() {}

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
}
