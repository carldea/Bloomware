package me.offeex.ofx.api.event.events;

import me.offeex.ofx.api.event.Event;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;

public class EventPlayerMove extends Event {
    public MovementType type;
    private Vec3d vec3d;

    public EventPlayerMove(MovementType type, Vec3d vec3d) {
        this.type = type;
        this.vec3d = vec3d;
    }

    public Vec3d getVec3d() {
        return vec3d;
    }

    public void setVec3d(Vec3d vec3d) {
        this.vec3d = vec3d;
    }
}
