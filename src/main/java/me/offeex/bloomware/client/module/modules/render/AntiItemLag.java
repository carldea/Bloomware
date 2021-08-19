package me.offeex.bloomware.client.module.modules.player;

import me.offeex.bloomware.client.command.CommandManager;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.settings.BooleanSetting;
import me.offeex.bloomware.client.setting.settings.NumberSetting;

public class AntiItemLag extends Module {

    private final Setting<Number> maxLength = register("Max Length", 28, 1, 100, 1);
    private final Setting<Boolean> notify = register("Notify", true);

    public AntiItemLag() {
        super("AntiItemLag", "Allows you to bypass lags with too long item name", Category.PLAYER, false);
    }

    @Override
    public void onTick() {
        assert mc.player != null;
        if (mc.player.getMainHandStack().hasCustomName()) {
            if (mc.player.getMainHandStack().getName().asString().length() >= ((NumberSetting) this.getSetting(0)).getValue()) {
                mc.player.getMainHandStack().removeCustomName();
                if (((BooleanSetting) this.getSetting(1)).isEnabled()) {
                    CommandManager.addChatMessage("Found lag item! Fixing...");
                }
            }
        }
    }
}
