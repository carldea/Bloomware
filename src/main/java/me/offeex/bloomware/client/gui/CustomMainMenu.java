package me.offeex.bloomware.client.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.realms.gui.screen.RealmsMainScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CustomMainMenu extends Screen {
    Identifier background = new Identifier("bloomware", "gui/coolbg.png");
    MinecraftClient mc = MinecraftClient.getInstance();

    public CustomMainMenu() {
        super(Text.of("nigger"));
    }

    @Override
    protected void init() {
        this.addButton(new ButtonWidget(width - 125, height - 125, 120, 20, Text.of("Singleplayer"), (buttonWidget) -> this.mc.openScreen(new SelectWorldScreen(this))));
        this.addButton(new ButtonWidget(width - 125, height - 100, 120, 20, Text.of("Multiplayer"), (buttonWidget) -> this.mc.openScreen(new MultiplayerScreen(this))));
        this.addButton(new ButtonWidget(width - 125, height - 75, 120, 20, Text.of("Settings"), (buttonWidget) -> this.mc.openScreen(new OptionsScreen(this, mc.options))));
        this.addButton(new ButtonWidget(width - 125, height - 50, 120, 20, Text.of("Realms"), (buttonWidget) -> this.mc.openScreen(new RealmsMainScreen(this))));
        this.addButton(new ButtonWidget(width - 125, height - 25, 120, 20, Text.of("Exit"), (buttonWidget) -> {
            assert this.client != null;
            this.client.scheduleStop();
        }));
        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        mc.getTextureManager().bindTexture(background);
        DrawableHelper.drawTexture(matrices, 0, 0, 0, 0, 0, width, height, height, width);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
