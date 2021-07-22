package me.offeex.ofx.client.module.modules.hud;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.gui.api.ColorUtils;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.modules.client.ClickGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class Welcomer extends Module {
    public Welcomer() {
        super("Welcomer", "Welcomes you", KEY_UNBOUND, Category.HUD, false);
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            width = Bloomware.sFontRenderer.getStringWidth("Looking cute today, " + mc.getSession().getUsername() + "! :^)", Bloomware.sFontRenderer.getFontsize()) + 8;

            Bloomware.sFontRenderer.drawString("Looking cute today, " + mc.getSession().getUsername() + "! :^)", x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
        }
    }
}
