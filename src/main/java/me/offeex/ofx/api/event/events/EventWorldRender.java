package me.offeex.ofx.api.event.events;

import me.offeex.ofx.api.event.Event;

public class EventWorldRender extends Event {

	public final float partialTicks;

	public EventWorldRender(float partialTicks) {
		this.partialTicks = partialTicks;
	}
}