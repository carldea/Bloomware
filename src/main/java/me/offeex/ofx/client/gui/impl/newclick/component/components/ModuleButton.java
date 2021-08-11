package me.offeex.ofx.client.gui.impl.newclick.component.components;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.util.ColorUtils;
import me.offeex.ofx.client.gui.impl.newclick.component.Component;
import me.offeex.ofx.client.gui.impl.newclick.Frame;
import me.offeex.ofx.client.gui.impl.newclick.component.components.settings.BooleanButton;
import me.offeex.ofx.client.gui.impl.newclick.component.components.settings.KeyButton;
import me.offeex.ofx.client.gui.impl.newclick.component.components.settings.ModeButton;
import me.offeex.ofx.client.gui.impl.newclick.component.components.settings.SliderButton;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.setting.Setting;
import me.offeex.ofx.client.setting.settings.BooleanSetting;
import me.offeex.ofx.client.setting.settings.KeybindSetting;
import me.offeex.ofx.client.setting.settings.ModeSetting;
import me.offeex.ofx.client.setting.settings.NumberSetting;
import net.minecraft.client.gui.DrawableHelper;

import java.awt.*;
import java.util.ArrayList;

public class ModuleButton extends Component {

    public Module module;
    public Frame frame;
    public int offset;
    private boolean isHovered;
    private boolean isPressed;
    private final ArrayList<Component> components;
    public boolean open;

    public ModuleButton(Module module, Frame frame, int offset) {
        this.module = module;
        this.frame = frame;
        this.offset = offset;
        this.components = new ArrayList<>();
        this.open = false;
        int settingY = this.offset + 12;
        if (!module.settings.isEmpty()) {
            for (Setting setting : module.settings) {
                if (setting instanceof ModeSetting) {
                    components.add(new ModeButton(setting, this, settingY));
                } else if (setting instanceof BooleanSetting) {
                    components.add(new BooleanButton(setting, this, settingY));
                } else if (setting instanceof NumberSetting) {
                    components.add(new SliderButton(setting, this, settingY));
                } else if (setting instanceof KeybindSetting) {
                    components.add(new KeyButton(this, settingY));
                }
            }
        }
    }

    @Override
    public void setOffset(final int offset) {
        this.offset = offset;
        int settingY = this.offset + 12;
        for (Component c : components) {
            c.setOffset(settingY);
            settingY += 12;
        }
    }

    @Override
    public int getHeight() {
        if (open) return 12 * (components.size() + 1);
        return 12;
    }

    @Override
    public void updateComponent(final double mouseX, final double mouseY) {
        isHovered = isHovered(mouseX, mouseY);
        if (!components.isEmpty()) {
            components.forEach(c -> c.updateComponent(mouseX, mouseY));
        }
    }

    @Override
    public void mouseClicked(final double mouseX, final double mouseY, final int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            module.toggle();
            isPressed = true;
        }
        if (isHovered(mouseX, mouseY) && button == 1) {
            open = !open;
            frame.update();
        }
        components.forEach(c -> c.mouseClicked(mouseX, mouseY, button));
    }

    @Override
    public void mouseReleased(final double mouseX, final double mouseY, final int mouseButton) {
        components.forEach(c -> c.mouseReleased(mouseX, mouseY, mouseButton));
        isPressed = false;
    }

    @Override
    public void keyTyped(final int key) {
        for (Component c : components) {
            c.keyTyped(key);
        }
    }

    @Override
    public void render() {
        if (isPressed)
            DrawableHelper.fill(stack, frame.getX(), frame.getY() + offset, frame.getX() + frame.getWidth(), frame.getY() + offset + 12, isHovered ? ColorUtils.getGuiColor().getRGB() : new Color(0, 0, 0, 70).getRGB());
        else
            DrawableHelper.fill(stack, frame.getX(), frame.getY() + offset, frame.getX() + frame.getWidth(), frame.getY() + offset + 12, isHovered ? ColorUtils.getGuiColor().darker().getRGB() : new Color(0, 0, 0, 70).getRGB());

        if (this.module.settings.size() > 1) {
            DrawableHelper.fill(stack, frame.getX() + 107, frame.getY() + offset + 2, frame.getX() + 110, frame.getY() + offset + 10, ColorUtils.getGuiColor().getRGB());
        }

        Bloomware.sFontRenderer.drawString(module.getName(), frame.getX() + 3, frame.getY() + offset + 2, module.isEnabled() ? ColorUtils.getTextColor().getRGB() : ColorUtils.Colors.WHITE.getRGB(), true);
        if(open) components.forEach(Component::render);
    }

    public boolean isHovered(final double x, final double y) {
        return x > this.frame.getX() && x < this.frame.getX() + this.frame.getWidth() && y > this.frame.getY() + this.offset && y < this.frame.getY() + 12 + this.offset;
    }
}