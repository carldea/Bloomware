package me.offeex.ofx.client.altmanager.widgets;

import me.offeex.ofx.client.altmanager.Account;
import me.offeex.ofx.client.altmanager.screens.EditCrackedAccount;
import me.offeex.ofx.client.altmanager.screens.EditMojangAccount;
import me.offeex.ofx.client.altmanager.screens.EditTokenAccount;
import me.offeex.ofx.client.gui.api.AbstractButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class EditAccountButton extends AbstractButton {
    Account account;
    MinecraftClient mc = MinecraftClient.getInstance();
    public boolean isPressed;
    Identifier edit = new Identifier("ofx", "icons/edit.png");
    Identifier edit2 = new Identifier("ofx", "icons/editpress.png");

    public EditAccountButton(Account account, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.account = account;
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            if (this.account.type.toString().equals("Mojang")) {
                mc.openScreen(new EditMojangAccount(this.account));
            } else if (this.account.type.toString().equals("Token")) {
                mc.openScreen(new EditTokenAccount(this.account));
            } else if (this.account.type.toString().equals("Cracked")) {
                mc.openScreen(new EditCrackedAccount(this.account));
            }
        }
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isPressed) {
            if (!isMouseWithin(mouseX, mouseY)) {
                isPressed = false;
            }
        }

        if (isMouseWithin(mouseX, mouseY)) {
            mc.getTextureManager().bindTexture(edit2);
        } else {
            mc.getTextureManager().bindTexture(edit);
        }

        DrawableHelper.drawTexture(stack, x, y, 0, 0, 0, 17, 17, 17, 17);
    }
}
