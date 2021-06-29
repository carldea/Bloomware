package me.offeex.ofx.altmanager.screens;

import me.offeex.ofx.Main;
import me.offeex.ofx.altmanager.Account;
import me.offeex.ofx.altmanager.AccountTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.io.IOException;

public class AddMojangAccount extends Screen {
    private TextFieldWidget email, password;
    MinecraftClient mc = MinecraftClient.getInstance();
    Identifier background = new Identifier("ofx", "coolbg.png");

    public AddMojangAccount() {
        super(Text.of("AddMojangAccount"));
    }

    @Override
    public void init() {
        this.mc.keyboard.setRepeatEvents(true);
        this.email = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, this.height / 4 + 105, 200, 20, Text.of(""));
        this.email.setMaxLength(20);
        this.email.setTextFieldFocused(true);
        this.email.setText("");
        this.children.add(this.email);
        this.password = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, this.height / 4 + 138, 200, 20, Text.of(""));
        this.password.setMaxLength(20);
        this.password.setTextFieldFocused(true);
        this.password.setText("");
        this.children.add(this.password);
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 160, 200, 20, Text.of("Add account"), (buttonWidget) -> {
            try {
                Main.accountManager.savePremium(new Account(this.email.getText(), this.password.getText(), AccountTypes.Mojang));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.mc.openScreen(new AltManager());
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 182, 200, 20, Text.of("Cancel"), (buttonWidget) -> this.mc.openScreen(new AltManager())));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        mc.getTextureManager().bindTexture(background);
        DrawableHelper.drawTexture(matrices, 0, 0, 0, 0, 0, width, height, height, width);
        email.render(matrices, mouseX, mouseY, delta);
        password.render(matrices, mouseX, mouseY, delta);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
