package me.offeex.ofx.module.modules.hud;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.modules.client.ClickGui;
import net.minecraft.client.gui.screen.Screen;
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
            width = Main.sFontRenderer.getStringWidth("Server: " + server, Main.sFontRenderer.getFontsize()) + 8;
            if (ClickGui.getCurrentScreen() == 2) {
                Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
            }
            Main.sFontRenderer.drawString("Server: " + server, x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
        }
    }
}
