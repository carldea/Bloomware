package me.offeex.bloomware.client.module.modules.client;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import org.lwjgl.glfw.GLFW;

public class ClickGui extends Module {

    public final Setting<Number> r = register("Red", 249, 1, 255, 1);
    public final Setting<Number> g = register("Green", 47, 1, 255, 1);
    public final Setting<Number> b = register("Blue", 154, 1, 255, 1);

    public ClickGui() {
        super("ClickGUI", "It's GUI", Category.CLIENT, true);
        this.setKey(GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    @Override
    public void toggle() {
        mc.openScreen(Bloomware.newGui);
    }
}
