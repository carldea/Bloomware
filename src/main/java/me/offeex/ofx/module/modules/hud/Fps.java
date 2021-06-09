package me.offeex.ofx.module.modules.hud;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.modules.client.ClickGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class Fps extends Module {
    public Fps() {
        super("FPS", "Shows the current fps value", KEY_UNBOUND, Category.HUD, false);
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            width = Main.sFontRenderer.getStringWidth("FPS: " + mc.fpsDebugString.split(" ")[0], Main.sFontRenderer.getFontsize()) + 8;
            if (ClickGui.getCurrentScreen() == 2) {
                Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
            }
            Main.sFontRenderer.drawString("FPS: " + mc.fpsDebugString.split(" ")[0], x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
        }
    }
}
