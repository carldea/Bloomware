package me.offeex.ofx.gui.impl.clickgui;

import me.offeex.ofx.Main;
import me.offeex.ofx.api.event.events.EventKeyPress;
import me.offeex.ofx.gui.api.AbstractDraggable;
import me.offeex.ofx.gui.impl.hud.HudScreen;
import me.offeex.ofx.module.Module;
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

//	private final TestComp comp;

	public GuiScreen() {
		super(new LiteralText("OFX ClickGUI"));
		this.panels = new ArrayList<>();
		int offsetX = 0;
		for (Module.Category category : Module.Category.values()) {
			GuiPanel guiPanel = new GuiPanel(category, 10 + offsetX, 20, 140, 80);
			panels.add(guiPanel);
			offsetX += guiPanel.width + 5;
		}
		Main.EVENTBUS.subscribe(listener);
	}

	@Override
	public void render(MatrixStack matricies, int mouseX, int mouseY, float tickDelta) {
		for (AbstractDraggable panel : panels) {
			panel.draw(matricies, mouseX, mouseY, tickDelta);
			panel.updateDragLogic(mouseX, mouseY);
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		for (int i = panels.size() - 1; i >= 0; i--) {
			if (panels.get(i).isMouseWithin(mouseX, mouseY)) {
				panels.get(i).mouseClicked(mouseX, mouseY, mouseButton);
				AbstractDraggable panel = panels.get(i);
				panels.remove(i);
				panels.add(panel);
				return true;
			}
		}
		return true;
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
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

}
