package me.offeex.ofx.gui.impl.hud.component;

import me.offeex.ofx.Main;
import me.offeex.ofx.api.event.events.EventDrawOverlay;
import me.offeex.ofx.gui.api.AbstractDraggable;
import me.offeex.ofx.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Component extends AbstractDraggable {
	private final Module module;

	public Component(Module module, int x, int y, int width, int height) {
		super(x + 10, y, width, height);
		this.module = module;
		Main.EVENTBUS.subscribe(listener);
	}

	@EventHandler
	private final Listener<EventDrawOverlay> listener = new Listener<>(e -> {
		this.draw(e.matrix, x, y, MinecraftClient.getInstance().getTickDelta());
	});

	@Override
	public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
		if (module.isEnabled() && module.getCategory().equals(Module.Category.HUD))
			module.draw(stack, mouseX, mouseY, tickDelta);
	}

	@Override
	public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
//		module.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
//		module.mouseReleased(mouseX, mouseY, mouseButton);
	}

	public Module getModule() {
		return module;
	}
}
