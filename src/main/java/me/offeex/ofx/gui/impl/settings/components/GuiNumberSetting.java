package me.offeex.ofx.gui.impl.settings.components;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.AbstractButton;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.setting.settings.NumberSetting;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class GuiNumberSetting extends AbstractButton {
    NumberSetting numberSetting;
    public boolean isPressed;

    public GuiNumberSetting(int x, int y, NumberSetting setting) {
        super(x, y, 100, 13);
        this.numberSetting = setting;
    }

    private int percentCounter(double maximum, double current) {
        return (int) (current * 100 / maximum);
    }
    private double percentToNumber(double maximum, int percent) {
        return maximum * percent / 100;
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        isPressed = true;
        numberSetting.setValue(percentToNumber(numberSetting.getMaximum(), percentCounter(100, mouseX - x)));
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isPressed) {
            if (!isMouseWithin(mouseX, mouseY)) {
                isPressed = false;
            } else {
                if (mouseX >= x && mouseX <= x + width) {
                    Screen.fill(stack, x, y, mouseX, y + height - 2, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY_DARKER, 120));
                    numberSetting.setValue(percentToNumber(numberSetting.getMaximum(), percentCounter(100, mouseX - x)));
                }
            }
        } else {
            Screen.fill(stack, x, y, x + percentCounter(numberSetting.getMaximum(), numberSetting.getValue()), y + height - 2, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY_DARKER, 120));
        }
        Main.sFontRenderer.drawString(numberSetting.getName() + "  " + numberSetting.getValue(), x + 2, y, ColorUtils.Colors.PRIMARY.getRGB(), true);
    }
}
