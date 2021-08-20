package me.offeex.bloomware.client.module;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Module {
	
	protected static final MinecraftClient mc = MinecraftClient.getInstance();
	public String name, description;
	public double version;
	public int key;
	public Setting<Boolean> shown;
	private final Category category;
	private boolean enabled;
	private final boolean hidden;
	public int x = 10, y = 100, width, height = 15;

	public Module(String name, String description, Category category, boolean hidden) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.enabled = false;
		this.shown = register("Drawn", hidden);
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
		return shown.getValue();
	}
	
	public int getKey() {
		return key;
	}
	
	public void setKey(int key) {
		this.key = key;
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

	public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	protected Setting<Number> register(final String name, final double value, final double min, final double max, final double inc) {
		final Setting<Number> s = new Setting<>(name, this, value, min, max, inc);
		Bloomware.settingManager.add(s);
		return s;
	}

	protected Setting<Boolean> register(final String name, final boolean value) {
		final Setting<Boolean> s = new Setting<>(name, this, value);
		Bloomware.settingManager.add(s);
		return s;
	}

	protected Setting<String> register(final String name, final List<String> modes, final String value) {
		final Setting<String> s = new Setting<>(name, this, modes, value);
		Bloomware.settingManager.add(s);
		return s;
	}
}
