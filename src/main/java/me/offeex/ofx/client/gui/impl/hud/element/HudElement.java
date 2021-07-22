package me.offeex.ofx.client.gui.impl.hud.element;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.event.events.EventDrawOverlay;
import me.offeex.ofx.client.gui.api.AbstractDraggable;
import me.offeex.ofx.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class HudElement extends AbstractDraggable {
	private final Module module;

	public HudElement(Module module, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.module = module;
		Bloomware.EVENTBUS.subscribe(listener);
	}

	@EventHandler
	private final Listener<EventDrawOverlay> listener = new Listener<>(e -> {
		this.draw(e.matrix, (int) MinecraftClient.getInstance().mouse.getX(), (int) MinecraftClient.getInstance().mouse.getY(), MinecraftClient.getInstance().getTickDelta());
	});

	@Override
	public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
		module.x = x;
		module.y = y;
		width = module.width;
		height = module.height;
		if (module.isEnabled()) {
			module.draw(stack, mouseX, mouseY, tickDelta);
		}
	}

	@Override
	public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
	}

	@Override
	public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
	}

	public Module getModule() {
		return module;
	}
}
