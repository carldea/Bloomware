package me.offeex.ofx.client.module.modules.hud;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.util.ColorUtils;
import me.offeex.ofx.client.module.Module;
import net.minecraft.client.util.math.MatrixStack;

public class Server extends Module {
    public Server() {
        super("Current server", "Shows current server you playing", KEY_UNBOUND, Category.HUD, false);
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

            Bloomware.sFontRenderer.drawString("Server: " + server, x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
        }
    }
}
