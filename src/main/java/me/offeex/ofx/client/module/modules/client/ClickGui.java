package me.offeex.ofx.client.module.modules.client;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.module.Module;
import org.lwjgl.glfw.GLFW;

public class ClickGui extends Module {

    public ClickGui() {
        super("ClickGUI", "It's GUI", GLFW.GLFW_KEY_RIGHT_SHIFT, Category.CLIENT, true);
    }

    @Override
    public void toggle() {
        mc.openScreen(Bloomware.guiscreen);
    }
}
