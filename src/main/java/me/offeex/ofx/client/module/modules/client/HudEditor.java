package me.offeex.ofx.client.module.modules.client;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.module.Module;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.glfw.GLFW;

public class HudEditor extends Module {
    Screen currentScreen;

    public HudEditor() {
        super("HudEditor", "Edit your HUD", GLFW.GLFW_KEY_RIGHT_CONTROL, Category.CLIENT, true);
    }

    @Override
    public void onEnable() {
        mc.openScreen(Bloomware.hudScreen);
        disable();
    }
}
