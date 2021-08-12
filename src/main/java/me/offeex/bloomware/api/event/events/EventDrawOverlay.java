package me.offeex.bloomware.api.event.events;

import me.offeex.bloomware.api.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public class EventDrawOverlay extends Event {

	public MatrixStack matrix;


	public EventDrawOverlay(MatrixStack matrix) {
		this.matrix = matrix;
	}
}