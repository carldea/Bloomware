package me.offeex.ofx.gui.impl.clickgui;

import me.offeex.ofx.gui.api.ColorUtils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Random;

public class Particle {
    private int x, y, size, speed;
    Random random = new Random();

    public Particle(int _x, int _y, int _size, int _speed) {
        x = _x; y = _y; size = _size; speed = _speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setX(int _x) {
        this.x = _x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(MatrixStack stack) {
        Screen.fill(stack, x, y, x + size, y + size, ColorUtils.Colors.WHITE.getRGB());
        setY(y + speed);
        setX(x + random.nextInt(5));
    }
}
