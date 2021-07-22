package me.offeex.ofx.mixins;

import me.offeex.ofx.api.util.Vector;
import net.minecraft.util.math.Vec3i;

public interface Vector3D {
    void set(double x, double y, double z);

    default void set(Vec3i vec) {
        set(vec.getX(), vec.getY(), vec.getZ());
    }
    default void set(Vector vec) {
        set(vec.x, vec.y, vec.z);
    }
    void setXZ(double x, double z);

    void setY(double y);
}
