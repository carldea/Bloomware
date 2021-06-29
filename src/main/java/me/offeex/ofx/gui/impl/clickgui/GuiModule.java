package me.offeex.ofx.gui.impl.clickgui;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.AbstractButton;
import me.offeex.ofx.gui.api.AbstractDraggable;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.gui.impl.settings.SettingWindow;
import me.offeex.ofx.module.Module;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.util.Iterator;

public class GuiModule extends AbstractButton {
    Module module;
    GuiPanel panel;
    public boolean isPressed;

    public GuiModule(int x, int y, Module module, GuiPanel panel) {
        super(x, y, panel.width, 17);
        this.module = module;
        this.panel = panel;
    }

    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isPressed) {
            if (!isMouseWithin(mouseX, mouseY)) isPressed = false;
            Screen.fill(stack, x, y, x + width, y + height, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 255));
        }
        else if (isMouseWithin(mouseX, mouseY)) {
            Screen.fill(stack, x + 1, y + 1, x + width - 1, y + height - 1, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY_DARKER, 120));
            // Module description
            int width = Main.sFontRenderer.getStringWidth(module.getDescription(), Main.sFontRenderer.getFontsize()) + 8;
            Screen.fill(stack, mouseX, mouseY, mouseX + width, mouseY + 14, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 120));
            Main.sFontRenderer.drawString(module.getDescription(), mouseX + 2, mouseY + 2, new Color(215, 215, 215).getRGB(), true);
        }

        if (module.isEnabled()) {
            Screen.fill(stack, x + 1, y + 1, x + width - 1, y + height - 1, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY_DARKER, 120));
            Main.sFontRenderer.drawString(module.getName(), x + 2, y + 4, new Color(215, 215, 215).getRGB(), true);
        } else {
            Main.sFontRenderer.drawString(module.getName(), x + 2, y + 4, ColorUtils.withTransparency(ColorUtils.Colors.GRAY, 255), true);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            if (this.isMouseWithin(mouseX, mouseY) && !panel.isMinimized) {
                isPressed = true;
                module.toggle();
            }
        }
        if (mouseButton == 1) {
            Iterator<AbstractDraggable> i = Main.guiscreen.panels.iterator();
            while (i.hasNext()) {
                AbstractDraggable ad = i.next();
                if (ad instanceof SettingWindow) {
                    SettingWindow sw = (SettingWindow) ad;
                    if (!sw.isPinned())
                        i.remove();
                }
            }
            SettingWindow sw = new SettingWindow(module, (int) mouseX, (int) mouseY, Main.guiscreen.panels.size() - 1);
            Main.guiscreen.panels.add(sw);
            sw.startDragging(mouseX, mouseY, mouseButton);
        }
    }
}
