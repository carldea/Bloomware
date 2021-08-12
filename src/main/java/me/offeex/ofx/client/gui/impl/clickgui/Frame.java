package me.offeex.ofx.client.gui.impl.clickgui;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.util.ColorUtils;
import me.offeex.ofx.client.gui.impl.clickgui.component.Component;
import me.offeex.ofx.client.gui.impl.clickgui.component.components.ModuleButton;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.util.ArrayList;

public class Frame implements IDraggable {

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
        if (isDragging)
            DrawableHelper.fill(new MatrixStack(), x, y, x + width, y + barHeight, new Color(50, 50, 50, 200).getRGB());
        else
            DrawableHelper.fill(new MatrixStack(), x, y, x + width, y + barHeight, ColorUtils.Colors.SECONDARY.getRGB());
        Bloomware.sFontRenderer.drawString(category.getName(), x + width / 2f - Bloomware.sFontRenderer.getStringWidth(category.getName(), Bloomware.sFontRenderer.getFontsize()) / 2f, y + 4, ColorUtils.getTextColor().getRGB(), true);
        if (open && !components.isEmpty()) {
            // DrawableHelper.fill(new MatrixStack(), x, y + height, x + width, y + height + 7, ColorUtils.Colors.PRIMARY.getRGB());
            components.forEach(Component::render);
            DrawableHelper.fill(new MatrixStack(), this.x, y + 16, this.x + 1, y + height, ColorUtils.getGuiColor().getRGB());
            //DrawableHelper.fill(new MatrixStack(), this.x + width, y + height - 5, this.x + width - 1, y + height, ColorUtils.getGuiColor().getRGB());
            DrawableHelper.fill(new MatrixStack(), this.x, y + height, this.x + width, y + height + 1, ColorUtils.getGuiColor().getRGB());
            DrawableHelper.fill(new MatrixStack(), this.x, y + barHeight - 1, this.x + width, y + barHeight, ColorUtils.getGuiColor().getRGB());
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
        if (drag && ClickGUI.dragging == null) {
            ClickGUI.dragging = this;
            this.isDragging = true;
        }
        else {
            if (ClickGUI.dragging == this)
                ClickGUI.dragging = null;
            this.isDragging = false;
        }
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
            this.setX(Math.max(0, Math.min(mc.getWindow().getScaledWidth() - this.width, mouseX - dragX)));
            this.setY(Math.max(0, Math.min(mc.getWindow().getScaledHeight() - this.height, mouseY - dragY)));
        }
    }

    public boolean isHover(final double x, final double y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
    }

}
