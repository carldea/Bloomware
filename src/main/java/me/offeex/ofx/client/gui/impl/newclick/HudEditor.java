package me.offeex.ofx.client.gui.impl.newclick;

import me.offeex.ofx.client.gui.impl.newclick.component.Component;
import me.offeex.ofx.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class HudEditor extends Screen {

    MinecraftClient mc = MinecraftClient.getInstance();
    final Frame frame;

    public HudEditor() {
        super(new LiteralText("Bloomware Gui"));
        int frameX = 10;
        frame = new Frame(Module.Category.HUD);
        frame.setX(frameX);
        frameX += frame.getWidth() + 10;
    }

    @Override
    public boolean isPauseScreen() { return false; }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
        DrawableHelper.fill(new MatrixStack(), 0, 0, mc.getWindow().getScaledWidth(), mc.getWindow().getScaledHeight(), new Color(0, 0, 0, 100).getRGB());
        frame.renderFrame();
        frame.updatePosition(mouseX, mouseY);
        frame.getComponents().forEach(c -> c.updateComponent(mouseX, mouseY));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
            if (frame.isHover(mouseX, mouseY) && mouseButton == 0) {
                frame.setDrag(true);
                frame.dragX = (int) (mouseX - frame.getX());
                frame.dragY = (int) (mouseY - frame.getY());
            }
            if (frame.isHover(mouseX, mouseY) && mouseButton == 1) frame.setOpen(!frame.isOpen());
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int state) {
        frame.setDrag(false);
        if (frame.isOpen() && !frame.getComponents().isEmpty()) {
            for (final Component component : frame.getComponents()) {
                component.mouseReleased(mouseX, mouseY, state);
            }
        }
        return false;
    }

    public void drawGradient(int left, int top, int right, int bottom, int startColor, int endColor) {
        this.fillGradient(new MatrixStack(), left, top, right, bottom, startColor, endColor);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (frame.isOpen() && keyCode != 1 && !frame.getComponents().isEmpty()) {
            for (final Component component : frame.getComponents()) {
                component.keyTyped(keyCode);
            }
        }

        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            mc.openScreen(null);
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

}
