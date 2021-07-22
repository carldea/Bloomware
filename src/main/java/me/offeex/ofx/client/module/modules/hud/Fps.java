package me.offeex.ofx.client.module.modules.hud;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.gui.api.ColorUtils;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.modules.client.ClickGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class Fps extends Module {
    public Fps() {
        super("FPS", "Shows the current fps value", KEY_UNBOUND, Category.HUD, false);
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            width = Bloomware.sFontRenderer.getStringWidth("FPS: " + mc.fpsDebugString.split(" ")[0], Bloomware.sFontRenderer.getFontsize()) + 8;
            if (ClickGui.getCurrentScreen() == 2) {
                Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
            }
            Bloomware.sFontRenderer.drawString("FPS: " + mc.fpsDebugString.split(" ")[0], x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
        }
    }
}
