package me.offeex.ofx.client.gui.impl.newclick.component.components.settings;

import me.offeex.ofx.api.util.ColorUtils;
import me.offeex.ofx.client.gui.impl.newclick.component.Component;
import me.offeex.ofx.client.gui.impl.newclick.component.components.ModuleButton;
import me.offeex.ofx.client.setting.Setting;
import me.offeex.ofx.client.setting.settings.NumberSetting;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SliderButton extends Component {

    private final NumberSetting setting;
    private final ModuleButton button;
    private boolean isHovered;
    private int offset;
    private int x;
    private int y;
    private boolean dragging;
    private double renderWidth;

    public SliderButton(final Setting setting, final ModuleButton button, final int offset) {
        this.setting = (NumberSetting) setting;
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
    public void mouseClicked(final double mouseX, final double mouseY, final int button) {
        if (isHovered(mouseX, mouseY) && button == 0 && this.button.open) {
            dragging = true;
        }
    }

    @Override
    public void mouseReleased(final double mouseX, final double mouseY, final int mouseButton) {
        dragging = false;
    }

    @Override
    public void updateComponent(final double mouseX, final double mouseY) {
        isHovered = isHovered(mouseX, mouseY);
        y = button.frame.getY() + offset;
        x = button.frame.getX();
        final double diff = Math.min(110, Math.max(0, mouseX - x));
        final double min = setting.minimum;
        final double max = setting.maximum;
        renderWidth = 110 * (setting.getValue() - min) / (max - min);
        if (dragging) {
            if (diff == 0) setting.setValue(setting.minimum);
            else {
                final double newValue = round(diff / 110 * (max - min) + min, 1);
                setting.setValue(newValue);
            }
        }
    }

    @Override
    public void render() {
        DrawableHelper.fill(new MatrixStack(), button.frame.getX() + 1, button.frame.getY() + offset, button.frame.getX() + button.frame.getWidth(), button.frame.getY() + offset + 12, isHovered ? new Color(0, 0, 0, 150).getRGB() : new Color(0, 0, 0, 130).getRGB());
        DrawableHelper.fill(new MatrixStack(), button.frame.getX() + 1, button.frame.getY() + offset, (int) (button.frame.getX() + renderWidth), button.frame.getY() + offset + 12,  isHovered ? ColorUtils.getSliderColor().darker().getRGB() : ColorUtils.getSliderColor().getRGB());
        mc.textRenderer.draw(new MatrixStack(), setting.getName(),  button.frame.getX() + 5, button.frame.getY() + offset + 3, isHovered ? new Color(170, 170, 170).getRGB() : -1);
        mc.textRenderer.draw(new MatrixStack(), String.valueOf(round(setting.getValue(), 1)),
                button.frame.getX() + button.frame.getWidth() - 2 - mc.textRenderer.getWidth(String.valueOf(round(setting.getValue(), 1))),
                button.frame.getY() + offset + 3, isHovered ? new Color(170, 170, 170).getRGB() : -1);
    }

    private static double round(final double value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public boolean isHovered(final double x, final double y) {
        return x > this.x && x < this.x + 110 && y > this.y && y < this.y + 12;
    }

}
