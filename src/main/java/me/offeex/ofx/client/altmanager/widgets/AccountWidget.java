package me.offeex.ofx.client.altmanager.widgets;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.altmanager.Account;
import me.offeex.ofx.client.gui.api.AbstractButton;
import me.offeex.ofx.client.gui.api.ColorUtils;
import net.minecraft.client.util.math.MatrixStack;

public class AccountWidget extends AbstractButton {
    private final Account account;
    String message;
    DeleteAccountButton deleteAccountButton;
    EditAccountButton editAccountButton;
    public boolean isPressed;

    public AccountWidget(Account account, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.account = account;
        deleteAccountButton = new DeleteAccountButton(this.account, x + 180, y + 2, 17, 17);
        editAccountButton = new EditAccountButton(this.account, x + 161, y + 2, 17, 17);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            if (deleteAccountButton.isMouseWithin(mouseX, mouseY)) {
                deleteAccountButton.isPressed = true;
                deleteAccountButton.mouseClicked(mouseX, mouseY, mouseButton);
            } else if (editAccountButton.isMouseWithin(mouseX, mouseY)) {
                editAccountButton.isPressed = true;
                editAccountButton.mouseClicked(mouseX, mouseY, mouseButton);
            } else {
                if (isMouseWithin(mouseX, mouseY)) {
                    isPressed = true;
                    message = account.login();
                }
            }
        }
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isPressed) {
            if (!isMouseInside(x, y, width, height, mouseX, mouseY)) {
                isPressed = false;
            }
        }
        Bloomware.sFontRenderer.drawString(account.getLogin(), x + 1, y + 2, ColorUtils.Colors.WHITE.getRGB(), true);
        Bloomware.sFontRenderer.drawString(account.type.toString(), x + 1, y + 15, ColorUtils.Colors.WHITE.getRGB(), true);
        if (message != null) {
            if (message.equals("Successful login!")) {
                Bloomware.sFontRenderer.drawString(message, x + 1, y + 28, ColorUtils.Colors.GREEN.getRGB(), true);
            } else {
                Bloomware.sFontRenderer.drawString(message, x + 1, y + 28, ColorUtils.Colors.RED.getRGB(), true);
            }
        }
        deleteAccountButton.x = this.x + 180;
        deleteAccountButton.y = this.y + 2;
        deleteAccountButton.draw(stack, mouseX, mouseY, tickDelta);
        editAccountButton.x = this.x + 161;
        editAccountButton.y = this.y + 2;
        editAccountButton.draw(stack, mouseX, mouseY, tickDelta);
    }
}
