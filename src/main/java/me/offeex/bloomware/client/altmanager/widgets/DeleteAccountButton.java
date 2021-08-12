package me.offeex.bloomware.client.altmanager.widgets;

import me.offeex.bloomware.client.altmanager.Account;
import me.offeex.bloomware.client.altmanager.screens.DeleteAccount;
import me.offeex.bloomware.client.gui.api.AbstractButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DeleteAccountButton extends AbstractButton {
    Account account;
    MinecraftClient mc = MinecraftClient.getInstance();
    public boolean isPressed;
    Identifier trashCan = new Identifier("bloomware", "icons/trashcan.png");
    Identifier trashCan2 = new Identifier("bloomware", "icons/trashcanpress.png");

    public DeleteAccountButton(Account account, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.account = account;
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            mc.openScreen(new DeleteAccount(this.account));
        }
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isPressed) {
            if (!isMouseWithin(mouseX, mouseY)) isPressed = false;
        }

        if (isMouseWithin(mouseX, mouseY)) {
            mc.getTextureManager().bindTexture(trashCan2);
        } else {
            mc.getTextureManager().bindTexture(trashCan);
        }

        DrawableHelper.drawTexture(stack, x, y, 0, 0, 0, 17, 17, 17, 17);
    }
}
