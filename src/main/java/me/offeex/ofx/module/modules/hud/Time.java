package me.offeex.ofx.module.modules.hud;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.modules.client.ClickGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time extends Module {
    public Time() {
        super("Time", "Shows current time", KEY_UNBOUND, Category.HUD, false);
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            width = Main.sFontRenderer.getStringWidth("Time: " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()), Main.sFontRenderer.getFontsize()) + 8;
            if (ClickGui.getCurrentScreen() == 2) {
                Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
            }
            Main.sFontRenderer.drawString("Time: " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()), x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
        }
    }
}
