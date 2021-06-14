package me.offeex.ofx.gui.impl.settings;

import me.offeex.ofx.gui.api.AbstractDraggable;
import me.offeex.ofx.module.Module;
import net.minecraft.client.util.math.MatrixStack;

public class SettingWindow extends AbstractDraggable {
    private final Module module;
    private boolean pinned;

    public SettingWindow(Module m, int x, int y) {
        super(x, y, 100, 50);
        this.module = m;
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {

    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int mouseButton) {

    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {

    }

    public Module getModule() {
        return module;
    }

    public boolean isPinned() {
        return pinned;
    }
}
