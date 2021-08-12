package me.offeex.bloomware.client.altmanager.widgets;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.altmanager.Account;
import me.offeex.bloomware.client.gui.api.AbstractDraggable;
import me.offeex.bloomware.api.util.ColorUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.ArrayList;

public class AccountPanel extends AbstractDraggable {
    public ArrayList<AccountWidget> accounts;
    MinecraftClient mc = MinecraftClient.getInstance();
    boolean isMinimized;

    public AccountPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
        accounts = new ArrayList<>();
        int offsetY = 13;
        for (Account account : Bloomware.accountManager.getAccounts()) {
            accounts.add(new AccountWidget(account, x, y + offsetY, 200, 40));
            offsetY += 40;
        }
        this.height = offsetY;
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        int totalHeight = this.height;
        if (isMinimized)
            totalHeight = 13;
//        if (Bloomware.oldGui.getDragging() == this)
            Screen.fill(stack, x, y, x + width, y + totalHeight, new Color(80, 80, 80, 200).getRGB());
//        else
            Screen.fill(stack, x, y, x + width, y + totalHeight, new Color(0, 0, 0, 150).getRGB());

        Screen.fill(stack, x, y, x + width, y + 14, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 200));
        Screen.fill(stack, x, y + 13, x + width, y + 14, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY, 120));

        Bloomware.pFontRenderer.drawString("Accounts", x + (width / 2f - Bloomware.pFontRenderer.getStringWidth("Accounts", 18) / 2f), y + 3, ColorUtils.Colors.PRIMARY.getRGB(), true);
        int offsetY = 13;

        if (!isMinimized) {
            Identifier minimized = new Identifier("bloomware", "icons/notminimized.png");
            mc.getTextureManager().bindTexture(minimized);
            DrawableHelper.drawTexture(stack, x + width - 10, y + 2, 0, 0, 0, 8, 8, 8, 8);
            for (AccountWidget accountWidget : accounts) {
                accountWidget.x = this.x;
                accountWidget.y = this.y + offsetY;
                accountWidget.draw(stack, mouseX, mouseY, tickDelta);
                offsetY += 40;
            }
        } else {
            Identifier minimized = new Identifier("bloomware", "icons/minimized.png");
            mc.getTextureManager().bindTexture(minimized);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        for (AccountWidget accountWidget : accounts) {
            if (accountWidget.isMouseWithin(mouseX, mouseY)) {
                accountWidget.mouseClicked(mouseX, mouseY, mouseButton);
                return;
            }
        }

        if (mouseButton == 1 && isMouseInside(x, y, width, 13, mouseX, mouseY)) {
            isMinimized = !isMinimized;
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            for (AccountWidget accountWidget : accounts) {
                accountWidget.isPressed = false;
            }
        }
    }
}
