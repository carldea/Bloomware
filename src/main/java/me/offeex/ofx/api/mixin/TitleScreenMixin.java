package me.offeex.ofx.api.mixin;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.CustomMainMenu;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.io.*;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text title) {
        super(title);
    }

    MinecraftClient mc = MinecraftClient.getInstance();
    int text1Length;

    @Inject(method = "init", at = @At("TAIL"))
    private void onInit(CallbackInfo info) {

        text1Length = textRenderer.getWidth("BloomWare client made by OffeeX, Rikonardo & DiOnFire");
    }

    @Inject(method = "render", cancellable = true, at = @At("TAIL"))
    public void onRender(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo cbi) {
        textRenderer.drawWithShadow(matrices, "BloomWare client made by OffeeX, Rikonardo & DiOnFire", width - text1Length - 3, 3, 0xffffffff);
    }
}
