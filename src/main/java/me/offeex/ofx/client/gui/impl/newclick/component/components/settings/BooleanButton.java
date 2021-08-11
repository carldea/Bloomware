package me.offeex.ofx.client.gui.impl.newclick.component.components.settings;

import me.offeex.ofx.api.util.ColorUtils;
import me.offeex.ofx.client.gui.impl.newclick.component.Component;
import me.offeex.ofx.client.gui.impl.newclick.component.components.ModuleButton;
import me.offeex.ofx.client.setting.Setting;
import me.offeex.ofx.client.setting.settings.BooleanSetting;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class BooleanButton extends Component {

    private final BooleanSetting setting;
    private final ModuleButton button;
    private boolean isHovered;
    private int offset;
    private int x;
    private int y;

    public BooleanButton(final Setting setting, final ModuleButton button, final int offset) {
        this.setting = (BooleanSetting) setting;
        this.button = button;
        this.x = button.frame.getX() + button.frame.getWidth();
        this.y = button.frame.getY() + button.offset;
        this.offset = offset;
    }

    @Override
    public void setOffset(final int offset) {
        this.offset = offset;
    }

    @Override
    public void updateComponent(final double mouseX, final double mouseY) {
        isHovered = isHovered(mouseX, mouseY);
        y = button.frame.getY() + this.offset;
        x = button.frame.getX();
    }

    @Override
    public void mouseClicked(final double mouseX, final double mouseY, final int button) {
        if (isHovered(mouseX, mouseY) && button == 0 && this.button.open) {
            setting.setEnabled(!setting.enabled);
        }
    }

    @Override
    public void render() {
        DrawableHelper.fill(new MatrixStack(), button.frame.getX(), button.frame.getY() + offset, button.frame.getX() + button.frame.getWidth(), button.frame.getY() + offset + 12, isHovered ? new Color(0, 0, 0, 150).getRGB() : new Color(0, 0, 0, 130).getRGB());
        mc.textRenderer.draw(new MatrixStack(), setting.getName(), button.frame.getX() + 5, button.frame.getY() + offset + 3, setting.isEnabled()  ?  ColorUtils.getGuiColor().getRGB() : -1);
    }

    public boolean isHovered(final double x, final double y) {
        return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12;
    }

}
