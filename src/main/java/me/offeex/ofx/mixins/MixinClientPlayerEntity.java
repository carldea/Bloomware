/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDrinker420/bleachhack-1.14/).
 * Copyright (c) 2019 Bleach.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.offeex.ofx.mixins;

import com.mojang.authlib.GameProfile;
import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.event.events.EventPlayerMotionUpdate;
import me.offeex.ofx.api.event.events.EventPlayerMove;
import me.offeex.ofx.api.event.events.EventPlayerUpdate;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.MovementType;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity extends AbstractClientPlayerEntity {

    public MixinClientPlayerEntity(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(at = @At("HEAD"), method = "move", cancellable = true)
    public void move(final MovementType movementType, final Vec3d vec3d, final CallbackInfo info) {
        /**
         * FIXED BY https://github.com/fuckyouthinkimboogieman
         * upd: sniff the bebra
         */
        EventPlayerMove event = new EventPlayerMove(movementType, vec3d);
        Bloomware.EVENTBUS.post(event);
        if (event.isCancelled()) {
            super.move(event.type, event.getVec3d());
            info.cancel();
        }
    }

    @Inject(method = "move", at = @At("RETURN"))
    public void movePost(MovementType type, Vec3d movement, CallbackInfo ci) {
        if ((ClientPlayerEntity)(Object) this  == MinecraftClient.getInstance().player) {
            Bloomware.EVENTBUS.post(new EventPlayerMove(type, movement));
        }
    }

    private EventPlayerMotionUpdate event;

    @Inject(method = "sendMovementPackets", at = @At("HEAD"), cancellable = true)
    public void OnPreUpdateWalkingPlayer(CallbackInfo info) {
        MinecraftClient mc = MinecraftClient.getInstance();
        event = new EventPlayerMotionUpdate(getX(), getY(), getZ(), onGround);
        Bloomware.EVENTBUS.post(event);
        if (event.isCancelled()) {
            Bloomware.EVENTBUS.post(new EventPlayerMotionUpdate.EventPlayerMotionUpdateCancelled(event.getPitch(), event.getYaw()));
            info.cancel();
            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.Both(mc.player.getX(), event.getY(), mc.player.getZ(), event.getYaw(), event.getPitch(), mc.player.isOnGround()));
            postWalkingUpdate();
        }

        if (event.isCancelled())
            info.cancel();
    }

    private void postWalkingUpdate()
    {
        if (event.getFunc() != null)
            event.getFunc().accept((ClientPlayerEntity) (Object) this);

//        event.setEra(MinecraftEvent.Stage.Post);

        Bloomware.EVENTBUS.post(event);
        if (event.isCancelled())
            Bloomware.EVENTBUS.post(new EventPlayerMotionUpdate.EventPlayerMotionUpdateCancelled(event.getPitch(), event.getYaw()));
    }

    @Inject(method = "sendMovementPackets", at = @At("RETURN"))
    public void OnPostUpdateWalkingPlayer(CallbackInfo info)
    {
        postWalkingUpdate();
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onUpdate(CallbackInfo info) {
        Bloomware.EVENTBUS.post(new EventPlayerUpdate());
    }
}