package me.offeex.bloomware.api.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import java.util.Objects;

public class Vector {
    public double x, y, z;

    public Vector() {}

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    public Vector set(Vector vec) {
        x = vec.x;
        y = vec.y;
        z = vec.z;

        return this;
    }

    public Vector set(Entity entity, double tickDelta) {
        x = MathHelper.lerp(tickDelta, entity.lastRenderX, entity.getX());
        y = MathHelper.lerp(tickDelta, entity.lastRenderY, entity.getY());
        z = MathHelper.lerp(tickDelta, entity.lastRenderZ, entity.getZ());

        return this;
    }

    public Vector add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    public Vector add(Vector vec) {
        return add(vec.x, vec.y, vec.z);
    }

    public Vector subtract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    public Vector subtract(Vector vec) {
        return subtract(vec.x, vec.y, vec.z);
    }

    public Vector multiply(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }

    public Vector multiply(double v) {
        return multiply(v, v, v);
    }

    public Vector divide(double v) {
        x /= v;
        y /= v;
        z /= v;

        return this;
    }

    public void negate() {
        x = -x;
        y = -y;
        z = -z;
    }

    public double distanceTo(Vector vec) {
        double d = vec.x - x;
        double e = vec.y - y;
        double f = vec.z - z;

        return Math.sqrt(d * d + e * e + f * f);
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector normalize() {
        return divide(length());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector Vector = (Vector) o;
        return Double.compare(Vector.x, x) == 0 && Double.compare(Vector.y, y) == 0 && Double.compare(Vector.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return String.format("[%.3f, %.3f, %.3f]", x, y, z);
    }
}
