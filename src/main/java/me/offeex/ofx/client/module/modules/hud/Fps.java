package me.offeex.ofx.client.module.modules.hud;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.util.ColorUtils;
import me.offeex.ofx.client.module.Module;
import net.minecraft.client.util.math.MatrixStack;

public class Fps extends Module {
    public Fps() {
        super("FPS", "Shows the current fps value", Category.HUD, true);
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            width = Bloomware.sFontRenderer.getStringWidth("FPS: " + mc.fpsDebugString.split(" ")[0], Bloomware.sFontRenderer.getFontsize()) + 8;
            Bloomware.sFontRenderer.drawString(mc.fpsDebugString.split(" ")[0], x + 4, y + 4, ColorUtils.getHudColor().getRGB(), true);
        }
    }
}
