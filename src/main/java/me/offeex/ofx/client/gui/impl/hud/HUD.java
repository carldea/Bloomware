package me.offeex.ofx.client.gui.impl.hud;

import com.google.common.eventbus.Subscribe;
import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.event.events.EventDrawOverlay;
import me.offeex.ofx.client.gui.api.font.StringRenderer;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.ModuleManager;
import net.minecraft.client.MinecraftClient;

public class HUD {

	MinecraftClient mc = MinecraftClient.getInstance();

	@Subscribe
	public void onDrawOverlay(EventDrawOverlay drawOverlay) {
		StringRenderer fontRenderer = Bloomware.pFontRenderer;

		int windowX = mc.getWindow().getScaledWidth();
		int windowY = mc.getWindow().getScaledHeight() - 13;

		for (Module module : ModuleManager.getModules()) {
			if (module.isEnabled() && !module.isHidden()) {
				fontRenderer.drawString(module.getName(), windowX - fontRenderer.getStringWidth(module.getName(), 23) - 3, windowY, module.getCategory().getColor().getRGB(), true);
				windowY += -10;
			}
		}
		for (int i = 0; i < ModuleManager.getModules().size(); i++) {
			Module tmp;
			for (int j = i + 1; j < ModuleManager.getModules().size(); j++) {
				if (ModuleManager.getModules().get(i).name.length() < ModuleManager.getModules().get(j).name.length()) {
					tmp = ModuleManager.getModules().get(i);
					ModuleManager.getModules().set(i, ModuleManager.getModules().get(j));
					ModuleManager.getModules().set(j, tmp);
				}
			}
		}
	}
}