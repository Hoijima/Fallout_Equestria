package com.redsparkle.foe.gui.Overlays;

import com.redsparkle.api.Capability.Player.rad.RadsFactoryProvider;
import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.Init.ConfigInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

/**
 * Created by NENYN on 14.11.2016.
 */
public class Rads_Overlay extends Gui {
    private final static ResourceLocation overlayBarRad = new ResourceLocation(GlobalNames.Domain, "textures/gui/rads_hud_overlay.png");
    private final static int BAR_WIDTH = 103;
    private final static int BAR_HEIGHT = 26;
    private final static int RadBAR_WIDTH = 100;
    private final static int RadBAR_HEIGHT = 8;

    public Rads_Overlay(Minecraft mc) {
        EntityPlayer player = mc.player;
        int playerRadlevel = player.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).getRadiation();
        ScaledResolution scaled = new ScaledResolution(mc);
        int screenWidth = scaled.getScaledWidth();
        int screenHeight = scaled.getScaledHeight();

        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glPushMatrix();

        GlStateManager.disableBlend();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
        Color color = new Color(ConfigInit.colorR, ConfigInit.colorG, ConfigInit.colorB); // I want to draw the texture to solid red color

        GL11.glColor4f((float) color.getRed() / 255f,
                (float) color.getGreen() / 255f,
                (float) color.getBlue() / 255f,
                (float) color.getAlpha() / 255f);


        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 1);
      /* This method tells OpenGL to draw with the custom texture */
        mc.renderEngine.bindTexture(overlayBarRad);
        final int PositionX = screenWidth - 75; // leftmost edge of the experience bar
        final int PositionY = screenHeight / 19 + BAR_HEIGHT;  // top of the experience bar
        GL11.glTranslatef(PositionX, PositionY - BAR_HEIGHT, 0);
        GL11.glScalef(0.76F, 0.76F, 0.76F);
        drawTexturedModalRect(0, 0, 0, 0, BAR_WIDTH, BAR_HEIGHT);
        GL11.glPushMatrix();
        if (playerRadlevel <= 1) {
        } else if (playerRadlevel >= 1) {
            drawTexturedModalRect(2, 6, 2, 29, Math.round(0.1F * playerRadlevel), RadBAR_HEIGHT);
            GL11.glScalef(0.76F, 0.76F, 0.76F);
        }
        GL11.glPushMatrix();

        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }
}
