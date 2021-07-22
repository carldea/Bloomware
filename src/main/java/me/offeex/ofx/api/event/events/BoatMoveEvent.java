package me.offeex.ofx.api.event.events;

import me.offeex.ofx.api.event.Event;
import net.minecraft.entity.vehicle.BoatEntity;

public class BoatMoveEvent extends Event {
    private static final BoatMoveEvent INSTANCE = new BoatMoveEvent();

    public BoatEntity boatEntity;

    public static BoatMoveEvent get(BoatEntity entity) {
        INSTANCE.boatEntity = entity;
        return INSTANCE;
    }
}
