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

public class AddTokenAccount extends Screen {
    private TextFieldWidget username, token;
    MinecraftClient mc = MinecraftClient.getInstance();
    Identifier background = new Identifier("bloomware", "gui/coolbg.png");

    public AddTokenAccount() {
        super(Text.of("AddTokenAccount"));
    }

    @Override
    public void init() {
        this.mc.keyboard.setRepeatEvents(true);
        this.username = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, this.height / 4 + 105, 200, 20, Text.of(""));
        this.username.setMaxLength(20);
        this.username.setTextFieldFocused(false);
        this.username.setText("");
        this.children.add(this.username);
        this.token = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, this.height / 4 + 138, 200, 20, Text.of(""));
        this.token.setMaxLength(2000);
        this.token.setTextFieldFocused(false);
        this.token.setText("");
        this.children.add(this.token);
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 160, 200, 20, Text.of("Add account"), (buttonWidget) -> {
            try {
                Bloomware.accountManager.saveAccount(new Account(this.username.getText(), this.token.getText(), AccountTypes.Token));
            } catch (IOException ignored) {}
            this.mc.openScreen(new AltManager());
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 182, 200, 20, Text.of("Cancel"), (buttonWidget) -> this.mc.openScreen(new AltManager())));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        mc.getTextureManager().bindTexture(background);
        DrawableHelper.drawTexture(matrices, 0, 0, 0, 0, 0, width, height, height, width);
        username.render(matrices, mouseX, mouseY, delta);
        token.render(matrices, mouseX, mouseY, delta);
        this.textRenderer.drawWithShadow(matrices, "Nickname", this.width / 2 - 100, this.height / 4 + 95, 0xFFFFFFFF);
        this.textRenderer.drawWithShadow(matrices, "Token", this.width / 2 - 100, this.height / 4 + 128, 0xFFFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
