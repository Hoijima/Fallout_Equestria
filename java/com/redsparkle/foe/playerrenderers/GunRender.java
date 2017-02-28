package com.redsparkle.foe.playerrenderers;

import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.items.guns.inits.ItemFirearm;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static java.lang.Math.abs;

/**
 * Created by NENYN on 2/12/2017.
 */
@SideOnly(Side.CLIENT)
public class GunRender implements LayerRenderer<EntityLivingBase> {
    private final RenderPlayer playerRenderer;
    private Float yawCorrector = 0F;
    private Float rotationPitch = 0F;
    public GunRender(RenderPlayer playerRendererIn) {

        this.playerRenderer = playerRendererIn;
    }




    @Override
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack itemstack = entitylivingbaseIn.getHeldItem(EnumHand.MAIN_HAND);
        Item item = itemstack.getItem();
        Minecraft minecraft = Minecraft.getMinecraft();


        if (itemstack != null && item instanceof ItemFirearm) {
            GlStateManager.pushMatrix();
            if (MathHelper.wrapDegrees(entitylivingbaseIn.getPitchYaw().y) >= 0.0F) {
                yawCorrector = MathHelper.wrapDegrees(entitylivingbaseIn.getPitchYaw().y);
            } else if (MathHelper.wrapDegrees(entitylivingbaseIn.getPitchYaw().y) < 0.0F) {
                yawCorrector = abs(MathHelper.wrapDegrees(entitylivingbaseIn.getPitchYaw().y) + 360);
            }


            if (MathHelper.wrapDegrees(entitylivingbaseIn.rotationPitch) >= 0.0F) {
                rotationPitch = MathHelper.wrapDegrees(entitylivingbaseIn.rotationPitch);
            } else if (MathHelper.wrapDegrees(entitylivingbaseIn.rotationPitch) < 0.0F) {
                rotationPitch = abs(MathHelper.wrapDegrees(entitylivingbaseIn.rotationPitch) + 360);
            }

            GlStateManager.translate(0.0F, 0.0F, 0.0F);
            GlStateManager.rotate(netHeadYaw, 0, 1.0F, 0);
            GlStateManager.rotate(headPitch, 1.0F, 0, 0);


            minecraft.getItemRenderer().renderItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.HEAD);
            GlStateManager.popMatrix();
        }
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}
