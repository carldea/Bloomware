package me.offeex.bloomware.client.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import me.offeex.bloomware.api.event.events.EventPacket;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.VehicleMoveS2CPacket;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

public class BoatFly extends Module {
    private final Setting<Boolean> packetSend = register("SendPacket", false);
    private final Setting<Number> speed = register("Speed", 0.1, 0.1, 5.0, 0.1);
    private final Setting<Boolean> rotate = register("Rotate", true);
    private Entity boat = null;

    public BoatFly() {
        super("BoatFly", "Allows you fly using boats.", Category.MOVEMENT, false);
    }

    @Override
    public void onDisable() {
        if (boat != null) {
            boat.setNoGravity(false);
        }
    }

    @Override
    public void onTick() {
        boat = mc.player.getVehicle();
        if (boat != null) {
            Vec3d np = boat.getPos();
            float y = mc.player.yaw;
            int mx = 0, my = 0, mz = 0;
            if (mc.options.keyJump.isPressed())
                my++;
            if (mc.options.keyBack.isPressed())
                mz++;
            if (mc.options.keyLeft.isPressed())
                mx--;
            if (mc.options.keyRight.isPressed())
                mx++;
            if (mc.options.keyForward.isPressed())
                mz--;
            double ts = 1;
            double s = Math.sin(Math.toRadians(y));
            double c = Math.cos(Math.toRadians(y));
            double nx = ts * mz * s;
            double nz = ts * mz * -c;
            double ny = ts * my;
            nx += ts * mx * -c;
            nz += ts * mx * -s;
            Vec3d nv3 = new Vec3d(nx, ny, nz);
            np = np.add(nv3.multiply(speed.getValue().doubleValue() / 10));
            boat.updatePosition(np.x, np.y, np.z);
            boat.setVelocity(0, 0, 0);
            nv3 = nv3.multiply(0.1);
            boat.addVelocity(nv3.x, nv3.y, nv3.z);

            if (rotate.getValue()) {
                boat.yaw = mc.player.yaw;
            }

            if (packetSend.getValue()) {
                Objects.requireNonNull(mc.getNetworkHandler()).sendPacket(new VehicleMoveC2SPacket(boat));
            }
        }
    }
}
