package me.offeex.ofx.module.modules.client;

import me.offeex.ofx.Main;
import me.offeex.ofx.module.Module;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.glfw.GLFW;

public class ClickGui extends Module {

    private static int currentScreen = 0;

    public ClickGui() {
        super("ClickGUI", "It's GUI", GLFW.GLFW_KEY_RIGHT_SHIFT, Category.CLIENT, true);
    }

    @Override
    public void toggle() {
        mc.openScreen(Main.guiscreen);
        setCurrentScreen(1);
    }

    public static void setCurrentScreen(int currentScreen) {
        ClickGui.currentScreen = currentScreen;
    }

    public static int getCurrentScreen() {
        return currentScreen;
    }
}
