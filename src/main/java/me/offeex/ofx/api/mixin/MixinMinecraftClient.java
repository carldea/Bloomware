package me.offeex.ofx.api.mixin;

import me.offeex.ofx.Main;
import me.offeex.ofx.hud.font.StringRenderer;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method = "<init>", at = @At("TAIL"))
    public void minecraftClient (CallbackInfo ci) {
        Main.pFontRenderer = new StringRenderer(23, "/assets/fonts/" + Main.FontMain + ".ttf");
        Main.sFontRenderer = new StringRenderer(18, "/assets/fonts/" + Main.FontMain + ".ttf");
    }
}
