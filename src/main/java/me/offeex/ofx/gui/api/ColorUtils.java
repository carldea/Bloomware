package me.offeex.ofx.gui.api;

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

    public static class Colors {
        public static final Color PRIMARY = Color.decode("#D6ED17");
        public static final Color PRIMARY_DARKER = Color.decode("#717d0f");
        public static final Color SECONDARY = Color.decode("#1c1c1c");
        public static final Color WHITE = Color.WHITE;
        public static final Color GRAY = Color.GRAY;
        public static final Color BLACK = Color.BLACK;
    }
}
