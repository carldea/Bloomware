package me.offeex.ofx.client.module.modules.client;

import me.offeex.ofx.client.module.Module;
import org.lwjgl.glfw.GLFW;

public class MainMenu extends Module {
    public MainMenu() {
        super("Bloomware Menu", "Adds custom menu to your client", GLFW.GLFW_KEY_UNKNOWN, Category.CLIENT, false);
    }
}
