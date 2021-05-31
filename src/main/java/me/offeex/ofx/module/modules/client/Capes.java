package me.offeex.ofx.module.modules.client;

import me.offeex.ofx.Main;
import me.offeex.ofx.api.util.CapeUtil;
import me.offeex.ofx.module.Module;
import org.lwjgl.glfw.GLFW;

public class Capes extends Module {
    public Capes() {
        super("Capes", "Shows Bloomware's cape", GLFW.GLFW_KEY_UNKNOWN, Category.CLIENT, false);
    }

    /*
    @Override
    public void onEnable() {
        CapeUtil.parseCape("JEX_CAPE", "a2306178ee1b40d48fd9d25c5756edec");
    }

     */
}
