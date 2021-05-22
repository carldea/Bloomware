package me.offeex.ofx.hud.component.components;

import me.offeex.ofx.Main;
import me.offeex.ofx.api.util.MovementUtil;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.hud.component.Component;
import me.offeex.ofx.module.modules.client.HudEditor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class Coordinates extends Component {
    public Coordinates() {
        super("Coords", "Shows current coords", 10, 10, 10, 18);
    }
    int savedX;
    int savedY;

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        String stringX = String.format("%.1f",MinecraftClient.getInstance().player.getX());
        String stringY = String.format("%.1f",MinecraftClient.getInstance().player.getY());
        String stringZ = String.format("%.1f",MinecraftClient.getInstance().player.getZ());

        if (isEnabled()) {

            width = Main.sFontRenderer.getStringWidth("XYZ: " + stringX + ", " + stringY + ", " + stringZ, Main.sFontRenderer.getFontsize()) + 8;
            if (HudEditor.openScreen)
                Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
            Main.sFontRenderer.drawString("XYZ: " + stringX + ", " + stringY + ", " + stringZ, x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
            //        super.y = MinecraftClient.getInstance().getWindow().getScaledHeight() - 24;
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            startDragging(mouseX, mouseY, mouseButton);
        }
        if (mouseButton == 1) {
            x = 8;
            y = MinecraftClient.getInstance().getWindow().getScaledHeight() - 30;
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
        stopDragging(mouseX, mouseY, mouseButton);
    }
}