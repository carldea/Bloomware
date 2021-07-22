package me.offeex.ofx.api.mixin;

import com.mojang.authlib.GameProfile;
import me.offeex.ofx.Bloomware;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

@Mixin(AbstractClientPlayerEntity.class)
abstract class MixinAbstractClientPlayerEntity extends PlayerEntity {

    Identifier jex_cape = new Identifier("ofx", "maincape.png");
    private final MinecraftClient mc = MinecraftClient.getInstance();

    private boolean enabled() {
        boolean nigger = false;
        File dir = new File(MinecraftClient.getInstance().runDirectory, Bloomware.name);
        File dataFile = new File(dir, "config.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String line = reader.readLine();
            while (line != null) {
                String lineN = line;
                line = reader.readLine();
                String[] args = lineN.split(":");
                if (args[1].equals("Capes")) {
                    nigger = args[2].equals("true");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nigger;
    }

    public MixinAbstractClientPlayerEntity(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Inject(method = "getCapeTexture", at = @At("HEAD"), cancellable = true)
    public void getCapeTexture(CallbackInfoReturnable<Identifier> cir) {
        if (enabled()) {
            GameProfile info = this.getGameProfile();
            UUID uuid = null;
            if (info != null) {
                uuid = this.getGameProfile().getId();
            }
            //if (uuid != null && uuid.toString().equals("ab47040f-6bcb-4c00-9424-238be6eec731")) {
                //String uuid = ((AbstractClientPlayerEntity) (Object) this).getUuidAsString().replace("-", "");
                cir.setReturnValue(jex_cape);
            //}
        }
    }
}