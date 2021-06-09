package me.offeex.ofx.gui.impl.clickgui;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.AbstractDraggable;
import me.offeex.ofx.gui.impl.hud.component.Component;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.modules.client.ClickGui;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class HudScreen extends Screen {

    public HudScreen() {
        super(new LiteralText("HudEditor"));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if(Main.guiscreen.getDragging() != null) Main.guiscreen.getDragging().updateDragLogic(mouseX, mouseY);
        for (AbstractDraggable ad : Main.guiscreen.panels) {
            if (ad instanceof GuiPanel) {
                GuiPanel panel = (GuiPanel) ad;
                if (panel.getCategory().equals(Module.Category.HUD))
                    panel.draw(matrices, mouseX, mouseY, delta);
            }
            else if (!(ad instanceof Component))
                ad.draw(matrices, mouseX, mouseY, delta);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        for (int i = Main.guiscreen.panels.size() - 1; i >= 0; i--) {
            if (Main.guiscreen.panels.get(i) instanceof GuiPanel) {
                if (Main.guiscreen.panels.get(i).isMouseWithin(mouseX, mouseY)) {
                    Main.guiscreen.panels.get(i).mouseClicked(mouseX, mouseY, mouseButton);
                    AbstractDraggable panel = Main.guiscreen.panels.get(i);
                    Main.guiscreen.panels.remove(i);
                    Main.guiscreen.panels.add(panel);
                    if (panel.isMouseInside(panel.x, panel.y, panel.width, 13, mouseX, mouseY)) {
                        Main.guiscreen.setDragging(panel);
                        panel.startDragging(mouseX, mouseY, mouseButton);
                    }
                    return true;
                }
            }
            else {
                if (Main.guiscreen.panels.get(i).isMouseWithin(mouseX, mouseY)) {
                    Main.guiscreen.panels.get(i).mouseClicked(mouseX, mouseY, mouseButton);
                    AbstractDraggable panel = Main.guiscreen.panels.get(i);
                    Main.guiscreen.panels.remove(i);
                    Main.guiscreen.panels.add(panel);
                    if (panel.isMouseInside(panel.x, panel.y, panel.width, height, mouseX, mouseY)) {
                        Main.guiscreen.setDragging(panel);
                        panel.startDragging(mouseX, mouseY, mouseButton);
                    }
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
        Main.guiscreen.setDragging(null);
        for (int i = Main.guiscreen.panels.size() - 1; i >= 0; i--) {
            if (Main.guiscreen.panels.get(i).isMouseWithin(mouseX, mouseY)) {
                Main.guiscreen.panels.get(i).mouseReleased(mouseX, mouseY, mouseButton);
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }

    @Override
    public void onClose() {
        super.onClose();
        Main.guiscreen.setDragging(null);
        ClickGui.setCurrentScreen(0);
    }
}
