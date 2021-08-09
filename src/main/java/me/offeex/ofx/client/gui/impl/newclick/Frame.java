package me.offeex.ofx.client.gui.impl.newclick;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.gui.api.ColorUtils;
import me.offeex.ofx.client.gui.impl.newclick.component.Component;
import me.offeex.ofx.client.gui.impl.newclick.component.components.ModuleButton;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.util.ArrayList;

public class Frame {

    MinecraftClient mc = MinecraftClient.getInstance();

    public Module.Category category;
    public ArrayList<Component> components;
    private boolean open;
    private final int width;
    private int y;
    private int x;
    private final int barHeight;
    private boolean isDragging;
    public int dragX;
    public int dragY;
    private int height;

    public Frame(Module.Category category) {
        this.category = category;
        this.components = new ArrayList<>();
        this.width = 110;
        this.x = 5;
        this.y = 5;
        this.barHeight = 16;
        this.dragX = 0;
        this.open = true;
        this.isDragging = false;
        int componentY = this.barHeight;
        for (Module m : ModuleManager.getModulesByCategory(category)) {
            ModuleButton moduleButton = new ModuleButton(m, this, componentY);
            components.add(moduleButton);
            componentY += 12;
        }
        update();
    }

    public void renderFrame() {
        DrawableHelper.fill(new MatrixStack(), x, y, x + width, y + barHeight, ColorUtils.Colors.PRIMARY.getRGB());
        Bloomware.sFontRenderer.drawString(category.getName(), x + width / 2 - mc.textRenderer.getWidth(category.name()) / 2, y + 4, ColorUtils.Colors.WHITE.getRGB(), true);
        if (open && !components.isEmpty()) {
            // DrawableHelper.fill(new MatrixStack(), x, y + height, x + width, y + height + 7, ColorUtils.Colors.PRIMARY.getRGB());
            components.forEach(Component::render);
            DrawableHelper.fill(new MatrixStack(), this.x, y + height - 5, this.x + 2, y + height, ColorUtils.Colors.PRIMARY.getRGB());
            DrawableHelper.fill(new MatrixStack(), this.x + width, y + height - 5, this.x + width - 2, y + height, ColorUtils.Colors.PRIMARY.getRGB());
            DrawableHelper.fill(new MatrixStack(), this.x, y + height, this.x + width, y + height + 2, ColorUtils.Colors.PRIMARY.getRGB());
        }
    }

    public ArrayList<Component> getComponents() {
        return this.components;
    }

    public void setX(final int newX) {
        this.x = newX;
    }

    public void setY(final int newY) {
        this.y = newY;
    }

    public void setDrag(final boolean drag) {
        this.isDragging = drag;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(final boolean open) {
        this.open = open;
    }

    public void update() {
        int off = this.barHeight;
        for (final Component comp : this.components) {
            comp.setOffset(off);
            off += comp.getHeight();
        }
        this.height = off;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public void updatePosition(final int mouseX, final int mouseY) {
        if (this.isDragging) {
            this.setX(mouseX - this.dragX);
            this.setY(mouseY - this.dragY);
        }
    }

    public boolean isHover(final double x, final double y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
    }

}
