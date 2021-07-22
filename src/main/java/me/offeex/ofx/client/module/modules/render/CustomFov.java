package me.offeex.ofx.client.module.modules.render;

import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.setting.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class CustomFov extends Module {
    public CustomFov() {
        super("Custom FOV", "Allows you to set fov you want", GLFW.GLFW_KEY_UNKNOWN, Category.RENDER, false,
                new NumberSetting("FOV", 90, 30, 360, 0.1));
    }

    @Override
    public void onEnable() {
        mc.options.fov = ((NumberSetting) this.getSetting(0)).getValue();
    }
}
