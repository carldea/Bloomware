package me.offeex.ofx.api.mixin;

import me.offeex.ofx.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.offeex.ofx.api.event.events.EventWorldRender;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

	@Inject(at = @At("HEAD"), method = "renderHand", cancellable = true)
	private void renderHand(MatrixStack matrixStack_1, Camera camera_1, float float_1, CallbackInfo info) {
		EventWorldRender event = new EventWorldRender(float_1);
		Main.EVENTBUS.post(event);
		if (event.isCancelled()) info.cancel();
	}
}