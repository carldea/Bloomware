package me.offeex.ofx.gui.impl.settings.components;

import me.offeex.ofx.Main;
import me.offeex.ofx.command.CommandManager;
import me.offeex.ofx.gui.api.AbstractButton;
import me.offeex.ofx.gui.api.AbstractDraggable;
import me.offeex.ofx.gui.impl.settings.SettingWindow;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CloseButton extends AbstractButton {
    public AbstractDraggable panel;
    public boolean isPressed;
    MinecraftClient mc = MinecraftClient.getInstance();

    Identifier closed = new Identifier("ofx", "icons/closed.png");

    public CloseButton(int x, int y, AbstractDraggable p) {
        super(x, y, 10, 10);
        this.panel = p;
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isPressed) {
            if (!isMouseWithin(mouseX, mouseY)) isPressed = false;
        }
        mc.getTextureManager().bindTexture(closed);
        DrawableHelper.drawTexture(stack, x, y, 0, 0, 0, 10, 10, 10, 10);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            isPressed = true;
            if (Main.guiscreen.panels.get(Main.guiscreen.panels.size() - 1) instanceof SettingWindow) {
                Main.guiscreen.panels.remove(Main.guiscreen.panels.get(Main.guiscreen.panels.size() - 1));
            } 
        }
    }
}
