package me.offeex.ofx.client.altmanager.widgets;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.gui.api.AbstractButton;
import me.offeex.ofx.client.gui.api.AbstractDraggable;
import me.offeex.ofx.client.gui.api.ColorUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.ArrayList;

public class ToolsPanel extends AbstractDraggable {
    ArrayList<AbstractButton> tools;
    MinecraftClient mc = MinecraftClient.getInstance();
    boolean isMinimized;

    public ToolsPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
        tools = new ArrayList<>();
        int offsetY = 13;
        tools.add(new AddCrackedAccountWidget(x, offsetY, 150, 17));
        tools.add(new AddMojangAccountWidget(x, offsetY, 150, 17));
        tools.add(new AddTokenAccountWidget(x, offsetY, 150, 17));
        this.height = tools.size() * 17 + 13;
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        int totalHeight = this.height;
        if (isMinimized)
            totalHeight = 13;
        if (Bloomware.guiscreen.getDragging() == this)
            Screen.fill(stack, x, y, x + width, y + totalHeight, new Color(80, 80, 80, 200).getRGB());
        else
            Screen.fill(stack, x, y, x + width, y + totalHeight, new Color(0, 0, 0, 150).getRGB());

        Screen.fill(stack, x, y, x + width, y + 14, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 200));
        Screen.fill(stack, x, y + 13, x + width, y + 14, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY, 120));

        Bloomware.pFontRenderer.drawString("Tools", x + (width / 2f - Bloomware.pFontRenderer.getStringWidth("Accounts", 18) / 2f), y + 3, ColorUtils.Colors.PRIMARY.getRGB(), true);
        int offsetY = 17;

        if (!isMinimized) {
            Identifier minimized = new Identifier("ofx", "icons/notminimized.png");
            mc.getTextureManager().bindTexture(minimized);
            DrawableHelper.drawTexture(stack, x + width - 10, y + 2, 0, 0, 0, 8, 8, 8, 8);
            for (AbstractButton tool : tools) {
                tool.x = this.x;
                tool.y = this.y + offsetY;
                tool.draw(stack, mouseX, mouseY, tickDelta);
                offsetY += 17;
            }
        } else {
            Identifier minimized = new Identifier("ofx", "icons/minimized.png");
            mc.getTextureManager().bindTexture(minimized);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        for (AbstractButton tool : tools) {
            if (tool.isMouseWithin(mouseX, mouseY)) {
                tool.mouseClicked(mouseX, mouseY, mouseButton);
                return;
            }
        }

        if (mouseButton == 1 && isMouseInside(x, y, width, 13, mouseX, mouseY)) {
            isMinimized = !isMinimized;
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            for (AbstractButton tool : tools) {
                switch (tool.getClass().getName()) {
                    case "AddCrackedAccountWidget": ((AddCrackedAccountWidget) tool).isPressed = false;
                    case "AddMojangAccountWidget": ((AddMojangAccountWidget) tool).isPressed = false;
                    case "AddTokenAccountWidget": ((AddTokenAccountWidget) tool).isPressed = false;
                }
            }
        }
    }
}
