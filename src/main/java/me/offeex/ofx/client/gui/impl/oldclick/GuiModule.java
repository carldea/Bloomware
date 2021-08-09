package me.offeex.ofx.client.gui.impl.oldclick;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.gui.api.AbstractButton;
import me.offeex.ofx.client.gui.api.AbstractDraggable;
import me.offeex.ofx.client.gui.api.ColorUtils;
import me.offeex.ofx.client.gui.impl.oldclicksettings.SettingWindow;
import me.offeex.ofx.client.module.Module;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

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
            int width = Bloomware.sFontRenderer.getStringWidth(module.getDescription(), Bloomware.sFontRenderer.getFontsize()) + 8;
            Screen.fill(stack, mouseX, mouseY, mouseX + width, mouseY + 14, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 120));
            Bloomware.sFontRenderer.drawString(module.getDescription(), mouseX + 2, mouseY + 2, new Color(215, 215, 215).getRGB(), true);
        }

        if (module.isEnabled()) {
            Screen.fill(stack, x + 1, y + 1, x + width - 1, y + height - 1, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY_DARKER, 120));
            Bloomware.sFontRenderer.drawString(module.getName(), x + 2, y + 4, new Color(215, 215, 215).getRGB(), true);
        } else {
            Bloomware.sFontRenderer.drawString(module.getName(), x + 2, y + 4, ColorUtils.withTransparency(ColorUtils.Colors.GRAY, 255), true);
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
            int count = 0;

            for (AbstractDraggable panel : Bloomware.oldGui.panels) {
                if (panel instanceof SettingWindow) {
                    count++;
                }
            }

            if (Bloomware.oldGui.panels.get(Bloomware.oldGui.panels.size() - 1) instanceof SettingWindow) {
                Bloomware.oldGui.panels.remove(Bloomware.oldGui.panels.get(Bloomware.oldGui.panels.size() - 1));
            }

            boolean isExist = false;
            for (AbstractDraggable panel : Bloomware.oldGui.panels) {
                if (panel instanceof SettingWindow) {
                    if (((SettingWindow) panel).getModule() == this.module) {
                        isExist = true;
                        break;
                    }
                }
            }
            if (!isExist && count == 0) {
                Bloomware.oldGui.panels.add(new SettingWindow(module, (int) mouseX + 50, (int) mouseY, Bloomware.oldGui.panels.size()));
            }
        }
    }
}
