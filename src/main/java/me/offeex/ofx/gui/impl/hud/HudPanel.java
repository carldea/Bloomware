package me.offeex.ofx.gui.impl.hud;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.AbstractDraggable;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.gui.impl.hud.component.Component;
import me.offeex.ofx.gui.impl.hud.component.ComponentManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.util.ArrayList;

public class HudPanel extends AbstractDraggable {

    ArrayList<HudButton> components;

    public HudPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
        components = new ArrayList<>();

        int offsetY = 14;
        for (Component component : ComponentManager.getComponents()) {
            components.add(new HudButton(x, y + offsetY, component, this));
            offsetY += 14;
        }
        this.height = offsetY;
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (this.dragging)
            Screen.fill(stack, x, y, x + width, y + height, new Color(80, 80, 80, 200).getRGB());
        else
            Screen.fill(stack, x, y, x + width, y + height, new Color(0, 0, 0, 150).getRGB());

        Screen.fill(stack, x, y, x + width, y + 14, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 200));
        Screen.fill(stack, x, y + 13, x + width, y + 14, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY, 120));
        Main.pFontRenderer.drawString("Components", x + (width / 2f - Main.pFontRenderer.getStringWidth("Components", Main.pFontRenderer.getFontsize()) / 2f), y + 2, ColorUtils.Colors.PRIMARY.getRGB(), true);
        int offsetY = 14;
        for (HudButton module : components) {
            module.x = this.x;
            module.y = this.y + offsetY;
            module.draw(stack, mouseX, mouseY, tickDelta);
            offsetY += 14;
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            for (HudButton component : components) {
                if (component.isMouseWithin(mouseX, mouseY)) {
                    component.mouseClicked(mouseX, mouseY, mouseButton);
                    return;
                }
            }
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
        for (HudButton module : components) {
            module.isPressed = false;
        }
    }
}
