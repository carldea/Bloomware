package me.offeex.bloomware.client.module.modules.combat;

import com.google.common.eventbus.Subscribe;
import me.offeex.bloomware.api.event.events.EventPacket;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import me.offeex.bloomware.mixins.IEntityVelocityUpdateS2CPacket;
import me.offeex.bloomware.mixins.IExplosionS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

public class Velocity extends Module {
    private final Setting<Number> horizontal = register("Horizontal", 0.0, 0.0, 100.0, 1.0);
    private final Setting<Number> vertical = register("Vertical", 0.0, 0.0, 100.0, 1.0);

    public Velocity() {
        super("Velocity", "Changes your velocity.", Category.COMBAT, false);
    }

    @Subscribe
    public void onPacketReceive(EventPacket.Receive event) {
        if (event.getPacket() instanceof EntityVelocityUpdateS2CPacket && mc.player != null) {
            if (horizontal.getValue().doubleValue() == 0 && vertical.getValue().doubleValue() == 0) {
                event.setCancelled(true);
            }
            if (((EntityVelocityUpdateS2CPacket) event.getPacket()).getId() == mc.player.getEntityId()) {
                IEntityVelocityUpdateS2CPacket velocity = (IEntityVelocityUpdateS2CPacket) event.getPacket();
                velocity.setVelocityX(velocity.getVelocityX() * horizontal.getValue().intValue());
                velocity.setVelocityY(velocity.getVelocityY() * vertical.getValue().intValue());
                velocity.setVelocityZ(velocity.getVelocityZ() * horizontal.getValue().intValue());
            }
        }
        if (event.getPacket() instanceof ExplosionS2CPacket) {
            IExplosionS2CPacket velocity = (IExplosionS2CPacket) event.getPacket();
            velocity.setPlayerVelocityX(velocity.getPlayerVelocityX() * horizontal.getValue().intValue());
            velocity.setPlayerVelocityY(velocity.getPlayerVelocityY() * vertical.getValue().intValue());
            velocity.setPlayerVelocityZ(velocity.getPlayerVelocityZ() * horizontal.getValue().intValue());
        }
    }
}
