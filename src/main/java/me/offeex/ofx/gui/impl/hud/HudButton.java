package me.offeex.ofx.gui.impl.hud;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.AbstractButton;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.gui.impl.hud.component.Component;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class HudButton extends AbstractButton {
    Component component;
    HudPanel panel;
    public boolean isPressed;

    public HudButton(int x, int y, Component component, HudPanel panel) {
        super(x, y, panel.width, 14);
        this.component = component;
        this.panel = panel;
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isPressed) {
            if (!isMouseWithin(mouseX, mouseY)) isPressed = false;
            Screen.fill(stack, x, y, x + width, y + height, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 255));
        }
        else if (isMouseWithin(mouseX, mouseY)) {
            Screen.fill(stack, x, y, x + width, y + height, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY_DARKER, 120));
        }

        if (component.isEnabled()) {
            Main.sFontRenderer.drawString(component.getName(), x + 2, y + 2, ColorUtils.Colors.PRIMARY.getRGB(), true);
        }
        else
            Main.sFontRenderer.drawString(component.getName(), x + 2, y + 2, new Color(215, 215, 215).getRGB(), true);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        isPressed = true;
        component.toggle();
    }
}
