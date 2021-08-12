package me.offeex.bloomware.client.module.modules.hud;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.api.util.ColorUtils;
import me.offeex.bloomware.client.module.Module;
import net.minecraft.client.util.math.MatrixStack;

public class Watermark extends Module {
    public Watermark() {
        super("WaterMark", "Shows logo", Category.HUD, true);
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            width = Bloomware.sFontRenderer.getStringWidth(Bloomware.name + " v" + Bloomware.version, Bloomware.sFontRenderer.getFontsize()) + 8;

            Bloomware.sFontRenderer.drawString(Bloomware.name + " v" + Bloomware.version, x + 4, y + 4, ColorUtils.getHudColor().getRGB(), true);
        }
    }
}
