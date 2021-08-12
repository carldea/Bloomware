package me.offeex.bloomware.api.event.events;

import me.offeex.bloomware.api.event.Event;
import net.minecraft.entity.vehicle.BoatEntity;

public class EventBoatMove extends Event {
    private static final EventBoatMove INSTANCE = new EventBoatMove();

    public BoatEntity boatEntity;

    public static EventBoatMove get(BoatEntity entity) {
        INSTANCE.boatEntity = entity;
        return INSTANCE;
    }
}
