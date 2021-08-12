package me.offeex.bloomware.client.gui.api;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public abstract class AbstractDraggable extends AbstractComponent implements IRenderable {

	protected int dragX, dragY;
	public MinecraftClient mc = MinecraftClient.getInstance();
	
	public AbstractDraggable(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public abstract void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta);

	@Override
	public abstract void mouseClicked(double mouseX, double mouseY, int mouseButton);
	
	public abstract void mouseReleased(double mouseX, double mouseY, int mouseButton);
	
	public void startDragging(double mouseX, double mouseY, int mouseButton) {
		this.dragX = (int) mouseX - this.x;
		this.dragY = (int) mouseY - this.y;
	}
	
	// this should be called on render
	public void updateDragLogic(int mouseX, int mouseY) {
		this.x = Math.max(0, (mouseX - dragX));
		//this.x = Math.min(mc.getWindow().getScaledWidth() - this.width, this.x);

		this.y = Math.max(0, (mouseY - dragY));
		//this.y = Math.min(mc.getWindow().getScaledHeight() - this.height, this.y);
	}
}
