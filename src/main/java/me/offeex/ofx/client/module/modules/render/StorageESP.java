package me.offeex.ofx.client.module.modules.render;

import com.google.common.eventbus.Subscribe;
import me.offeex.ofx.api.event.events.EventWorldRender;
import me.offeex.ofx.api.util.RenderUtil;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.setting.settings.BooleanSetting;
import me.offeex.ofx.client.setting.settings.NumberSetting;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.util.math.Box;
import org.lwjgl.glfw.GLFW;

public class StorageESP extends Module {
    public StorageESP() {
        super("StorageESP", "Allows you to see storages", GLFW.GLFW_KEY_UNKNOWN, Category.RENDER, false,
                new BooleanSetting("Chest", false),
                new BooleanSetting("E-Chest", false),
                new BooleanSetting("Shulker", false),
                new NumberSetting("Chest (R)", 100, 1, 255, 1),
                new NumberSetting("Chest (G)", 5, 1, 255, 1),
                new NumberSetting("Chest (B)", 100, 1, 255, 1),
                new NumberSetting("E-Chest (R)", 100, 1, 255, 1),
                new NumberSetting("E-Chest (G)", 5, 1, 255, 1),
                new NumberSetting("E-Chest (B)", 100, 1, 255, 1),
                new NumberSetting("Shulker (R)", 100, 1, 255, 1),
                new NumberSetting("Shulker (G)", 5, 1, 255, 1),
                new NumberSetting("Shulker (B)", 100, 1, 255, 1));
    }

    @Subscribe
    public void onRender(EventWorldRender event) {
        if (isEnabled()) {
            assert mc.world != null;

            for (BlockEntity block : mc.world.blockEntities) {
                if (mc.options.getPerspective().isFirstPerson()) {
                    if (block instanceof ChestBlockEntity && ((BooleanSetting) this.getSetting(0)).isEnabled()) {
                        RenderUtil.drawFilledBox(new Box(
                                block.getPos().getX() + 0.06, block.getPos().getY(), block.getPos().getZ() + 0.06,
                                block.getPos().getX() + 0.94, block.getPos().getY() + 0.875, block.getPos().getZ() + 0.94), 255 - (float) ((NumberSetting) this.getSetting(3)).getValue(), 255 - (float) ((NumberSetting) this.getSetting(4)).getValue(), 255 - (float) ((NumberSetting) this.getSetting(5)).getValue(), 0.7F);
                    }
                    if (block instanceof EnderChestBlockEntity && ((BooleanSetting) this.getSetting(1)).isEnabled()) {
                        RenderUtil.drawFilledBox(new Box(
                                block.getPos().getX() + 0.06, block.getPos().getY(), block.getPos().getZ() + 0.06,
                                block.getPos().getX() + 0.94, block.getPos().getY() + 0.875, block.getPos().getZ() + 0.94), 255 - (float) ((NumberSetting) this.getSetting(6)).getValue(), 255 - (float) ((NumberSetting) this.getSetting(7)).getValue(), 255 - (float) ((NumberSetting) this.getSetting(8)).getValue(), 0.7F);
                    }
                    if (block instanceof ShulkerBoxBlockEntity && ((BooleanSetting) this.getSetting(2)).isEnabled()) {
                        RenderUtil.drawFilledBox(new Box(
                                block.getPos().getX() + 0.06, block.getPos().getY(), block.getPos().getZ() + 0.06,
                                block.getPos().getX() + 0.94, block.getPos().getY() + 0.875, block.getPos().getZ() + 0.94), 255 - (float) ((NumberSetting) this.getSetting(9)).getValue(), 255 - (float) ((NumberSetting) this.getSetting(10)).getValue(), 255 - (float) ((NumberSetting) this.getSetting(11)).getValue(), 0.7F);
                    }
                }
            }
        }
    }
}
