package me.offeex.ofx.api.mixin;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.font.StringRenderer;
import me.offeex.ofx.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.Session;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method = "<init>", at = @At("TAIL"))
    public void minecraftClient(CallbackInfo ci) {
        Main.pFontRenderer = new StringRenderer(23, "/assets/fonts/" + Main.FontMain + ".ttf");
        Main.sFontRenderer = new StringRenderer(18, "/assets/fonts/" + Main.FontMain + ".ttf");
    }


    @Shadow
    @Nullable
    public ClientPlayerEntity player;

    @Shadow
    @Nullable
    public ClientWorld world;

    @Shadow
    @Final
    private Session session;

    @Inject(at = @At("INVOKE"), method = "tick", cancellable = true)
    public void tick(CallbackInfo callbackInfo) {
        if (player != null && world != null) ModuleManager.onTick();
    }

    @Inject(at = @At("TAIL"), method = "getWindowTitle", cancellable = true)
    public void getWindowTitle(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(Main.name + " v" + Main.version);
    }
}
