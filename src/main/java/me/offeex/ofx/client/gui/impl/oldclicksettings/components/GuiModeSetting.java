package me.offeex.ofx.client.gui.impl.oldclicksettings.components;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.gui.api.AbstractButton;
import me.offeex.ofx.client.gui.api.ColorUtils;
import me.offeex.ofx.client.setting.settings.ModeSetting;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class GuiModeSetting extends AbstractButton {
    ModeSetting settingMode;
    public boolean isPressed;

    public GuiModeSetting(int x, int y, ModeSetting setting) {
        super(x, y, 100, 13);
        this.settingMode = setting;
    }

    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isPressed) {
            if (!isMouseWithin(mouseX, mouseY)) {
                isPressed = false;
            }
            Screen.fill(stack, x, y, x + width, y + height, new Color(0, 0, 0, 150).getRGB());
        }
        else if (isMouseInside(x, y, width, height, mouseX, mouseY)) {
            Screen.fill(stack, x, y, x + width, y + height, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY_DARKER, 120));
        }

        Bloomware.sFontRenderer.drawString("Mode  " + settingMode.getMode(), x + 2, y, ColorUtils.Colors.PRIMARY.getRGB(), true);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            isPressed = true;
            settingMode.cycle();
        }
    }
}
