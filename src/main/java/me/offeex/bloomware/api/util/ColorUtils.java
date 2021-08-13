package me.offeex.bloomware.api.util;

import me.offeex.bloomware.client.module.ModuleManager;
import me.offeex.bloomware.client.module.modules.client.ClickGui;

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
        int r = ((ClickGui) ModuleManager.getModule("ClickGUI")).r.getValue().intValue();
        int g = ((ClickGui) ModuleManager.getModule("ClickGUI")).g.getValue().intValue();
        int b = ((ClickGui) ModuleManager.getModule("ClickGUI")).b.getValue().intValue();
        return new Color(r, g, b, 190);
    }

    public static Color getTextColor() {
        int r = me.offeex.bloomware.client.module.modules.client.Colors.getInstance().textR.getValue().intValue();
        int g = me.offeex.bloomware.client.module.modules.client.Colors.getInstance().textG.getValue().intValue();
        int b = me.offeex.bloomware.client.module.modules.client.Colors.getInstance().textB.getValue().intValue();
        return new Color(r, g, b, 255);
    }

    public static Color getSliderColor() {
        int r = me.offeex.bloomware.client.module.modules.client.Colors.getInstance().sliderR.getValue().intValue();
        int g = me.offeex.bloomware.client.module.modules.client.Colors.getInstance().sliderG.getValue().intValue();
        int b = me.offeex.bloomware.client.module.modules.client.Colors.getInstance().sliderB.getValue().intValue();
        return new Color(r, g, b, 255);
    }

    public static Color getHudColor() {
        int r = me.offeex.bloomware.client.module.modules.client.Colors.getInstance().hudR.getValue().intValue();
        int g = me.offeex.bloomware.client.module.modules.client.Colors.getInstance().hudG.getValue().intValue();
        int b = me.offeex.bloomware.client.module.modules.client.Colors.getInstance().hudB.getValue().intValue();
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
