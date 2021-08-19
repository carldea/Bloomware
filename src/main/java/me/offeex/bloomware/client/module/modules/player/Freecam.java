package me.offeex.bloomware.client.module.modules.player;

import com.google.common.eventbus.Subscribe;
import com.mojang.authlib.GameProfile;
import me.offeex.bloomware.api.event.events.EventPacket;
import me.offeex.bloomware.api.event.events.EventPlayerMove;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.EntityPose;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3d;

public class Freecam extends Module {
    private final Setting<Number> speed = register("Speed", 1.0, 0.1, 3.0, 0.1);
    private double[] playerPosition;
    private float[] playerRotation;
    private boolean flyingBefore;
    private float flySpeedBefore;

    public Freecam() {
        super("Freecam", "Allows you fly like spectator.", Category.PLAYER, false);
    }

    @Subscribe
    public void onPacket(EventPacket event) {
        if (event.getPacket() instanceof PlayerMoveC2SPacket) {
            event.setCancelled(true);
        }
    }

    @Subscribe
    public void onPlayerMove(EventPlayerMove event) {
        mc.player.noClip = true;
    }

    @Override
    public void onTick() {
        mc.player.setOnGround(false);
        mc.player.abilities.setFlySpeed((float) (speed.getValue().doubleValue() / 5));
        mc.player.abilities.flying = true;
        mc.player.setPose(EntityPose.STANDING);
    }

    @Override
    public void onEnable() {
        playerPosition = new double[] {
            mc.player.getX(),
            mc.player.getY(),
            mc.player.getZ()
        };
        playerRotation = new float[] {
            mc.player.yaw,
            mc.player.pitch
        };

        mc.player.setSprinting(false);

        OtherClientPlayerEntity playerEntity = new OtherClientPlayerEntity(mc.world, new GameProfile(mc.player.getUuid(), mc.getSession().getUsername()));
        playerEntity.copyPositionAndRotation(mc.player);
        playerEntity.yaw = mc.player.yaw;
        mc.world.addEntity(-100, playerEntity);

        flyingBefore = mc.player.abilities.flying;
        flySpeedBefore = mc.player.abilities.getFlySpeed();

        mc.chunkCullingEnabled = false;
    }

    @Override
    public void onDisable() {
        try {
            mc.chunkCullingEnabled = true;
            mc.player.noClip = false;
            mc.world.removeEntity(-100);
            mc.player.abilities.flying = flyingBefore;
            mc.player.abilities.setFlySpeed(flySpeedBefore);
            mc.player.refreshPositionAndAngles(playerPosition[0], playerPosition[1], playerPosition[2], playerRotation[0], playerRotation[1]);
            mc.player.setVelocity(Vec3d.ZERO);
        } catch (NullPointerException exception) {
            disable();
        }
    }
}
