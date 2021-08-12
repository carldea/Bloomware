package me.offeex.bloomware.mixins;

import me.offeex.bloomware.client.altmanager.screens.AltManager;
import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.gui.CustomMainMenu;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text title) {
        super(title);
    }

    private boolean enabled() {
        return Bloomware.moduleManager.getModule("BloomwareMenu").isEnabled();
    }

    MinecraftClient mc = MinecraftClient.getInstance();
    int text1Length;

    @Inject(method = "init", at = @At("TAIL"), cancellable = true)
    private void onInit(CallbackInfo info) {
        if (enabled()) {
            mc.openScreen(new CustomMainMenu());
            info.cancel();
        }
    }

    @Inject(method = "render", cancellable = true, at = @At("TAIL"))
    public void onRender(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo cbi) {
        textRenderer.drawWithShadow(matrices, "BloomWare client made by OffeeX, Rikonardo & DiOnFire", width - text1Length - 3, 3, 0xffffffff);
    }

    @Inject(at = @At("RETURN"), method = "initWidgetsNormal")
    private void addCustomButton(int y, int spacingY, CallbackInfo ci) {
        this.addButton(new ButtonWidget(this.width / 2 - 100 + 205, y, 80, 20, Text.of("Alt Manager"), (buttonWidget) -> this.mc.openScreen(new AltManager())));
    }
}
