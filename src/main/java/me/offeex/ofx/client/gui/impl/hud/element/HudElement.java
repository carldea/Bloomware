package me.offeex.ofx.client.gui.impl.hud.element;

import com.google.common.eventbus.Subscribe;
import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.event.events.EventDrawOverlay;
import me.offeex.ofx.client.gui.api.AbstractDraggable;
import me.offeex.ofx.client.gui.api.ColorUtils;
import me.offeex.ofx.client.gui.impl.clickgui.GuiScreen;
import me.offeex.ofx.client.gui.impl.clickgui.HudScreen;
import me.offeex.ofx.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class HudElement extends AbstractDraggable {
	private final Module module;

	public HudElement(Module module, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.module = module;
		Bloomware.EVENTBUS.register(this);
	}

	@Subscribe
	public void onDrawOverlay(EventDrawOverlay e) {
		this.draw(e.matrix, (int) MinecraftClient.getInstance().mouse.getX(), (int) MinecraftClient.getInstance().mouse.getY(), MinecraftClient.getInstance().getTickDelta());
	}

	@Override
	public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
		module.x = x;
		module.y = y;
		width = module.width;
		height = module.height;
		if (module.isEnabled() && !(mc.currentScreen instanceof GuiScreen)) {
			module.draw(stack, mouseX, mouseY, tickDelta);
		}
		if (mc.currentScreen instanceof HudScreen && module.isEnabled()) {
			Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
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
