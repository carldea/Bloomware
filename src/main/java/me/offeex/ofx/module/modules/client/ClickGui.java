package me.offeex.ofx.module.modules.client;

import me.offeex.ofx.Main;
import me.offeex.ofx.module.Module;
import org.lwjgl.glfw.GLFW;

public class ClickGui extends Module {

    public ClickGui() {
        super("ClickGUI", "It's GUI", GLFW.GLFW_KEY_RIGHT_SHIFT, Category.CLIENT, true);
    }

    @Override
    public void toggle() {
//        mc.openScreen(GuiScreen.INSTANCE);
        mc.openScreen(Main.gui.getGuiScreen());
    }
}
