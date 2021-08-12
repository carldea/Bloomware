package me.offeex.ofx.client.module.modules.hud;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.util.ColorUtils;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.modules.client.ClickGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class ItemDurability extends Module {
    private String text;

    public ItemDurability() {
        super("Item Durability", "Allows you to see durability of item on your screen.", Module.Category.HUD, true);
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            assert mc.player != null;
            if (!mc.player.getMainHandStack().isEmpty() || !mc.player.getMainHandStack().isDamageable()) {
                width = (int) (Bloomware.sFontRenderer.getStringWidth("Durability: -", Bloomware.sFontRenderer.getFontsize()));
                text = "Durability: -";
            } else {
                width = (int) (Bloomware.sFontRenderer.getStringWidth("Durability: " + (mc.player.getMainHandStack().getMaxDamage() - mc.player.getMainHandStack().getDamage()), Bloomware.sFontRenderer.getFontsize()));
                text = "Durability: " + (mc.player.getMainHandStack().getMaxDamage() - mc.player.getMainHandStack().getDamage());
            }
//            if (ClickGui.getCurrentScreen() == 2) {
//                Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
//            }
            Bloomware.sFontRenderer.drawString(text, x + 4, y + 4, ColorUtils.getHudColor().getRGB(), true);
        }
    }
}
