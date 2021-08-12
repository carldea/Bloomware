package me.offeex.bloomware.client.module.modules.misc;

import com.mojang.authlib.GameProfile;
import me.offeex.bloomware.client.module.Module;
import net.minecraft.client.network.OtherClientPlayerEntity;

import java.util.UUID;

public class FakePlayer extends Module {
    public FakePlayer() {
        super("FakePlayer", "Summons a fake player entity", Category.MISC, false);
    }
    public String nickname = "TheFakeDiOnFire", UUID_Data = "ab47040f-6bcb-4c00-9424-238be6eec731";

    @Override
    public void onEnable() {
        if (mc.world != null && mc.player != null) {
            OtherClientPlayerEntity player = new OtherClientPlayerEntity(mc.world, new GameProfile(UUID.fromString(UUID_Data), nickname));
            player.copyPositionAndRotation(mc.player);
            player.setHeadYaw(mc.player.headYaw);
            mc.world.addEntity(-100, player);
        } else {
            disable();
        }
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) disable();
    }

    @Override
    public void onDisable() {
        if (mc.world != null) {
            mc.world.removeEntity(-100);
        }
    }
}
