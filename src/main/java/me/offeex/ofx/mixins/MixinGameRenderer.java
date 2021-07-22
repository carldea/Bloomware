package me.offeex.ofx.mixins;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.event.events.EventWorldRender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

	@Inject(at = @At("HEAD"), method = "renderHand", cancellable = true)
	private void renderHand(MatrixStack matrixStack_1, Camera camera_1, float flout, CallbackInfo info) {
		/**
		 * FIXED BY https://github.com/fuckyouthinkimboogieman
		 */
		EventWorldRender event = new EventWorldRender(flout);
		Bloomware.EVENTBUS.post(event);
		if (event.isCancelled()) info.cancel();
	}
}