package me.offeex.ofx.gui.impl.hud.component.components;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.gui.impl.hud.component.Component;
import me.offeex.ofx.module.modules.client.HudEditor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class Fps extends Component {
    public Fps() {
        super("FPS", "Shows the current fps value", 10, 40, 10, 18);
    }

    private static MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            width = Main.sFontRenderer.getStringWidth("FPS: " + mc.fpsDebugString.split(" ")[0], Main.sFontRenderer.getFontsize()) + 8;
            if (HudEditor.openScreen) {
                Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
            }
            Main.sFontRenderer.drawString("FPS: " + mc.fpsDebugString.split(" ")[0], x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
        }
    }


    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
    }
}
