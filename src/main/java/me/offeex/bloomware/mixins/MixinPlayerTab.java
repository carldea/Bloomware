package me.offeex.bloomware.mixins;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.module.ModuleManager;
import me.offeex.bloomware.client.module.modules.render.ExtraTab;
import net.minecraft.client.gui.hud.PlayerListHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PlayerListHud.class)
public class MixinPlayerTab {
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I", ordinal = 0), index = 1)
    private int modifyCount(int count) {
        ExtraTab module = (ExtraTab) ModuleManager.getModule("ExtraTab");
        if (module.isEnabled()) {
            return module.count.getValue().intValue();
        } else {
            return 80;
        }
    }
}
