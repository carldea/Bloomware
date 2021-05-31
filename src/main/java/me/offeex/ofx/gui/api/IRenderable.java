package me.offeex.ofx.gui.api;

import net.minecraft.client.util.math.MatrixStack;

/**
 * 
 * @author Gav06
 * 
 * @since 5/4/2021
 */
public interface IRenderable {

	void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta);
	
}
