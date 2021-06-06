package me.offeex.ofx.gui.impl.hud.component.components;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.gui.impl.hud.component.Component;
import me.offeex.ofx.module.modules.client.HudEditor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class Server extends Component {
    public Server() {
        super("Current server", "Shows current server you playing", 10, 20, 10, 18);
    }

    String server = "s";
    private static MinecraftClient mc = MinecraftClient.getInstance();

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
            if (HudEditor.openScreen) {
                Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
            }
            Main.sFontRenderer.drawString("Server: " + server, x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
    }
}
