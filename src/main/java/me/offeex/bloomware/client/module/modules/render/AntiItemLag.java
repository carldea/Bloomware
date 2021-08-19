package me.offeex.bloomware.client.module.modules.render;

import me.offeex.bloomware.client.command.CommandManager;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;

public class AntiItemLag extends Module {

    private final Setting<Number> maxLength = register("MaxLength", 28, 1, 100, 1);
    private final Setting<Boolean> notify = register("Notify", true);

    public AntiItemLag() {
        super("AntiItemLag", "Allows you to bypass lags with too long item name", Category.PLAYER, false);
    }

    @Override
    public void onTick() {
        assert mc.player != null;
        if (mc.player.getMainHandStack().hasCustomName()) {
            if (mc.player.getMainHandStack().getName().asString().length() >= maxLength.getValue().intValue()) {
                mc.player.getMainHandStack().removeCustomName();
                if (notify.getValue()) {
                    CommandManager.addChatMessage("Found lag item! Fixing...");
                }
            }
        }
    }
}
