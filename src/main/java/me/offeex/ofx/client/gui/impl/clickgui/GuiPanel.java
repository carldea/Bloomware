package me.offeex.ofx.client.gui.impl.clickgui;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.gui.api.AbstractDraggable;
import me.offeex.ofx.client.gui.api.ColorUtils;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.ArrayList;

public class GuiPanel extends AbstractDraggable {

    private final Module.Category category;
    ArrayList<GuiModule> modules;
    MinecraftClient mc = MinecraftClient.getInstance();
    boolean isMinimized;

    public GuiPanel(Module.Category category, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.category = category;
        modules = new ArrayList<>();

        int offsetY = 17;
        for (Module module : ModuleManager.getModulesByCategory(category)) {
            modules.add(new GuiModule(x, y + offsetY, module, this));
            offsetY += 17;
        }
        this.height = offsetY;
    }

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
//        Чёрный фон на панельке
        int totalHeight = this.height;
        if (isMinimized)
            totalHeight = 17;
        if (Bloomware.guiscreen.getDragging() == this)
            Screen.fill(stack, x, y, x + width, y + totalHeight, new Color(80, 80, 80, 200).getRGB());
        else
            Screen.fill(stack, x, y, x + width, y + totalHeight, new Color(0, 0, 0, 150).getRGB());

        Screen.fill(stack, x, y, x + width, y + 14, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 200));
        Screen.fill(stack, x, y + 13, x + width, y + 14, ColorUtils.withTransparency(ColorUtils.Colors.PRIMARY, 120));

        Bloomware.pFontRenderer.drawString(category.getName(), x + (width / 2f - Bloomware.pFontRenderer.getStringWidth(category.getName(), Bloomware.pFontRenderer.getFontsize()) / 2f), y + 3, ColorUtils.Colors.PRIMARY.getRGB(), true);
        int offsetY = 17;

        if (!isMinimized) {
            Identifier minimized = new Identifier("ofx", "icons/notminimized.png");
            mc.getTextureManager().bindTexture(minimized);
            DrawableHelper.drawTexture(stack, x + width - 10, y + 2, 0, 0, 0, 8, 8, 8, 8);
            for (GuiModule module : modules) {
                module.x = this.x;
                module.y = this.y + offsetY;
                module.draw(stack, mouseX, mouseY, tickDelta);
                if (module.module.getSettings().size() > 1) {
                    Identifier settingIcon = new Identifier("ofx", "icons/setting.png");
                    mc.getTextureManager().bindTexture(settingIcon);
                    DrawableHelper.drawTexture(stack, module.x + module.width - 12, module.y + 3, 0, 0, 0, 10, 10, 10, 10);
                }
                offsetY += 17;
            }
        }

        if (isMinimized) {
            Identifier minimized = new Identifier("ofx", "icons/minimized.png");
            mc.getTextureManager().bindTexture(minimized);
            DrawableHelper.drawTexture(stack, x + width - 10, y + 2, 0, 0, 0, 8, 8, 8, 8);
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

    public Module.Category getCategory() {
        return category;
    }
}
