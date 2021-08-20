package me.offeex.bloomware.client.module.modules.hud;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.api.util.ColorUtils;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Coordinates extends Module {
    private final Setting<Boolean> otherDimension = register("OtherDimension", true);

    public Coordinates() {
        super("Coords", "Shows current coords", Category.HUD, true);
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
            if (getDimension() == 2 || !otherDimension.getValue())
                width = Bloomware.sFontRenderer.getStringWidth("XYZ: " + stringX + ", " + stringY + ", " + stringZ, Bloomware.sFontRenderer.getFontsize()) + 8;
            else {
                width = Bloomware.sFontRenderer.getStringWidth("XYZ: " + stringX + ", " + stringY + ", " + stringZ + " (" + stringXN + ", " + stringZN + ")", Bloomware.sFontRenderer.getFontsize()) + 8;
            }
        }

        if (getDimension() == 2 || !otherDimension.getValue())
            Bloomware.sFontRenderer.drawString("XYZ: " + stringX + ", " + stringY + ", " + stringZ, x + 4, y + 4, ColorUtils.getHudColor().getRGB(), true);
        else {
            Bloomware.sFontRenderer.drawString("XYZ: " + stringX + ", " + stringY + ", " + stringZ + " (" + stringXN + ", " + stringZN + ")", x + 4, y + 4, ColorUtils.getHudColor().getRGB(), true);
        }
    }

    public static int getDimension() {
        assert MinecraftClient.getInstance().world != null;
        switch (MinecraftClient.getInstance().world.getRegistryKey().getValue().getPath()) {
            case "the_nether":
                return 1;
            case "the_end":
                return 2;
            default:
                return 0;
        }
    }
}
