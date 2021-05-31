package me.offeex.ofx.hud;

import me.offeex.ofx.Main;
import me.offeex.ofx.api.util.MovementUtil;
import me.offeex.ofx.gui.api.AbstractButton;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.hud.component.Component;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class HudComponent extends AbstractButton {
    Component component;
    HudPanel panel;
    public boolean isPressed;

    public HudComponent(int x, int y, Component component, HudPanel panel) {
        super(x, y, panel.width, 14);
        this.component = component;
        this.panel = panel;
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        isPressed = true;
        component.toggle();
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        String playerX = String.format("%.1f", MinecraftClient.getInstance().player.getX());
        String playerY = String.format("%.1f",MinecraftClient.getInstance().player.getY());
        String playerZ = String.format("%.1f",MinecraftClient.getInstance().player.getZ());

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
}
