package me.offeex.ofx.api.mixin;

import me.offeex.ofx.client.altmanager.screens.AltManager;
import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.gui.CustomMainMenu;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text title) {
        super(title);
    }

    private boolean enabled() {
        boolean nigger = false;
        File dir = new File(MinecraftClient.getInstance().runDirectory, Bloomware.name);
        File dataFile = new File(dir, "config.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String line = reader.readLine();
            while(line != null) {
                String lineN = line;
                line = reader.readLine();
                String[] args = lineN.split(":");
                if (args[1].equals("Bloomware Menu")) {
                    nigger = args[2].equals("true");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nigger;
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
