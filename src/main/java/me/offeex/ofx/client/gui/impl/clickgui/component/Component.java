package me.offeex.ofx.client.gui.impl.clickgui.component;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Component {

    public final static MinecraftClient mc;
    public final static MatrixStack stack = new MatrixStack();

    public void render() { }

    public void updateComponent(double mouseX, double mouseY) { }

    public void mouseClicked(double mouseX, double mouseY, int button) { }

    public void mouseReleased( double mouseX,  double mouseY,  int mouseButton) { }

    public void keyTyped( int key) { }

    public void setOffset( int offset) { }

    public int getHeight() { return 0; }


    static {
        mc = MinecraftClient.getInstance();
    }

}
