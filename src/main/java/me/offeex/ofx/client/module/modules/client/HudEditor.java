package me.offeex.ofx.client.module.modules.client;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.module.Module;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.glfw.GLFW;

public class HudEditor extends Module {

    public HudEditor() {
        super("HudEditor", "Edit your HUD", Category.CLIENT, true);
    }

    @Override
    public void onEnable() {
        mc.openScreen(Bloomware.hudEditor);
        disable();
    }
}
