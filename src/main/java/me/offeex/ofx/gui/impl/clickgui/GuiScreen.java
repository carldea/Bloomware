package me.offeex.ofx.gui.impl.clickgui;

import me.offeex.ofx.Main;
import me.offeex.ofx.api.event.events.EventKeyPress;
import me.offeex.ofx.gui.api.AbstractDraggable;
import me.offeex.ofx.gui.impl.hud.component.Component;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.ModuleManager;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

/**
 * 
 * @author Gav06
 * 
 * @since 5/4/2021
 *
 */
public class GuiScreen extends Screen {
	int key;
	public final ArrayList<AbstractDraggable> panels;
//	public final ArrayList<GuiPanel> guiPanels;
	private AbstractDraggable dragging = null;

	public GuiScreen() {
		super(new LiteralText("ClickGUI"));
//		guiPanels = new ArrayList<>();
		panels = new ArrayList<>();
		int offsetX = 0;
		for (Module.Category category : Module.Category.values()) {
			GuiPanel guiPanel = new GuiPanel(category, 10 + offsetX, 20, 140, 80);
			panels.add(guiPanel);
//			guiPanels.add(guiPanel);
			offsetX += guiPanel.width + 5;
		}
		for (Module m : ModuleManager.modules) {
			if (m.getCategory().equals(Module.Category.HUD)) {
				panels.add(m.getComponent());
			}
		}
		Main.EVENTBUS.subscribe(listener);
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		if(dragging != null) dragging.updateDragLogic(mouseX, mouseY);
		for (AbstractDraggable ad : panels) {
			if (ad instanceof GuiPanel) {
				GuiPanel panel = (GuiPanel) ad;
				if (!panel.getCategory().equals(Module.Category.HUD))
					panel.draw(matrices, mouseX, mouseY, delta);
			}
			else if (!(ad instanceof Component))
				ad.draw(matrices, mouseX, mouseY, delta);
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		for (int i = panels.size() - 1; i >= 0; i--) {
			if (!(panels.get(i) instanceof Component)) {
				if (panels.get(i).isMouseWithin(mouseX, mouseY)) {
					panels.get(i).mouseClicked(mouseX, mouseY, mouseButton);
					AbstractDraggable panel = panels.get(i);
					panels.remove(i);
					panels.add(panel);
					if (panel.isMouseInside(panel.x, panel.y, panel.width, 13, mouseX, mouseY)) {
						dragging = panel;
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

	@EventHandler
	private final Listener<EventKeyPress> listener = new Listener<>(e -> {
		key = e.getKey();
	});

	@Override
	public void onClose() {
		super.onClose();
		dragging = null;
	}

	public AbstractDraggable getDragging(){
		return dragging;
	}
	public void setDragging(AbstractDraggable dragging) {
		this.dragging = dragging;
	}
}
