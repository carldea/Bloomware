package me.offeex.ofx.client.module.modules.client;

import me.offeex.ofx.client.module.Module;
import org.lwjgl.glfw.GLFW;

public class Capes extends Module {
    public Capes () {
        super("Capes", "Adds capes to the player", GLFW.GLFW_KEY_UNKNOWN, Category.CLIENT, false);
    }
}
