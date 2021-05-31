package me.offeex.ofx.api.mixin;

import com.mojang.authlib.GameProfile;
import me.offeex.ofx.api.util.CapeUtil;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
abstract class MixinAbstractClientPlayerEntity extends PlayerEntity {

    Identifier jex_cape = new Identifier("ofx", "maincape.png");

    public MixinAbstractClientPlayerEntity(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Inject(method = "getCapeTexture", at = @At("HEAD"), cancellable = true)
    public void getCapeTexture(CallbackInfoReturnable<Identifier> cir) {
        String uuid = ((AbstractClientPlayerEntity) (Object) this).getUuidAsString().replace("-", "");
        cir.setReturnValue(jex_cape);
    }

}