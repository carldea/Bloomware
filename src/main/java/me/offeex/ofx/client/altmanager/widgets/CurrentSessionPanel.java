package me.offeex.ofx.client.altmanager.widgets;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.gui.api.AbstractDraggable;
import me.offeex.ofx.client.gui.api.ColorUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.awt.*;

public class CurrentSessionPanel extends AbstractDraggable {
    MinecraftClient mc = MinecraftClient.getInstance();
    boolean isMinimized;

    public CurrentSessionPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        int totalHeight = this.height;
        if (isMinimized) {
            totalHeight = 13;
        }
        if (Bloomware.guiscreen.getDragging() == this) {
            Screen.fill(stack, x, y, x + width, y + totalHeight, new Color(80, 80, 80, 200).getRGB());
        } else {
            Screen.fill(stack, x, y, x + width, y + totalHeight, new Color(0, 0, 0, 150).getRGB());
        }

        Screen.fill(stack, x, y, x + width, y + 14, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 200));
        Screen.fill(stack, x, y + 13, x + width, y + 14, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY, 120));

        Bloomware.pFontRenderer.drawString("Current session", x + (width / 2f - Bloomware.pFontRenderer.getStringWidth("Current session", 18) / 2f), y + 3, ColorUtils.Colors.PRIMARY.getRGB(), true);

        if (!isMinimized) {
            Identifier minimized = new Identifier("ofx", "icons/notminimized.png");
            mc.getTextureManager().bindTexture(minimized);
            DrawableHelper.drawTexture(stack, x + width - 10, y + 2, 0, 0, 0, 8, 8, 8, 8);
            Bloomware.sFontRenderer.drawString("Logged in " + mc.getSession().getUsername(), x + 2, y + 14, ColorUtils.Colors.WHITE.getRGB(), true);
        } else {
            Identifier minimized = new Identifier("ofx", "icons/minimized.png");
            mc.getTextureManager().bindTexture(minimized);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 1 && isMouseInside(x, y, width, 13, mouseX, mouseY)) {
            isMinimized = !isMinimized;
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int mouseButton) {

    }
}
