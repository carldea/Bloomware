package me.offeex.ofx.gui.impl.clickgui;

import me.offeex.ofx.Main;
import me.offeex.ofx.gui.api.AbstractDraggable;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.gui.impl.hud.HudScreen;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.ModuleManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Gav06
 *
 * @since 5/7/2021
 */
public class GuiPanel extends AbstractDraggable {

    Module.Category category;
    ArrayList<GuiModule> modules;
    boolean isMinimized;

    public GuiPanel(Module.Category category, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.category = category;
        modules = new ArrayList<>();

        int offsetY = 13;
        for (Module module : ModuleManager.getModulesByCategory(category)) {
            modules.add(new GuiModule(x, y + offsetY, module, this));
            offsetY += 13;
        }
        this.height = offsetY;
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
//        Чёрный фон на панельке
        int totalHeight = this.height;
        if (isMinimized)
            totalHeight = 13;
        if (this.dragging)
            Screen.fill(stack, x, y, x + width, y + totalHeight, new Color(80, 80, 80, 200).getRGB());
        else
            Screen.fill(stack, x, y, x + width, y + totalHeight, new Color(0, 0, 0, 150).getRGB());

        Screen.fill(stack, x, y, x + width, y + 14, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 200));
        Screen.fill(stack, x, y + 13, x + width, y + 14, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY, 120));

        Main.pFontRenderer.drawString(category.name, x + (width / 2f - Main.pFontRenderer.getStringWidth(category.name, Main.pFontRenderer.getFontsize()) / 2f), y + 3, ColorUtils.Colors.PRIMARY.getRGB(), true);
        int offsetY = 13;

        if (!isMinimized) {
            for (GuiModule module : modules) {
                module.x = this.x;
                module.y = this.y + offsetY;
                module.draw(stack, mouseX, mouseY, tickDelta);
                offsetY += 13;
            }
        }

        
    }

    public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
        for (GuiModule module : modules) {
            if (module.isMouseWithin(mouseX, mouseY)) {
                module.mouseClicked(mouseX, mouseY, mouseButton);
                return;
            }
        }

        if (mouseButton == 1 && isMouseInside(x, y, width, 13, mouseX, mouseY)) {
            isMinimized = !isMinimized;
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0)
        for (GuiModule module : modules) {
            module.isPressed = false;
        }
    }
}
