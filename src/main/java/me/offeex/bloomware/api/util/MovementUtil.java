package me.offeex.bloomware.api.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffect;

public final class MovementUtil
{
    public static double x = MinecraftClient.getInstance().player.getX();
    public static double y = MinecraftClient.getInstance().player.getY();
    public static double z = MinecraftClient.getInstance().player.getZ();

    public static String stringX = String.format("%.1f",x);
    public static String stringY = String.format("%.1f",y);
    public static String stringZ = String.format("%.1f",z);

    public static float getSpeed() {
        return (float)Math.sqrt(x * x + z * z);
    }
    
    public static void strafe() {
        strafe(getSpeed());
    }
    
    public static boolean isMoving() {
        return MinecraftClient.getInstance().player != null && (MinecraftClient.getInstance().player.input.movementForward != 0.0f || MinecraftClient.getInstance().player.input.movementSideways != 0.0f);
    }
    
    public static boolean hasMotion() {
        return x != 0.0 && z != 0.0 && y != 0.0;
    }
    
    public static void strafe(final float speed) {
        if (!isMoving()) {
            return;
        }
        final double yaw = getDirection();
        x = -Math.sin(yaw) * speed;
        z = Math.cos(yaw) * speed;
    }
    
    public static void forward(final double length) {
        final double yaw = Math.toRadians(MinecraftClient.getInstance().player.yaw);
        MinecraftClient.getInstance().player.setPos(x + -Math.sin(yaw) * length, y, z + Math.cos(yaw) * length);
    }
    
    public static double[] forward2(final double speed) {
        float forward = MinecraftClient.getInstance().player.input.movementForward;
        float side = MinecraftClient.getInstance().player.input.movementSideways;
        float yaw = MinecraftClient.getInstance().player.prevYaw + (MinecraftClient.getInstance().player.yaw - MinecraftClient.getInstance().player.prevYaw) * MinecraftClient.getInstance().getTickDelta();
        if (forward != 0.0f) {
            if (side > 0.0f) {
                yaw += ((forward > 0.0f) ? -45 : 45);
            }
            else if (side < 0.0f) {
                yaw += ((forward > 0.0f) ? 45 : -45);
            }
            side = 0.0f;
            if (forward > 0.0f) {
                forward = 1.0f;
            }
            else if (forward < 0.0f) {
                forward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(yaw + 90.0f));
        final double cos = Math.cos(Math.toRadians(yaw + 90.0f));
        final double posX = forward * speed * cos + side * speed * sin;
        final double posZ = forward * speed * sin - side * speed * cos;
        return new double[] { posX, posZ };
    }
    
    public static double getDirection() {
        float rotationYaw = MinecraftClient.getInstance().player.yaw;
        if (MinecraftClient.getInstance().player.forwardSpeed < 0.0f) {
            rotationYaw += 180.0f;
        }
        float forward = 1.0f;
        if (MinecraftClient.getInstance().player.forwardSpeed < 0.0f) {
            forward = -0.5f;
        }
        else if (MinecraftClient.getInstance().player.forwardSpeed > 0.0f) {
            forward = 0.5f;
        }
        if (MinecraftClient.getInstance().player.sidewaysSpeed > 0.0f) {
            rotationYaw -= 90.0f * forward;
        }
        if (MinecraftClient.getInstance().player.sidewaysSpeed < 0.0f) {
            rotationYaw += 90.0f * forward;
        }
        return Math.toRadians(rotationYaw);
    }
    
    public static double getBaseMoveSpeed() {
        double baseSpeed = 0.2873;
        if (MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().player.hasStatusEffect(StatusEffect.byRawId(1))) {
            final int amplifier = MinecraftClient.getInstance().player.getStatusEffect(StatusEffect.byRawId(1)).getAmplifier();
            baseSpeed *= 1.0 + 0.2 * (amplifier + 1);
        }
        return baseSpeed;
    }
    
    public static double getDirectionStrafe() {
        return Math.toRadians(MinecraftClient.getInstance().player.yaw);
    }
    
    public static double getSpeedStrafe() {
        return Math.sqrt(Math.pow(MovementUtil.x, 2.0) + Math.pow(MovementUtil.x, 2.0));
    }
    
    public static void setSpeedStrafe(final double speed) {
        MovementUtil.x = -Math.sin(getDirection()) * speed;
        MovementUtil.z = Math.cos(getDirection()) * speed;
    }
}
