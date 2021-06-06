package me.offeex.ofx.gui.impl.hud.component.components;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.gui.impl.hud.component.Component;
import me.offeex.ofx.module.modules.client.HudEditor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.math.MatrixStack;

public class Ping extends Component {

    public Ping() {
        super("Ping", "Shows your ping", 10, 60, 10, 18);
    }

    private static MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            assert mc.getNetworkHandler() != null;
            assert mc.player != null;
            PlayerListEntry playerListEntry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
            assert playerListEntry != null;
            try {
                width = Main.sFontRenderer.getStringWidth("Ping: " + playerListEntry.getLatency() + "ms", Main.sFontRenderer.getFontsize()) + 8;
                if (HudEditor.openScreen) {
                    Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
                }
                Main.sFontRenderer.drawString("Ping: " + playerListEntry.getLatency() + "ms", x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
            } catch (NullPointerException nullPointerException) {
                width = Main.sFontRenderer.getStringWidth("Ping: 0ms", Main.sFontRenderer.getFontsize()) + 8;
                if (HudEditor.openScreen) {
                    Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
                }
                Main.sFontRenderer.drawString("Ping: 0ms", x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
            }
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
    }
}
