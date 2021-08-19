package me.offeex.bloomware.client.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import me.offeex.bloomware.api.event.events.EventPlayerMove;
import me.offeex.bloomware.api.util.MovementUtil;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Sprinting you", Category.MOVEMENT, false);
    }

    public void onDisable() {
        mc.player.setSprinting(false);
    }

    @Subscribe
    public void onPlayerMove(EventPlayerMove e) {
        mc.player.setSprinting(true);
    }
}

