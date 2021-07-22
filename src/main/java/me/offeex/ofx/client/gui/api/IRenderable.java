package me.offeex.ofx.client.gui.api;

import net.minecraft.client.util.math.MatrixStack;

public interface IRenderable {

	void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta);
	
}
