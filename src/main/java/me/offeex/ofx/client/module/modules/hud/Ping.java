package me.offeex.ofx.client.module.modules.hud;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.gui.api.ColorUtils;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.modules.client.ClickGui;
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
                width = Bloomware.sFontRenderer.getStringWidth("Ping: " + playerListEntry.getLatency() + "ms", Bloomware.sFontRenderer.getFontsize()) + 8;

                Bloomware.sFontRenderer.drawString("Ping: " + playerListEntry.getLatency() + "ms", x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
            } catch (NullPointerException nullPointerException) {
                width = Bloomware.sFontRenderer.getStringWidth("Ping: 0ms", Bloomware.sFontRenderer.getFontsize()) + 8;

                Bloomware.sFontRenderer.drawString("Ping: 0ms", x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
            }
        }
    }
}
