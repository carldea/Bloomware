package me.offeex.bloomware.client.altmanager.screens;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.altmanager.Account;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.io.IOException;

public class DeleteAccount extends Screen {
    Account account;
    Identifier background = new Identifier("bloomware", "gui/coolbg.png");
    MinecraftClient mc = MinecraftClient.getInstance();

    public DeleteAccount(Account account) {
        super(Text.of("EditCrackedAccount"));
        this.account = account;
    }

    @Override
    public void init() {
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 120 + 18, 200, 20, Text.of("Delete account"), (buttonWidget) -> {
            try {
                Bloomware.accountManager.deleteAccount(this.account);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.mc.openScreen(new AltManager());
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 120 + 40, 200, 20, Text.of("Cancel"), (buttonWidget) -> {
            this.mc.openScreen(new AltManager());
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        mc.getTextureManager().bindTexture(background);
        DrawableHelper.drawTexture(matrices, 0, 0, 0, 0, 0, width, height, height, width);
        this.textRenderer.drawWithShadow(matrices, "Nickname", this.width / 2 - 100, this.height / 4 + 95, 0xFFFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
