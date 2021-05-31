package me.offeex.ofx.gui.api;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import org.lwjgl.opengl.GL32;

import java.awt.Color;

import static org.lwjgl.opengl.GL11.*;

public class RenderHelper {

    public void prepareGL(float lineWidth) {
        GlStateManager.pushMatrix();
        glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
        RenderSystem.blendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ZERO, GL_ONE);
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        RenderSystem.disableDepthTest();
        glDisable(GL_TEXTURE_2D);
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        RenderSystem.enableAlphaTest();
        glEnable(GL_LINE_SMOOTH);
        glEnable(GL32.GL_DEPTH_CLAMP);
        glLineWidth(lineWidth);
    }

    public void releaseGL() {
        glDisable(GL32.GL_DEPTH_CLAMP);
        glDisable(GL_LINE_SMOOTH);
        RenderSystem.disableAlphaTest();
        GlStateManager.enableCull();
        GlStateManager.enableLighting();
        glEnable(GL_TEXTURE_2D);
        RenderSystem.enableDepthTest();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        glLineWidth(1.0f);
        glHint(GL_LINE_SMOOTH_HINT, GL_DONT_CARE);
        GlStateManager.popMatrix();
    }

    public void glColor(Color color) {
        glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
    }

    public void drawLine2d(int x1, int y1, int x2, int y2, float lineWidth, Color color) {
        glPushMatrix();
        glColor(color);
        glLineWidth(lineWidth);
        RenderSystem.disableTexture();
        glBegin(GL_LINES);
        {
            glVertex2i(x1, y1);
            glVertex2i(x2, y2);
        }
        glEnd();
        RenderSystem.enableTexture();
        glPopMatrix();
    }

    public void drawLine3d(double x1, double y1, double z1, double x2, double y2, double z2) {
        glBegin(GL_LINES);
        {
            glVertex3d(x1, y1, z1);
            glVertex3d(x2, y2, z2);
        }
        glEnd();
    }

    // using integers so it syncs up with vanilla render methods
    public void drawOutline2d(int x1, int y1, int x2, int y2, float lineWidth, Color color) {
        glPushMatrix();
        glColor(color);
        glLineWidth(lineWidth);
        RenderSystem.disableTexture();
        glBegin(GL_LINE_LOOP);
        {
            glVertex2i(x1, y1);
            glVertex2i(x2, y1);
            glVertex2i(x2, y2);
            glVertex2i(x1, y2);
        }
        glEnd();
        RenderSystem.enableTexture();
        glPopMatrix();
    }
}
