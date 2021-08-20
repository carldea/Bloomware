package me.offeex.bloomware.client.module.modules.hud;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.api.util.ColorUtils;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import net.minecraft.client.util.math.MatrixStack;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time extends Module {
    private final Setting<Boolean> showSeconds = register("ShowSeconds", true);

    public Time() {
        super("Time", "Shows current time", Category.HUD, true);
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            width = Bloomware.sFontRenderer.getStringWidth("Time: " + (showSeconds.getValue() ? new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) : new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime())), Bloomware.sFontRenderer.getFontsize()) + 8;
            Bloomware.sFontRenderer.drawString("Time: " + (showSeconds.getValue() ? new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) : new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime())), x + 4, y + 4, ColorUtils.getHudColor().getRGB(), true);
        }
    }
}
