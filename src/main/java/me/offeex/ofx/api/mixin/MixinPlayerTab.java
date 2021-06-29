package me.offeex.ofx.api.mixin;

import me.offeex.ofx.Main;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.setting.settings.NumberSetting;
import net.minecraft.client.gui.hud.PlayerListHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PlayerListHud.class)
public class MixinPlayerTab {
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I", ordinal = 0), index = 1)
    private int modifyCount(int count) {
        Module module = Main.moduleManager.getModule("Extra Tab");
        if (module.isEnabled()) {
            return (int) ((NumberSetting) module.getSetting(0)).getValue();
        } else {
            return 80;
        }
    }
}
