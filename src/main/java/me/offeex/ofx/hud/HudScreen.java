package me.offeex.ofx.hud;

import me.offeex.ofx.hud.component.Component;
import me.offeex.ofx.hud.component.ComponentManager;
import me.offeex.ofx.module.modules.client.HudEditor;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

public class HudScreen extends Screen {
    HudPanel panel;
    ArrayList<Component> components;
//    Component component;

    public HudScreen() {
        super(new LiteralText("OFX HudEditor"));
        panel = new HudPanel(10, 20, 150, 220);
        components = ComponentManager.getComponents();
//        component = ComponentManager.getComponentByName("Coords");
    }

    @Override
    public void render(MatrixStack matricies, int mouseX, int mouseY, float tickDelta) {
            panel.draw(matricies, mouseX, mouseY, tickDelta);
            panel.updateDragLogic(mouseX, mouseY);
            for (Component c : components) {
                c.updateDragLogic(mouseX, mouseY);
            }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (panel.isMouseWithin(mouseX, mouseY)) {
            panel.mouseClicked(mouseX, mouseY, mouseButton);
            return true;
        }
        for (Component c : components) {
            if (c.isMouseWithin(mouseX, mouseY)) {
                c.mouseClicked(mouseX, mouseY, mouseButton);
                return true;
            }
        }
        return true;

    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
        panel.stopDragging(mouseX, mouseY, mouseButton);
        if (panel.isMouseWithin(mouseX, mouseY)) {
            panel.mouseReleased(mouseX, mouseY, mouseButton);
            return true;
        }
        for (Component c : components) {
            c.stopDragging(mouseX, mouseY, mouseButton);
            if (c.isMouseWithin(mouseX, mouseY)) {
                c.mouseReleased(mouseX, mouseY, mouseButton);
                return true;
            }
        }
        return true;
    }

    @Override
    public void onClose() {
        super.onClose();
        HudEditor.openScreen = false;

    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }
}
