package me.offeex.bloomware.client.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import me.offeex.bloomware.api.event.events.EventPlayerMove;
import me.offeex.bloomware.api.util.MovementUtil;
import me.offeex.bloomware.client.module.Module;
import net.minecraft.util.math.Vec3d;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", "Sprinting you", Category.MOVEMENT, false);
    }

    public void onDisable() {
        mc.player.setSprinting(false);
    }

    @Subscribe
    public void onPlayerMove(EventPlayerMove e) {
        if (isEnabled()) {
            if(!mc.player.isSneaking() && mc.player.forwardSpeed > 0.0F) {
                mc.player.setSprinting(true);
            }
        }
    }
}

