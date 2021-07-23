package me.offeex.ofx.client.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import me.offeex.ofx.api.event.events.EventBoatMove;
import me.offeex.ofx.api.util.PlayerDataUtil;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.setting.settings.NumberSetting;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Vec3d;

public class BoatFly extends Module {

    public BoatFly() {
        super("Boat Fly", "Fly using boats.", -1, Category.MOVEMENT, false,
                new NumberSetting("Forward Speed", 1.0, 0.1, 10.0, 0.1),
                new NumberSetting("Up/Down Speed", 0.5, 0.1, 5.0, 0.1),
                new NumberSetting("Fall Speed", 0.0, 0.0, 1.0, 0.1));
    }

    @Override
    public void onTick() {
        if (mc.player.getVehicle() == null || (!(mc.player.getVehicle() instanceof BoatEntity) && mc.player.getVehicle().getPrimaryPassenger() != mc.player)) {
            return;
        }
        BoatEntity vehicle = (BoatEntity) mc.player.getVehicle();
        vehicle.setYaw(mc.player.getYaw(1));

        Vec3d vel = PlayerDataUtil.getHorizontalVelocity(((NumberSetting) settings.get(0)).getValue());
        double velX = vel.getX();
        double velY = 0;
        double velZ = vel.getZ();

        if (mc.options.keyJump.isPressed()) velY += ((NumberSetting) settings.get(1)).getValue() / 20;
        if (mc.options.keySprint.isPressed()) velY -= ((NumberSetting) settings.get(1)).getValue() / 20;
        else velY -= ((NumberSetting) settings.get(2)).getValue() / 20;

        vehicle.setVelocity(velX, velY, velZ);
    }
}
