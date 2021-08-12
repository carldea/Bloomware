package me.offeex.bloomware.api.event.events;

import me.offeex.bloomware.api.event.Event;

public class EventWorldRender extends Event {

	public final float partialTicks;

	public EventWorldRender(float partialTicks) {
		this.partialTicks = partialTicks;
	}
}