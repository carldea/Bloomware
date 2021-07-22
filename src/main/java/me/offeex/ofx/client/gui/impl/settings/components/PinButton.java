package me.offeex.ofx.client.gui.impl.settings.components;

import me.offeex.ofx.client.gui.api.AbstractButton;
import me.offeex.ofx.client.gui.impl.settings.SettingWindow;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PinButton extends AbstractButton {
    public SettingWindow panel;
    public boolean isPressed;

    MinecraftClient mc = MinecraftClient.getInstance();

    Identifier pin = new Identifier("ofx", "icons/pin.png");
    Identifier pinGreen = new Identifier("ofx", "icons/pinned.png");

    public PinButton(int x, int y, SettingWindow p) {
        super(x, y, 10, 10);
        this.panel = p;
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            isPressed = true;
            panel.pinned = !panel.pinned;
        }
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isPressed) {
            if (!isMouseWithin(mouseX, mouseY)) isPressed = false;
        }

        if (this.panel.isPinned()) {
            mc.getTextureManager().bindTexture(pinGreen);
        } else {
            mc.getTextureManager().bindTexture(pin);
        }
        DrawableHelper.drawTexture(stack, x, y, 0, 0, 0, 10, 10, 10, 10);
    }
}
