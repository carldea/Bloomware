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
        int r = (int) ((NumberSetting) ModuleManager.getModule("ClickGUI").getSetting(0)).getValue();
        int g = (int) ((NumberSetting) ModuleManager.getModule("ClickGUI").getSetting(1)).getValue();
        int b = (int) ((NumberSetting) ModuleManager.getModule("ClickGUI").getSetting(2)).getValue();
        return new Color(r, g, b, 190);
    }

    public static Color getTextColor() {
        int r = (int) ((NumberSetting) ModuleManager.getModule("Colors").getSetting(0)).getValue();
        int g = (int) ((NumberSetting) ModuleManager.getModule("Colors").getSetting(1)).getValue();
        int b = (int) ((NumberSetting) ModuleManager.getModule("Colors").getSetting(2)).getValue();
        return new Color(r, g, b, 255);
    }

    public static Color getSliderColor() {
        int r = (int) ((NumberSetting) ModuleManager.getModule("Colors").getSetting(3)).getValue();
        int g = (int) ((NumberSetting) ModuleManager.getModule("Colors").getSetting(4)).getValue();
        int b = (int) ((NumberSetting) ModuleManager.getModule("Colors").getSetting(5)).getValue();
        return new Color(r, g, b, 255);
    }

    public static Color getHudColor() {
        int r = (int) ((NumberSetting) ModuleManager.getModule("Colors").getSetting(6)).getValue();
        int g = (int) ((NumberSetting) ModuleManager.getModule("Colors").getSetting(7)).getValue();
        int b = (int) ((NumberSetting) ModuleManager.getModule("Colors").getSetting(8)).getValue();
        return new Color(r, g, b, 255);
    }

    public static Color GetRainbowColor(float p_Hue, float p_Saturation, float p_Lightness, final float p_Alpha)
    {
        if (p_Saturation < 0.0f || p_Saturation > 100.0f)
        {
            throw new IllegalArgumentException("Color parameter outside of expected range - Saturation");
        }
        if (p_Lightness < 0.0f || p_Lightness > 100.0f)
        {
            throw new IllegalArgumentException("Color parameter outside of expected range - Lightness");
        }
        if (p_Alpha < 0.0f || p_Alpha > 1.0f)
        {
            throw new IllegalArgumentException("Color parameter outside of expected range - Alpha");
        }
        p_Hue = (p_Hue %= 360.0f) / 360.0f;
        p_Saturation /= 100.0f;
        p_Lightness /= 100.0f;
        float n5;
        if (p_Lightness < 0.0)
        {
            n5 = p_Lightness * (1.0f + p_Saturation);
        }
        else
        {
            n5 = p_Lightness + p_Saturation - p_Saturation * p_Lightness;
        }
        p_Saturation = 2.0f * p_Lightness - n5;
        p_Lightness = Math.max(0.0f, FutureClientColorCalculation(p_Saturation, n5, p_Hue + 0.33333334f));
        final float max = Math.max(0.0f, FutureClientColorCalculation(p_Saturation, n5, p_Hue));
        p_Saturation = Math.max(0.0f, FutureClientColorCalculation(p_Saturation, n5, p_Hue - 0.33333334f));
        p_Lightness = Math.min(p_Lightness, 1.0f);
        final float min = Math.min(max, 1.0f);
        p_Saturation = Math.min(p_Saturation, 1.0f);
        return new Color(p_Lightness, min, p_Saturation, p_Alpha);
    }

    private static float FutureClientColorCalculation(final float n, final float n2, float n3)
    {
        if (n3 < 0.0f)
        {
            ++n3;
        }
        if (n3 > 1.0f)
        {
            --n3;
        }
        if (6.0f * n3 < 1.0f)
        {
            return n + (n2 - n) * 6.0f * n3;
        }
        if (2.0f * n3 < 1.0f)
        {
            return n2;
        }
        if (3.0f * n3 < 2.0f)
        {
            return n + (n2 - n) * 6.0f * (0.6666667f - n3);
        }
        return n;
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
