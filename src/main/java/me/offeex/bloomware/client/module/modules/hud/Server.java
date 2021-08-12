package me.offeex.bloomware.client.module.modules.hud;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.api.util.ColorUtils;
import me.offeex.bloomware.client.module.Module;
import net.minecraft.client.util.math.MatrixStack;

public class Server extends Module {
    public Server() {
        super("Current server", "Shows current server you playing", Category.HUD, true);
    }

    String server = "s";

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (mc.getCurrentServerEntry() != null) {
            server = mc.getCurrentServerEntry().address;
        } else if (mc.isInSingleplayer()) {
            server = "Singleplayer";
        } else {
            server = "null";
        }

        if (isEnabled()) {
            width = Bloomware.sFontRenderer.getStringWidth("Server: " + server, Bloomware.sFontRenderer.getFontsize()) + 8;

            Bloomware.sFontRenderer.drawString("Server: " + server, x + 4, y + 4, ColorUtils.getHudColor().getRGB(), true);
        }
    }
}
