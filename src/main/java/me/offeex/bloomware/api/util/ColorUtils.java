package me.offeex.bloomware.api.util;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.setting.settings.NumberSetting;

import java.awt.*;

public class ColorUtils {
    public static Color getDarkerVersion (Color color) {
        float[] HSB = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        HSB[1] = 0.5f; // Насыщенность
        HSB[2] = 0.4f; // Яркость
        Color newColor = Color.getHSBColor(HSB[0], HSB[1], HSB[2]);
        newColor = new Color(newColor.getRed(), newColor.getGreen(), newColor.getBlue(), color.getAlpha());
        return newColor;
    }

    public static int withTransparency (Color color, int transparency) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), transparency).getRGB();
    }

    public static Color getGuiColor() {
        int r = (int) ((NumberSetting) Bloomware.moduleManager.getModule("ClickGUI").getSetting(0)).getValue();
        int g = (int) ((NumberSetting) Bloomware.moduleManager.getModule("ClickGUI").getSetting(1)).getValue();
        int b = (int) ((NumberSetting) Bloomware.moduleManager.getModule("ClickGUI").getSetting(2)).getValue();
        return new Color(r, g, b, 190);
    }

    public static Color getTextColor() {
        int r = (int) ((NumberSetting) Bloomware.moduleManager.getModule("Colors").getSetting(0)).getValue();
        int g = (int) ((NumberSetting) Bloomware.moduleManager.getModule("Colors").getSetting(1)).getValue();
        int b = (int) ((NumberSetting) Bloomware.moduleManager.getModule("Colors").getSetting(2)).getValue();
        return new Color(r, g, b, 255);
    }

    public static Color getSliderColor() {
        int r = (int) ((NumberSetting) Bloomware.moduleManager.getModule("Colors").getSetting(3)).getValue();
        int g = (int) ((NumberSetting) Bloomware.moduleManager.getModule("Colors").getSetting(4)).getValue();
        int b = (int) ((NumberSetting) Bloomware.moduleManager.getModule("Colors").getSetting(5)).getValue();
        return new Color(r, g, b, 255);
    }

    public static Color getHudColor() {
        int r = (int) ((NumberSetting) Bloomware.moduleManager.getModule("Colors").getSetting(6)).getValue();
        int g = (int) ((NumberSetting) Bloomware.moduleManager.getModule("Colors").getSetting(7)).getValue();
        int b = (int) ((NumberSetting) Bloomware.moduleManager.getModule("Colors").getSetting(8)).getValue();
        return new Color(r, g, b, 255);
    }

    public static class Colors {
        public static final Color PRIMARY = new Color(249, 47, 154, 190);
        public static final Color PRIMARY_DARKER = new Color(153, 11, 87, 255);
        public static final Color SECONDARY = Color.decode("#1c1c1c");
        public static final Color GREEN = Color.GREEN;
        public static final Color RED = Color.RED;
        public static final Color WHITE = Color.WHITE;
    }
}
