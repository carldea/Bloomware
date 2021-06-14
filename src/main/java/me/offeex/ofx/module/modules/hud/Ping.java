package me.offeex.ofx.module.modules.hud;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.modules.client.ClickGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.math.MatrixStack;

public class Ping extends Module {

    public Ping() {
        super("Ping", "Shows your ping", KEY_UNBOUND, Category.HUD, false);
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            assert mc.getNetworkHandler() != null;
            assert mc.player != null;
            PlayerListEntry playerListEntry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
            assert playerListEntry != null;
            try {
                width = Main.sFontRenderer.getStringWidth("Ping: " + playerListEntry.getLatency() + "ms", Main.sFontRenderer.getFontsize()) + 8;
                if (ClickGui.getCurrentScreen() == 2) {
                    Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
                }
                Main.sFontRenderer.drawString("Ping: " + playerListEntry.getLatency() + "ms", x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
            } catch (NullPointerException nullPointerException) {
                width = Main.sFontRenderer.getStringWidth("Ping: 0ms", Main.sFontRenderer.getFontsize()) + 8;
                if (ClickGui.getCurrentScreen() == 2) {
                    Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
                }
                Main.sFontRenderer.drawString("Ping: 0ms", x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
            }
        }
    }
}
