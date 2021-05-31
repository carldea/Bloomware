package me.offeex.ofx.hud.component.components;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.hud.component.Component;
import me.offeex.ofx.module.modules.client.HudEditor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TanyaMazurenko extends Component {
    public TanyaMazurenko() {
        super("Tanya Mazurenko", "oops :^)", 10, 100, 80, 50);
    }

    private static MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            mc.getTextureManager().bindTexture(new Identifier("ofx", "ez.gif"));
            DrawableHelper.drawTexture(stack, (int) (1.5 + 30), 64, 0, 0, 0, 92, 64, 64, 92);
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
