package me.offeex.bloomware.client.altmanager.screens;

import me.offeex.bloomware.client.altmanager.widgets.AccountPanel;
import me.offeex.bloomware.client.altmanager.widgets.CurrentSessionPanel;
import me.offeex.bloomware.client.altmanager.widgets.ToolsPanel;
import me.offeex.bloomware.client.gui.api.AbstractDraggable;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class AltManager extends Screen {
    MinecraftClient mc = MinecraftClient.getInstance();
    Identifier background = new Identifier("bloomware", "gui/coolbg.png");
    ArrayList<AbstractDraggable> panels;
    private AbstractDraggable dragging = null;

    public AltManager() {
        super(Text.of("AltManager"));
        panels = new ArrayList<>();
        panels.add(new AccountPanel(10, 10, 200, 100));
        panels.add(new ToolsPanel(220, 10, 150, 100));
        panels.add(new CurrentSessionPanel(380, 10, 150, 100));
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        mc.getTextureManager().bindTexture(background);
        DrawableHelper.drawTexture(matrices, 0, 0, 0, 0, 0, width, height, height, width);
        if (dragging != null) dragging.updateDragLogic(mouseX, mouseY);
        panels.forEach(abstractDraggable -> { abstractDraggable.draw(matrices, mouseX, mouseY, delta);});
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        for (int i = panels.size() - 1; i >= 0; i--) {
            AbstractDraggable panel;
            try {
                panel = panels.get(i);
            } catch (IndexOutOfBoundsException e) {
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

    public AbstractDraggable getDragging() {
        return dragging;
    }

    public void setDragging(AbstractDraggable dragging) {
        this.dragging = dragging;
    }
}
