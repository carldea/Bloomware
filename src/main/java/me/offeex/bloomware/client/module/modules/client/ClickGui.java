package me.offeex.bloomware.client.module.modules.client;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class ClickGui extends Module {

    public ClickGui() {
        super("ClickGUI", "It's GUI", Category.CLIENT, true,
                new NumberSetting("Red", 249, 1, 255, 1),
                new NumberSetting("Green", 47, 1, 255, 1),
                new NumberSetting("Blue", 154, 1, 255, 1));
        this.setKey(GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    @Override
    public void toggle() {
        mc.openScreen(Bloomware.newGui);
    }
}
