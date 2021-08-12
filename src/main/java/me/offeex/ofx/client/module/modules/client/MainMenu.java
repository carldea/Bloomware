package me.offeex.ofx.client.module.modules.client;

import me.offeex.ofx.client.module.Module;
import org.lwjgl.glfw.GLFW;

public class MainMenu extends Module {
    public MainMenu() {
        super("BloomwareMenu", "Adds custom menu to your client", Category.CLIENT, false);
    }
}
