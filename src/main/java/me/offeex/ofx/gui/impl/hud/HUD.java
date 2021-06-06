package me.offeex.ofx.gui.impl.hud;

import me.offeex.ofx.Main;
import me.offeex.ofx.api.event.events.EventDrawOverlay;
import me.offeex.ofx.gui.api.font.StringRenderer;
import me.offeex.ofx.gui.impl.clickgui.GuiScreen;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.ModuleManager;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;

public class HUD {
	private final MinecraftClient mc = MinecraftClient.getInstance();
	GuiScreen screen = Main.gui.getGuiScreen();

	public HUD() {
		Main.EVENTBUS.subscribe(listener);
	}
	
	@EventHandler
	private final Listener<EventDrawOverlay> listener = new Listener<>(e -> {
		StringRenderer fontRenderer = Main.pFontRenderer;

		int windowX = mc.getWindow().getScaledWidth();
		int windowY = mc.getWindow().getScaledHeight() - 13;

		for (Module module : ModuleManager.getModules()) {
			if (module.isEnabled() && !module.isHidden()) {
				fontRenderer.drawString(module.getName(), windowX - fontRenderer.getStringWidth(module.getName(), 23) - 3, windowY, module.category.color.getRGB(), true);
				windowY += -10;
			}
		}
		for (int i = 0; i < ModuleManager.modules.size(); i++) {
			Module tmp;
			for (int j = i + 1; j < ModuleManager.modules.size(); j++) {
				if (ModuleManager.modules.get(i).name.length() < ModuleManager.modules.get(j).name.length()) {
					tmp = ModuleManager.modules.get(i);
					ModuleManager.modules.set(i, ModuleManager.modules.get(j));
					ModuleManager.modules.set(j, tmp);
				}
			}
		}
	});
}