package me.offeex.ofx.gui.impl;

import me.offeex.ofx.gui.api.AbstractDraggable;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.setting.Setting;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class GuiSettings extends AbstractDraggable {

    Setting setting;
    private boolean isOpen = true;
    private final GuiModule module;

    public GuiSettings(GuiModule module, int x, int y) {
        super(x, y, 200, 0);
        this.module = module;
        module.module.settings.forEach(set -> {

        });
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isOpen) {
            Screen.fill(stack, x + 50, y + 50, x + width - 10, y + height - 10, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 255));
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        isOpen = false;
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int mouseButton) {

    }
}
