package me.offeex.ofx.client.gui.impl.hud.element;

import com.google.common.eventbus.Subscribe;
import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.event.events.EventDrawOverlay;
import me.offeex.ofx.api.util.ColorUtils;
import me.offeex.ofx.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class Element{
	private final Module module;
	private boolean isDragging;
	private int x;
	private int y;
	public int dragX;
	public int dragY;
	public int width;
	public int height;
	MinecraftClient mc = MinecraftClient.getInstance();

	public Element(Module module) {
		this.module = module;
		this.isDragging = false;
		Bloomware.EVENTBUS.register(this);
	}
//
	@Subscribe
	public void onDrawOverlay(EventDrawOverlay e) {
		this.draw(e.matrix, (int) MinecraftClient.getInstance().mouse.getX(), (int) MinecraftClient.getInstance().mouse.getY(), MinecraftClient.getInstance().getTickDelta());
	}

	public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
		module.x = x;
		module.y = y;
		width = module.width;
		height = module.height;
		if (module.isEnabled()) {
			module.draw(stack, mouseX, mouseY, tickDelta);
		}

		if (mc.currentScreen == Bloomware.hudEditor && module.isEnabled()) {
			DrawableHelper.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
		}
	}

	public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
	}

	public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
	}

	public void updatePosition(final int mouseX, final int mouseY) {
		if (this.isDragging) {
			this.x = (mouseX - this.dragX);
			this.y = (mouseY - this.dragY);
		}
	}

	public void setDrag(final boolean drag) {
		this.isDragging = drag;
	}

	public boolean isHover(final double x, final double y) {
		return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
	}

	public Module getModule() {
		return module;
	}
}