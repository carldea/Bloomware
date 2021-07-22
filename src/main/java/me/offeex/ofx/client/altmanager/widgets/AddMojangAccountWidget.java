package me.offeex.ofx.client.altmanager.widgets;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.altmanager.screens.AddMojangAccount;
import me.offeex.ofx.client.gui.api.AbstractButton;
import me.offeex.ofx.client.gui.api.ColorUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class AddMojangAccountWidget extends AbstractButton {
    MinecraftClient mc = MinecraftClient.getInstance();
    boolean isPressed;

    public AddMojangAccountWidget(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            mc.openScreen(new AddMojangAccount());
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
        Bloomware.sFontRenderer.drawString("New Mojang Account", x + 1, y + 1, ColorUtils.Colors.WHITE.getRGB(), true);
    }
}
