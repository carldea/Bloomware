package me.offeex.bloomware.client.module.modules.render;

import com.google.common.eventbus.Subscribe;
import me.offeex.bloomware.api.event.events.EventWorldRender;
import me.offeex.bloomware.api.util.RenderUtil;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.util.math.Box;

public class StorageESP extends Module {

    private final Setting<Boolean> chests = register("Chest", false);
    private final Setting<Boolean> echests = register("EChest", false);
    private final Setting<Boolean> shulkers = register("Shulkers", false);
    private final Setting<Number> chestRed = register("ChestR", 100, 1, 255, 1);
    private final Setting<Number> chestGreen = register("ChestG", 5, 1, 255, 1);
    private final Setting<Number> chestBlue = register("ChestB", 100, 1, 255, 1);
    private final Setting<Number> echestsRed = register("EChestR", 100, 1, 255, 1);
    private final Setting<Number> echestsGreen = register("EChestG", 5, 1, 255, 1);
    private final Setting<Number> echestsBlue = register("EChestB", 100, 1, 255, 1);
    private final Setting<Number> shulkersRed = register("ShulkerR", 100, 1, 255, 1);
    private final Setting<Number> shulkersGreen = register("ShulkerG", 5, 1, 255, 1);
    private final Setting<Number> shulkersBlue = register("ShulkerB", 100, 1, 255, 1);

    public StorageESP() {
        super("StorageESP", "Allows you to see storages", Category.RENDER, false);
    }

    @Subscribe
    public void onRender(EventWorldRender event) {
        if (isEnabled()) {
            assert mc.world != null;

            for (BlockEntity block : mc.world.blockEntities) {
                if (mc.options.getPerspective().isFirstPerson()) {
                    if (block instanceof ChestBlockEntity && chests.getValue()) {
                        RenderUtil.drawFilledBox(new Box(
                                block.getPos().getX() + 0.06, block.getPos().getY(), block.getPos().getZ() + 0.06,
                                block.getPos().getX() + 0.94, block.getPos().getY() + 0.875, block.getPos().getZ() + 0.94), 255 - chestRed.getValue().floatValue(), 255 - chestGreen.getValue().floatValue(), 255 - chestBlue.getValue().floatValue(), 0.7F);
                    }
                    if (block instanceof EnderChestBlockEntity && echests.getValue()) {
                        RenderUtil.drawFilledBox(new Box(
                                block.getPos().getX() + 0.06, block.getPos().getY(), block.getPos().getZ() + 0.06,
                                block.getPos().getX() + 0.94, block.getPos().getY() + 0.875, block.getPos().getZ() + 0.94), 255 - echestsRed.getValue().floatValue(), 255 - echestsGreen.getValue().floatValue(), 255 - echestsBlue.getValue().floatValue(), 0.7F);
                    }
                    if (block instanceof ShulkerBoxBlockEntity && shulkers.getValue()) {
                        RenderUtil.drawFilledBox(new Box(
                                block.getPos().getX() + 0.06, block.getPos().getY(), block.getPos().getZ() + 0.06,
                                block.getPos().getX() + 0.94, block.getPos().getY() + 0.875, block.getPos().getZ() + 0.94), 255 - shulkersRed.getValue().floatValue(), 255 - shulkersGreen.getValue().floatValue(), 255 - shulkersBlue.getValue().floatValue(), 0.7F);
                    }
                }
            }
        }
    }
}
