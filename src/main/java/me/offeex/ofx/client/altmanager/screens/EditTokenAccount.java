package me.offeex.ofx.client.altmanager.screens;

import me.offeex.ofx.Main;
import me.offeex.ofx.client.altmanager.Account;
import me.offeex.ofx.client.altmanager.AccountTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.io.IOException;

public class EditTokenAccount extends Screen {
    Account account;
    private TextFieldWidget username, token;
    MinecraftClient mc = MinecraftClient.getInstance();
    Identifier background = new Identifier("ofx", "gui/coolbg.png");

    public EditTokenAccount(Account account) {
        super(Text.of("EditTokenAccount"));
        this.account = account;
    }

    @Override
    public void init() {
        this.mc.keyboard.setRepeatEvents(true);
        this.username = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, this.height / 4 + 105, 200, 20, Text.of(""));
        this.username.setMaxLength(20);
        this.username.setTextFieldFocused(false);
        this.username.setText(account.getLogin());
        this.children.add(this.username);
        this.token = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, this.height / 4 + 138, 200, 20, Text.of(""));
        this.token.setMaxLength(2000);
        this.token.setTextFieldFocused(false);
        this.token.setText(account.getPassword());
        this.children.add(this.token);
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 160, 200, 20, Text.of("Edit account"), (buttonWidget) -> {
            try {
                Main.accountManager.deleteAccount(account);
                Main.accountManager.saveAccount(new Account(this.username.getText(), this.token.getText(), AccountTypes.Token));
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
        super.render(matrices, mouseX, mouseY, delta);
    }
}
