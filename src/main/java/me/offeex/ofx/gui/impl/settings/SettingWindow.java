package me.offeex.ofx.gui.impl.settings;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.AbstractButton;
import me.offeex.ofx.gui.api.AbstractDraggable;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.gui.impl.settings.components.*;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.setting.Setting;
import me.offeex.ofx.setting.settings.BooleanSetting;
import me.offeex.ofx.setting.settings.KeybindSetting;
import me.offeex.ofx.setting.settings.ModeSetting;
import me.offeex.ofx.setting.settings.NumberSetting;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.util.ArrayList;

public class SettingWindow extends AbstractDraggable {
    private final Module module;
    public boolean pinned;
    public int index;
    private final int offset;
    public ArrayList<Setting> settings;
    public ArrayList<AbstractButton> guiSettings = new ArrayList<>();

    public SettingWindow(Module m, int x, int y, int index) {
        super(x, y, 100, 13 * (m.getCountOfSettings() + 1));
        this.module = m;
        this.index = index;
        offset = module.getCountOfSettings() * 13;
        if (m.getSettings() != null) {
            int i = 1;
            for (Setting setting : m.getSettings()) {
                if (setting instanceof ModeSetting) {
                    GuiModeSetting g = new GuiModeSetting(x + 2, y + i * 13, (ModeSetting) setting);
                    guiSettings.add(g);
                } else if (setting instanceof BooleanSetting) {
                    GuiBooleanSetting g = new GuiBooleanSetting(x + 2, y + i * 13, (BooleanSetting) setting);
                    guiSettings.add(g);
                } else if (setting instanceof NumberSetting) {
                    GuiNumberSetting g = new GuiNumberSetting(x + 2, y + i * 13, (NumberSetting) setting);
                    guiSettings.add(g);
                } else if (setting instanceof KeybindSetting) {
                    GuiKeybindSetting g = new GuiKeybindSetting(x + 2, y + i * 13, (KeybindSetting) setting);
                    guiSettings.add(g);
                }
                i++;
            }
            CloseButton closeButton = new CloseButton(x + 88, y + 2, this);
            guiSettings.add(closeButton);
//            PinButton pinButton = new PinButton(x + 76, y + 2, this);
//            guiSettings.add(pinButton);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (module.getSettings() != null) {
            for (AbstractButton setting : guiSettings) {
                if (setting.isMouseWithin(mouseX, mouseY)) {
                    setting.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            for (AbstractButton setting : guiSettings) {
                if (setting instanceof GuiModeSetting) {
                    ((GuiModeSetting) setting).isPressed = false;
                } else if (setting instanceof GuiBooleanSetting) {
                    ((GuiBooleanSetting) setting).isPressed = false;
                } else if (setting instanceof GuiNumberSetting) {
                    ((GuiNumberSetting) setting).isPressed = false;
                } else if (setting instanceof CloseButton) {
                    ((CloseButton) setting).isPressed = false;
                } else if (setting instanceof GuiKeybindSetting) {
                    ((GuiKeybindSetting) setting).isPressed = false;
                } else if (setting instanceof PinButton) {
                    ((PinButton) setting).isPressed = false;
                }
            }
        }
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (Main.guiscreen.getDragging() == this) {
            Screen.fill(stack, x, y, x + width, y + offset, new Color(80, 80, 80, 200).getRGB());
        } else {
            Screen.fill(stack, x, y, x + width, y + offset, new Color(0, 0, 0, 150).getRGB());
        }
        Screen.fill(stack, x, y, x + width, y + 14, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 200));
        Screen.fill(stack, x, y + 13, x + width, y + 14, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY, 120));

        if (module.getSettings() != null) {
            int offsetY = 13;
            for (AbstractButton setting : guiSettings) {
                if (setting instanceof CloseButton) {
                    setting.x = this.x + 88;
                    setting.y = this.y + 2;
                    setting.draw(stack, mouseX, mouseY, tickDelta);
                } else if (setting instanceof PinButton) {
                    setting.x = this.x + 76;
                    setting.y = this.y + 2;
                    setting.draw(stack, mouseX, mouseY, tickDelta);
                } else {
                    setting.x = this.x;
                    setting.y = this.y + offsetY + 2;
                    setting.draw(stack, mouseX, mouseY, tickDelta);
                    offsetY += 13;
                }
            }
        }
        Main.sFontRenderer.drawString(module.getName(), x + 2, y + 2, ColorUtils.Colors.PRIMARY.getRGB(), true);
    }

    public Module getModule() {
        return module;
    }

    public boolean isPinned() {
        return pinned;
    }
}
