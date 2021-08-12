package me.offeex.bloomware.client.altmanager.screens;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.altmanager.Account;
import me.offeex.bloomware.client.altmanager.AccountTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.io.IOException;

public class EditCrackedAccount extends Screen {
    private TextFieldWidget nickname;
    MinecraftClient mc = MinecraftClient.getInstance();
    Identifier background = new Identifier("bloomware", "gui/coolbg.png");
    Account account;

    public EditCrackedAccount(Account account) {
        super(Text.of("EditCrackedAccount"));
        this.account = account;
    }

    @Override
    public void init() {
        this.mc.keyboard.setRepeatEvents(true);
        this.nickname = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, this.height / 4 + 105, 200, 20, Text.of(""));
        this.nickname.setMaxLength(20);
        this.nickname.setTextFieldFocused(true);
        this.nickname.setText(account.getLogin());
        this.children.add(this.nickname);
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 120 + 18, 200, 20, Text.of("Add account"), (buttonWidget) -> {
            try {
                Bloomware.accountManager.deleteAccount(account);
                Bloomware.accountManager.saveAccount(new Account(this.nickname.getText(), "", AccountTypes.Cracked));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.mc.openScreen(new AltManager());
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 120 + 40, 200, 20, Text.of("Cancel"), (buttonWidget) -> this.mc.openScreen(new AltManager())));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        mc.getTextureManager().bindTexture(background);
        DrawableHelper.drawTexture(matrices, 0, 0, 0, 0, 0, width, height, height, width);
        this.textRenderer.drawWithShadow(matrices, "Nickname", this.width / 2f - 100, this.height / 4f + 95, 0xFFFFFFFF);
        nickname.render(matrices, mouseX, mouseY, delta);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
