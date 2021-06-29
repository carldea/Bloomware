package me.offeex.ofx.altmanager.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AltManager extends Screen {
    MinecraftClient mc = MinecraftClient.getInstance();

    Identifier background = new Identifier("ofx", "coolbg.png");

    public AltManager() {
        super(Text.of("AltManager"));
    }

    @Override
    public void init() {
        this.addButton(new ButtonWidget(width - 125, height - 125, 120, 20, Text.of("Login"), (buttonWidget) -> this.mc.openScreen(new AddMojangAccount())));
        this.addButton(new ButtonWidget(width - 125, height - 100, 120, 20, Text.of("Login Offline"), (buttonWidget) -> this.mc.openScreen(new AddCrackedAccount())));
        this.addButton(new ButtonWidget(width - 125, height - 75, 120, 20, Text.of("Delete"), (buttonWidget) -> this.mc.openScreen(new SelectWorldScreen(this))));
        this.addButton(new ButtonWidget(width - 125, height - 50, 120, 20, Text.of("Edit"), (buttonWidget) -> this.mc.openScreen(new SelectWorldScreen(this))));
        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        mc.getTextureManager().bindTexture(background);
        DrawableHelper.drawTexture(matrices, 0, 0, 0, 0, 0, width, height, height, width);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
