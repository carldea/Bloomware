package me.offeex.bloomware.mixins;

import me.offeex.bloomware.client.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = KeyboardInput.class, priority = Integer.MAX_VALUE)
public abstract class MixinMovementInput extends Input {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/options/KeyBinding;isPressed()Z"))
    public boolean isKeyPressed(KeyBinding keyBinding) {
        if (MinecraftClient.getInstance().player != null
                && ModuleManager.getModule("InventoryMove").isEnabled()
                && !(MinecraftClient.getInstance().currentScreen instanceof ChatScreen)
                && MinecraftClient.getInstance().currentScreen != null
                && MinecraftClient.getInstance().options.keySneak != keyBinding)
            return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), keyBinding.getDefaultKey().getCode()); // InventoryMove

        return keyBinding.isPressed(); // NormalMove
    }
}
