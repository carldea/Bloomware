package me.offeex.ofx.module.modules.client;

import me.offeex.ofx.Main;
import me.offeex.ofx.module.Module;
import org.lwjgl.glfw.GLFW;

public class HudEditor extends Module {
    public static boolean openScreen;

    public HudEditor() {
        super("HudEditor", "", GLFW.GLFW_KEY_RIGHT_CONTROL, Category.CLIENT, false);
    }

    @Override
    public void toggle() {
        mc.openScreen(Main.hudd.getHudScreen());
        openScreen = true;
    }
}
