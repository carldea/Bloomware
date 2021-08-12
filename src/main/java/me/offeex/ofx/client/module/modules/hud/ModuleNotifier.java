package me.offeex.ofx.client.module.modules.hud;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.util.ColorUtils;
import me.offeex.ofx.client.module.Module;
import net.minecraft.client.util.math.MatrixStack;

public class ModuleNotifier extends Module {
    public ModuleNotifier() {
        super("Module Notifier", "Adds the window with the notifications about toggling modules", Category.HUD, true);
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

            Bloomware.sFontRenderer.drawString(Bloomware.moduleNotifier.getMessage(), x + 4, y + 4, ColorUtils.getHudColor().getRGB(), true);
        }
    }
}
