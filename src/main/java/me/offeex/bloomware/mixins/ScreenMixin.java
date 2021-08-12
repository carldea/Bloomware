package me.offeex.bloomware.mixins;

import me.offeex.bloomware.client.gui.CustomMainMenu;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(Screen.class)
public class ScreenMixin {

    MinecraftClient mc = MinecraftClient.getInstance();
    @Inject(method = "renderBackgroundTexture", at = @At("HEAD"), cancellable = true)
    public void renderBG(int vOffset, CallbackInfo ci) {
        mc.getTextureManager().bindTexture(new Identifier("bloomware", "gui/coolbg.png"));
        int width = mc.getWindow().getScaledWidth();
        int height = mc.getWindow().getScaledHeight();
        DrawableHelper.drawTexture(new MatrixStack(), 0, 0, 0, 0, 0, width, height, height, width);
        if (!(mc.currentScreen instanceof CustomMainMenu)) {

            DrawableHelper.fill(new MatrixStack(), 0, 0, width, height, new Color(0, 0, 0, 60).getRGB());
        }
        ci.cancel();
    }
}
