package me.offeex.bloomware.client.gui.impl.clickgui.component.components.settings;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.api.util.ColorUtils;
import me.offeex.bloomware.client.gui.impl.clickgui.component.Component;
import me.offeex.bloomware.client.gui.impl.clickgui.component.components.ModuleButton;
import me.offeex.bloomware.client.setting.Setting;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class BooleanButton extends Component {

    private final Setting<Boolean> setting;
    private final ModuleButton button;
    private boolean isHovered;
    private int offset;
    private int x;
    private int y;

    public BooleanButton(final Setting setting, final ModuleButton button, final int offset) {
        this.setting = setting;
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
            setting.setValue(!setting.getValue());
        }
    }

    @Override
    public void render() {
        DrawableHelper.fill(new MatrixStack(), button.frame.getX(), button.frame.getY() + offset, button.frame.getX() + button.frame.getWidth(), button.frame.getY() + offset + 12, isHovered ? new Color(0, 0, 0, 150).getRGB() : new Color(0, 0, 0, 130).getRGB());
        Bloomware.sFontRenderer.drawString(setting.getName(), button.frame.getX() + 3, button.frame.getY() + offset + 2, setting.getValue() ? ColorUtils.getTextColor().getRGB() : ColorUtils.Colors.WHITE.getRGB(), true);
    }

    public boolean isHovered(final double x, final double y) {
        return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12;
    }

}
