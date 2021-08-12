package me.offeex.bloomware.client.altmanager.widgets;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.altmanager.screens.AddTokenAccount;
import me.offeex.bloomware.client.gui.api.AbstractButton;
import me.offeex.bloomware.api.util.ColorUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class AddTokenAccountWidget extends AbstractButton {
    MinecraftClient mc = MinecraftClient.getInstance();
    boolean isPressed;

    public AddTokenAccountWidget(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            mc.openScreen(new AddTokenAccount());
        }
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isPressed) {
            if (!isMouseInside(x, y, width, height, mouseX, mouseY)) {
                isPressed = false;
            }
            Screen.fill(stack, x, y, x + width, y + height, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 255));
        }
        Bloomware.sFontRenderer.drawString("New Token Account", x + 1, y + 1, ColorUtils.Colors.WHITE.getRGB(), true);
    }
}
