package me.offeex.ofx.client.gui.api;

/**
 * 
 * @author Gav06
 * 
 * @since 5/4/2021
 */
public abstract class AbstractComponent implements IClickable {
	
	public int x, y, width, height;
	
	public AbstractComponent(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public abstract void mouseClicked(double mouseX, double mouseY, int mouseButton);

	public boolean isMouseWithin(double mouseX, double mouseY) {
		return isMouseInside(x, y, width, height, mouseX, mouseY);
	}
}
