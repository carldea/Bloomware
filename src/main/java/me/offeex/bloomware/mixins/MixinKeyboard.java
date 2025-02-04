package me.offeex.bloomware.mixins;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.api.event.events.EventKeyPress;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.module.ModuleManager;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Keyboard;

@Mixin(Keyboard.class)
public class MixinKeyboard {
	@Inject(method = "onKey", at = @At(value = "INVOKE", target = "net/minecraft/client/util/InputUtil.isKeyPressed(JI)Z", ordinal = 5), cancellable = true)
	private void onKeyEvent(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo callbackInfo) {
		/**
		 * FIXED BY https://github.com/fuckyouthinkimboogieman
		 */

		EventKeyPress event = new EventKeyPress(key, scanCode);
		for (Module module : ModuleManager.getModules()) {
			if (module.getKey() == key) module.toggle();
		}
		Bloomware.EVENTBUS.post(event);
		if (event.isCancelled())
			callbackInfo.cancel();
	}
}