package me.offeex.ofx.api.util;

import me.offeex.ofx.mixins.Vector3D;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class PlayerDataUtil {
    public static MinecraftClient mc;
    private static final double diagonal = 1 / Math.sqrt(2);
    private static final Vec3d horizontalVelocity = new Vec3d(0, 0, 0);

    public static Vec3d getHorizontalVelocity(double bps) {
        assert mc.player != null;
        float yaw = mc.player.getYaw(1);

        Vec3d forward = Vec3d.fromPolar(0, yaw);
        Vec3d right = Vec3d.fromPolar(0, yaw + 90);

        double velX = 0;
        double velZ = 0;
        assert mc.player != null;

        boolean a = false;
        if (mc.player.input.pressingForward) {
            velX += forward.x / 20 * bps;
            velZ += forward.z / 20 * bps;
            a = true;
        }
        if (mc.player.input.pressingBack) {
            velX -= forward.x / 20 * bps;
            velZ -= forward.z / 20 * bps;
            a = true;
        }

        boolean b = false;
        if (mc.player.input.pressingRight) {
            velX += right.x / 20 * bps;
            velZ += right.z / 20 * bps;
            b = true;
        }
        if (mc.player.input.pressingLeft) {
            velX -= right.x / 20 * bps;
            velZ -= right.z / 20 * bps;
            b = true;
        }

        if (a && b) {
            velX *= diagonal;
            velZ *= diagonal;
        }

        ((Vector3D) horizontalVelocity).setXZ(velX, velZ);
        return horizontalVelocity;
    }
}
