package me.offeex.bloomware.client.module.modules.hud;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.api.util.ColorUtils;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.api.traits.IMixinMinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Fps extends Module {
    public Fps() {
        super("FPS", "Shows the current fps value", Category.HUD, true);
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            width = Bloomware.sFontRenderer.getStringWidth("FPS: " + ((IMixinMinecraftClient)mc).fps(), Bloomware.sFontRenderer.getFontsize()) + 8;
            Bloomware.sFontRenderer.drawString("FPS: " + ((IMixinMinecraftClient)mc).fps(), x + 4, y + 4, ColorUtils.getHudColor().getRGB(), true);
        }
    }
}
