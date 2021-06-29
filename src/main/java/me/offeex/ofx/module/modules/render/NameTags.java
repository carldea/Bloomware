package me.offeex.ofx.module.modules.render;

import me.offeex.ofx.module.Module;
import me.offeex.ofx.setting.settings.BooleanSetting;
import org.lwjgl.glfw.GLFW;

public class NameTags extends Module {
    public NameTags() {
        super("Name Tags", ":^)", GLFW.GLFW_KEY_UNKNOWN, Category.RENDER, false,
                new BooleanSetting("Health", true),
                new BooleanSetting("Gamemode", false),
                new BooleanSetting("Ping", false));
    }
}
