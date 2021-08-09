package me.offeex.ofx.client.gui.impl.oldclick;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.gui.api.AbstractDraggable;
import me.offeex.ofx.client.gui.impl.hud.element.HudElement;
import me.offeex.ofx.client.gui.impl.oldclicksettings.SettingWindow;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.ModuleManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class HudScreen extends Screen {

    public HudScreen() {
        super(new LiteralText("HudEditor"));
        for (Module m : ModuleManager.getModulesByCategory(Module.Category.HUD)) {
            HudElement element = new HudElement(m, 10, 10, 100, 20);
            Bloomware.oldGui.panels.add(element);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (Bloomware.oldGui.getDragging() != null) Bloomware.oldGui.getDragging().updateDragLogic(mouseX, mouseY);
        for (AbstractDraggable ad : Bloomware.oldGui.panels) {
            if (ad instanceof GuiPanel) {
                GuiPanel panel = (GuiPanel) ad;
                if (panel.getCategory().equals(Module.Category.HUD))
                    panel.draw(matrices, mouseX, mouseY, delta);
            } else if (ad instanceof SettingWindow) {
                continue;
            } else if (!(ad instanceof HudElement))
                ad.draw(matrices, mouseX, mouseY, delta);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        int localHeight;
        for (int i = Bloomware.oldGui.panels.size() - 1; i >= 0; i--) {
            localHeight = height;
            AbstractDraggable panel = Bloomware.oldGui.panels.get(i);
            if (panel instanceof GuiPanel) {
                GuiPanel guiPanel = (GuiPanel) panel;
                localHeight = 13;
                if (guiPanel.getCategory() != Module.Category.HUD)
                    continue;
            }
            if (panel instanceof HudElement) {
                HudElement element = (HudElement) panel;
                if (!element.getModule().isEnabled()) {
                    continue;
                } else {
                    if (panel.isMouseInside(panel.x, panel.y, panel.width, 20, mouseX, mouseY)) {
                        panel.mouseClicked(mouseX, mouseY, mouseButton);
                        Bloomware.oldGui.panels.remove(i);
                        Bloomware.oldGui.panels.add(panel);
                        Bloomware.oldGui.setDragging(panel);
                        panel.startDragging(mouseX, mouseY, mouseButton);
                    }
                }
            } else {
                if (panel.isMouseWithin(mouseX, mouseY)) {
                    panel.mouseClicked(mouseX, mouseY, mouseButton);
                    Bloomware.oldGui.panels.remove(i);
                    Bloomware.oldGui.panels.add(panel);
                    if (panel.isMouseInside(panel.x, panel.y, panel.width, 13, mouseX, mouseY)) {
                        Bloomware.oldGui.setDragging(panel);
                        panel.startDragging(mouseX, mouseY, mouseButton);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
        Bloomware.oldGui.setDragging(null);
        for (int i = Bloomware.oldGui.panels.size() - 1; i >= 0; i--) {
            if (Bloomware.oldGui.panels.get(i).isMouseWithin(mouseX, mouseY)) {
                Bloomware.oldGui.panels.get(i).mouseReleased(mouseX, mouseY, mouseButton);
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
        Bloomware.oldGui.setDragging(null);
    }
}
