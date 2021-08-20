package me.offeex.bloomware.client.module.modules.combat;

import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import net.minecraft.text.Text;

public class AutoLog extends Module {
    private final Setting<Boolean> disableAfterLog = register("DisableAfterLogout", true);
    private final Setting<Number> health = register("Health", 2.0, 1.0, 20.0, 1.0);

    public AutoLog() {
        super("AutoLog", "Disconnects you when your health is low.", Category.COMBAT, false);
    }

    @Override
    public void onTick() {
        if (mc.player.getHealth() <= health.getValue().doubleValue()) {
            mc.getNetworkHandler().getConnection().disconnect(Text.of("Kicked due AutoLog."));
            if (disableAfterLog.getValue()) {
                this.disable();
            }
        }
    }
}
