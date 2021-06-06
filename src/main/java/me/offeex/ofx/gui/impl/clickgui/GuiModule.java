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
//    GuiSettings setting;
    public boolean isPressed;

    public GuiModule(int x, int y, Module module, GuiPanel panel) {
        super(x, y, panel.width, 13);
        this.module = module;
        this.panel = panel;
//        setting = new GuiSettings(this, x, y);
    }

    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isPressed) {
            if (!isMouseWithin(mouseX, mouseY)) isPressed = false;
            Screen.fill(stack, x, y, x + width, y + height, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 255));
        }
        else if (isMouseWithin(mouseX, mouseY)) {
            Screen.fill(stack, x, y, x + width, y + height, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY_DARKER, 120));
        }

        if (module.isEnabled())
            Main.sFontRenderer.drawString(module.getName(), x + 2, y + 2, ColorUtils.Colors.PRIMARY.getRGB(), true);
        else
            Main.sFontRenderer.drawString(module.getName(), x + 2, y + 2, new Color(215, 215, 215).getRGB(), true);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) { //Проверка на ЛКМ
            if (this.isMouseWithin(mouseX, mouseY) && !panel.isMinimized) {
                isPressed = true;
                module.toggle();
            }
        }
        if (mouseButton == 1) { //Проверка на ПКМ
            Iterator<AbstractDraggable> i = GuiScreen.getInstance().panels.iterator();
            while (i.hasNext()) {
                AbstractDraggable ad = i.next();
                if (ad instanceof SettingWindow) {
                    SettingWindow sw = (SettingWindow) ad;
                    if (!sw.isPinned())
                        i.remove();
                }
            }
            SettingWindow sw = new SettingWindow(module, (int) mouseX, (int) mouseY);
            GuiScreen.getInstance().panels.add(sw);
        }

    }
}
