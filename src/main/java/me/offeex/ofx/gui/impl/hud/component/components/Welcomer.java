package me.offeex.ofx.gui.impl.hud.component.components;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.gui.impl.hud.component.Component;
import me.offeex.ofx.module.modules.client.HudEditor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class Welcomer extends Component {
    public Welcomer() {
        super("Welcomer", "Welcomes you", 10, 30, 10, 18);
    }

    private static MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            width = Main.sFontRenderer.getStringWidth("Looking cute today, " + mc.getSession().getUsername() + "! :^)", Main.sFontRenderer.getFontsize()) + 8;
            if (HudEditor.openScreen) {
                Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
            }
            Main.sFontRenderer.drawString("Looking cute today, " + mc.getSession().getUsername() + "! :^)", x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
        }
        if (mouseButton == 1) {
            x = 8;
            y = MinecraftClient.getInstance().getWindow().getScaledHeight() - 30;
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
    }
}
