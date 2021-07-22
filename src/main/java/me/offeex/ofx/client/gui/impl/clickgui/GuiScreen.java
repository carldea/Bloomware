package me.offeex.ofx.client.gui.impl.clickgui;

import com.google.common.eventbus.Subscribe;
import me.offeex.ofx.api.event.events.EventKeyPress;
import me.offeex.ofx.client.gui.api.AbstractDraggable;
import me.offeex.ofx.client.gui.impl.hud.element.HudElement;
import me.offeex.ofx.client.module.Module;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

public class GuiScreen extends Screen {
    int key;
    public final ArrayList<AbstractDraggable> panels;
    private AbstractDraggable dragging = null;

    public GuiScreen() {
        super(new LiteralText("ClickGUI"));
        panels = new ArrayList<>();
        int offsetX = 0;
        for (Module.Category category : Module.Category.values()) {
            GuiPanel guiPanel = new GuiPanel(category, 10 + offsetX, 20, 140, 80);
            panels.add(guiPanel);
            offsetX += guiPanel.width + 5;
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (dragging != null) dragging.updateDragLogic(mouseX, mouseY);
        for (AbstractDraggable ad : panels) {
            if (ad instanceof GuiPanel) {
                GuiPanel panel = (GuiPanel) ad;
                if (!panel.getCategory().equals(Module.Category.HUD))
                    panel.draw(matrices, mouseX, mouseY, delta);
            } else if (!(ad instanceof HudElement))
                ad.draw(matrices, mouseX, mouseY, delta);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        int localHeight;
        for (int i = panels.size() - 1; i >= 0; i--) {
            localHeight = height;
            AbstractDraggable panel;
            try {
                panel = panels.get(i);
            } catch (IndexOutOfBoundsException e) {
                continue;
            }

            if ((panel instanceof HudElement)) // if there something from HUD, then we stop mouseClicked()
                continue;

            if (panel instanceof GuiPanel) {
                GuiPanel guiPanel = (GuiPanel) panel;
                localHeight = 13;
                if (guiPanel.getCategory() == Module.Category.HUD) // if HUD, then we stop mouseClicked()
                    continue;
            }

            if (panel.isMouseWithin(mouseX, mouseY)) {
                panel.mouseClicked(mouseX, mouseY, mouseButton);
                try {
                    panels.remove(i);
                    panels.add(panel);
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
                if (mouseButton == 0) {
                    if (panel.isMouseInside(panel.x, panel.y, panel.width, 13, mouseX, mouseY)) {
                        dragging = panel;
                        panel.startDragging(mouseX, mouseY, mouseButton);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
        dragging = null;
        for (int i = panels.size() - 1; i >= 0; i--) {
            if (panels.get(i).isMouseWithin(mouseX, mouseY)) {
                panels.get(i).mouseReleased(mouseX, mouseY, mouseButton);
                return true;
            }
        }
        return true;
    }


    @Override
    public boolean isPauseScreen() {
        return true;
    }

    @Subscribe
    public void onKey(EventKeyPress keyPress) {
        key = keyPress.getKey();
    }

    @Override
    public void onClose() {
        super.onClose();
        dragging = null;
    }

    public AbstractDraggable getDragging() {
        return dragging;
    }

    public void setDragging(AbstractDraggable dragging) {
        this.dragging = dragging;
    }
}
