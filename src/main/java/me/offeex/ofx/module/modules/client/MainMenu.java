package me.offeex.ofx.module.modules.client;

import me.offeex.ofx.module.Module;
import org.lwjgl.glfw.GLFW;

public class MainMenu extends Module {
    public MainMenu() {
        super("Main Menu", "Adds custom menu to your client", GLFW.GLFW_KEY_UNKNOWN, Category.CLIENT, false);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
