package me.offeex.ofx.mixins;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.event.events.EventDrawOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(InGameHud.class)
public class MixinIngameHud {

	@Inject(at = @At(value = "RETURN"), method = "render", cancellable = true)
	public void render(MatrixStack matrixStack, float float_1, CallbackInfo info) {
		/**
		 * FIXED BY https://github.com/fuckyouthinkimboogieman
		 * Пиздатый евентбус от гугла, рендер не работает, заебись
		 */

		EventDrawOverlay event = new EventDrawOverlay(matrixStack);
		Bloomware.EVENTBUS_ALPINE.post(event);
		if (event.isCancelled())
			info.cancel();
	}
}