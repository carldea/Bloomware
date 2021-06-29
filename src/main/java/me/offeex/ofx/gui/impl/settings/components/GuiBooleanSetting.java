package me.offeex.ofx.gui.impl.settings.components;

import me.offeex.ofx.Main;
import me.offeex.ofx.command.CommandManager;
import me.offeex.ofx.gui.api.AbstractButton;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.setting.settings.BooleanSetting;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class GuiBooleanSetting extends AbstractButton {
    BooleanSetting booleanSetting;
    public boolean isPressed;

    public GuiBooleanSetting(int x, int y, BooleanSetting setting) {
        super(x, y, 100, 13);
        this.booleanSetting = setting;
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            isPressed = true;
            booleanSetting.toggle();
        }
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isPressed) {
            if (!isMouseWithin(mouseX, mouseY)) isPressed = false;
            Screen.fill(stack, x, y, x + width, y + height, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 255));
        }
        else if (isMouseInside(x, y, width, height, mouseX, mouseY)) {
            Screen.fill(stack, x, y, x + width, y + height, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY_DARKER, 120));
        }

        if (booleanSetting.isEnabled()) {
            Main.sFontRenderer.drawString(booleanSetting.getName(), x + 2, y, ColorUtils.Colors.PRIMARY.getRGB(), true);
        } else {
            Main.sFontRenderer.drawString(booleanSetting.getName(), x + 2, y, ColorUtils.Colors.WHITE.getRGB(), true);
        }
    }
}
