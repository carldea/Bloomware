package me.offeex.ofx.hud.component;

import me.offeex.ofx.Main;
import me.offeex.ofx.api.event.events.EventDrawOverlay;
import me.offeex.ofx.gui.api.AbstractDraggable;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.setting.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Component extends AbstractDraggable {
	private final String name;
	private final String description;
	private boolean enabled;

	public List<Setting> settings = new ArrayList<Setting>();
	MinecraftClient mc = MinecraftClient.getInstance();

	public Component(String name, String description, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.name = name;
		this.description = description;
		this.enabled = false;
		Main.EVENTBUS.subscribe(listener);
	}

	public void addSettings(Setting... settings) {
		this.settings.addAll(Arrays.asList(settings));
//		this.settings.sort(Comparator.comparingInt(s -> s == keyCode ? 1 : 0));
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isEnabled() {
		return enabled;
	}
	public void enable() {
		setEnabled(true);
	}
	public void disable() {
		setEnabled(false);
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;

		if(Main.saveLoad != null) {
			Main.saveLoad.save();
		}
	}
	public void toggle() {
		enabled = !enabled;

		if(Main.saveLoad != null) {
			Main.saveLoad.save();
		}
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@EventHandler
	private final Listener<EventDrawOverlay> listener = new Listener<>(e -> {
		this.draw(e.matrix, x, y, MinecraftClient.getInstance().getTickDelta());
	});

	@Override
	public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
	}

	@Override
	public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
			startDragging(mouseX, mouseY, mouseButton);
	}

	@Override
	public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
		stopDragging(mouseX, mouseY, mouseButton);
	}
}
