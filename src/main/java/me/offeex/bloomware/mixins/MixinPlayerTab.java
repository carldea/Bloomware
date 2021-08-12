package me.offeex.bloomware.mixins;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.settings.NumberSetting;
import net.minecraft.client.gui.hud.PlayerListHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PlayerListHud.class)
public class MixinPlayerTab {
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I", ordinal = 0), index = 1)
    private int modifyCount(int count) {
        Module module = Bloomware.moduleManager.getModule("ExtraTab");
        if (module.isEnabled()) {
            return (int) ((NumberSetting) module.getSetting(0)).getValue();
        } else {
            return 80;
        }
    }
}
