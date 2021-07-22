package me.offeex.ofx.client.module.modules.hud;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.gui.api.ColorUtils;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.modules.client.ClickGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class ModuleNotifier extends Module {
    public ModuleNotifier() {
        super("Module Notifier", "Adds the window with the notifications about toggling modules", KEY_UNBOUND, Category.HUD, false);
    }

    String message = "";

    public void setMessage(String msg) {
        Thread t = new Thread(() -> {
            message = msg;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.print("hohol");
            }
            message = "";
        });
        t.start();
    }

    private String getMessage() {
        return message;
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            width = Bloomware.sFontRenderer.getStringWidth(Bloomware.moduleNotifier.getMessage(), Bloomware.sFontRenderer.getFontsize()) + 8;
            if (ClickGui.getCurrentScreen() == 2) {
                Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
            }
            Bloomware.sFontRenderer.drawString(Bloomware.moduleNotifier.getMessage(), x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
        }
    }
}
