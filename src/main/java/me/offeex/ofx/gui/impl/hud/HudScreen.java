package me.offeex.ofx.gui.impl.hud;

import me.offeex.ofx.gui.api.AbstractDraggable;
import me.offeex.ofx.gui.impl.hud.component.Component;
import me.offeex.ofx.gui.impl.hud.component.ComponentManager;
import me.offeex.ofx.module.modules.client.HudEditor;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

public class HudScreen extends Screen {
    public static HudPanel panel;
    ArrayList<Component> components;
    private static HudScreen instance;
    private AbstractDraggable dragging = null;
//    Component component;

    public HudScreen() {
        super(new LiteralText("OFX HudEditor"));
        instance = this;
        panel = new HudPanel(10, 20, 150, 220);
        components = ComponentManager.getComponents();
//        component = ComponentManager.getComponentByName("Coords");
    }

    @Override
    public void render(MatrixStack matricies, int mouseX, int mouseY, float tickDelta) {
        panel.draw(matricies, mouseX, mouseY, tickDelta);
        if(dragging != null) dragging.updateDragLogic(mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
//        Проверка на MouseClicked
        if (panel.isMouseWithin(mouseX, mouseY)) {
            panel.mouseClicked(mouseX, mouseY, mouseButton);
//            Проверка на Draggable
            if(panel.isMouseInside(panel.x, panel.y, panel.width, 13, mouseX, mouseY)){
                dragging = panel;
                panel.startDragging(mouseX, mouseY, mouseButton);
            }
            return true;
        }
        for (Component c : components) {
            if (c.isMouseWithin(mouseX, mouseY)) {
                c.mouseClicked(mouseX, mouseY, mouseButton);
                dragging = c;
                c.startDragging(mouseX, mouseY, mouseButton);
                return true;
            }
        }
        return true;

    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
        dragging = null;
        if (panel.isMouseWithin(mouseX, mouseY)) {
            panel.mouseReleased(mouseX, mouseY, mouseButton);
            return true;
        }
        for (Component c : components) {
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
        dragging = null;
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }

    public static HudScreen getInstance(){
        return instance;
    }

    public AbstractDraggable getDragging(){
        return dragging;
    }
}
