package me.offeex.ofx.gui.impl.hud.component.components;

import me.offeex.ofx.gui.impl.hud.component.Component;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TanyaMazurenko extends Component {
    public TanyaMazurenko() {
        super("Tanya Mazurenko", "oops :^)", 10, 100, 80, 50);
    }

    private static MinecraftClient mc = MinecraftClient.getInstance();
    Identifier tanya = new Identifier("ofx", "ez.gif");

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            mc.getTextureManager().bindTexture(tanya);
            DrawableHelper.drawTexture(stack, (int) (1.5 + 30), 64, 0, 0, 0, 92, 64, 64, 92);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
    }
}
