package me.offeex.ofx.client.gui.impl.settings.components;

import com.google.common.eventbus.Subscribe;
import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.event.events.EventKeyPress;
import me.offeex.ofx.client.command.CommandManager;
import me.offeex.ofx.client.gui.api.AbstractButton;
import me.offeex.ofx.client.gui.api.ColorUtils;
import me.offeex.ofx.client.setting.settings.KeybindSetting;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class GuiKeybindSetting extends AbstractButton {
    KeybindSetting keybindSetting;
    public boolean isPressed;
    private boolean listening = false;
    Thread t;
    public int key = -1;
    private String mainText = "";

    public GuiKeybindSetting(int x, int y, KeybindSetting setting) {
        super(x, y, 100, 13);
        this.keybindSetting = setting;
    }

    @Subscribe
    public void onKeyPressed(EventKeyPress event) {
        if (listening) {
            key = event.getKey();
            CommandManager.addChatMessage(key + "pressed!");
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        isPressed = true;
        if (listening) {
            mainText = "Listening...";
        }
        listening = !listening;
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isPressed) {
            if (!isMouseWithin(mouseX, mouseY)) isPressed = false;
            Screen.fill(stack, x, y, x + width, y + height, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 255));
        }
        else if (isMouseInside(x, y, width, height, mouseX, mouseY)) {
            Screen.fill(stack, x, y, x + width, y + height, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY_DARKER, 120));
        }

        if (key != -1) {
            mainText = "Bind   " + key;
        } else {
            mainText = "Bind   NONE";
        }

        Bloomware.sFontRenderer.drawString(mainText, x + 2, y, ColorUtils.Colors.PRIMARY.getRGB(), true);
    }
}
