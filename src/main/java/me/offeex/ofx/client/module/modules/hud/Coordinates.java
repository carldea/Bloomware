package me.offeex.ofx.client.module.modules.hud;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.util.ColorUtils;
import me.offeex.ofx.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Coordinates extends Module {
    public Coordinates() {
        super("Coords", "Shows current coords", KEY_UNBOUND, Category.HUD, true);
    }

    String stringXN, stringZN;

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        assert MinecraftClient.getInstance().player != null;
        String stringX = String.format("%.1f", MinecraftClient.getInstance().player.getX());
        String stringY = String.format("%.1f", MinecraftClient.getInstance().player.getY());
        String stringZ = String.format("%.1f", MinecraftClient.getInstance().player.getZ());

        switch (getDimension()) {
            case 1:
                stringXN = String.format("%.1f", MinecraftClient.getInstance().player.getX() * 8);
                stringZN = String.format("%.1f", MinecraftClient.getInstance().player.getZ() * 8);
                break;
            case 0:
                stringXN = String.format("%.1f", MinecraftClient.getInstance().player.getX() / 8);
                stringZN = String.format("%.1f", MinecraftClient.getInstance().player.getZ() / 8);
        }

        if (isEnabled()) {
            if (getDimension() == 2)
                width = Bloomware.sFontRenderer.getStringWidth("XYZ: " + stringX + ", " + stringY + ", " + stringZ, Bloomware.sFontRenderer.getFontsize()) + 8;
            else
                width = Bloomware.sFontRenderer.getStringWidth("XYZ: " + stringX + ", " + stringY + ", " + stringZ + " (" + stringXN + ", " + stringZN + ")", Bloomware.sFontRenderer.getFontsize()) + 8;
        }

        if (getDimension() == 2)
            Bloomware.sFontRenderer.drawString("XYZ: " + stringX + ", " + stringY + ", " + stringZ, x + 4, y + 4, ColorUtils.getHudColor().getRGB(), true);
        else
            Bloomware.sFontRenderer.drawString("XYZ: " + stringX + ", " + stringY + ", " + stringZ + " (" + stringXN + ", " + stringZN + ")", x + 4, y + 4, ColorUtils.getHudColor().getRGB(), true);
    }

    public static int getDimension() {
        assert MinecraftClient.getInstance().world != null;
        switch (MinecraftClient.getInstance().world.getRegistryKey().getValue().getPath()) {
            case "the_nether": return 1;
            case "the_end":    return 2;
            default: return 0;
        }
    }
}
