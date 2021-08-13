package me.offeex.bloomware.client.gui.impl.hud;

import com.google.common.eventbus.Subscribe;
import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.api.event.events.EventDrawOverlay;
import me.offeex.bloomware.api.util.RainbowUtil;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.module.ModuleManager;
import net.minecraft.client.MinecraftClient;

public class HUD {

    MinecraftClient mc = MinecraftClient.getInstance();

    public HUD() {
        Bloomware.EVENTBUS.register(this);
    }

    @Subscribe
    public void onDrawOverlay(EventDrawOverlay e) {
        int windowX = mc.getWindow().getScaledWidth();
        int windowY = mc.getWindow().getScaledHeight() - 13;

        RainbowUtil Rainbow = new RainbowUtil();
        int l_I = 0;
        for (Module module : ModuleManager.getModules()) {
            if (module.isEnabled() && !module.isHidden()) {
                mc.textRenderer.drawWithShadow(e.matrix, module.getName(), windowX - mc.textRenderer.getWidth(module.getName()) - 3, windowY, Rainbow.GetRainbowColorAt(l_I), true);
                windowY += -10;
                l_I += 15;
                if (l_I >= 355)
                    l_I = 0;
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