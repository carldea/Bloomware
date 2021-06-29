package me.offeex.ofx.gui.api;

public interface IClickable {

	void mouseClicked(double mouseX, double mouseY, int mouseButton);
	
	default boolean isMouseInside(double x, double y, double width, double height, double mouseX, double mouseY) {
		return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
	}
}
