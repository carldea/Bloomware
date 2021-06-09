package me.offeex.ofx.module.modules.client;

import me.offeex.ofx.Main;
import me.offeex.ofx.module.Module;
import org.lwjgl.glfw.GLFW;

public class HudEditor extends Module {

    public HudEditor() {
        super("HudEditor", "Edit your HUD", GLFW.GLFW_KEY_RIGHT_CONTROL, Category.CLIENT, true);
    }

    @Override
    public void toggle() {
        mc.openScreen(Main.hudScreen);
        ClickGui.setCurrentScreen(2);
    }
}
