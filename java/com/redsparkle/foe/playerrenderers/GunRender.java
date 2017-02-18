package com.redsparkle.foe.playerrenderers;

import com.redsparkle.foe.items.guns.inits.ItemFirearm;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by NENYN on 2/12/2017.
 */
@SideOnly(Side.CLIENT)
public class GunRender implements LayerRenderer<AbstractClientPlayer> {
    private final RenderPlayer playerRenderer;

    public GunRender(RenderPlayer playerRendererIn) {

        this.playerRenderer = playerRendererIn;
    }




    @Override
    public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack itemstack = entitylivingbaseIn.getHeldItem(EnumHand.MAIN_HAND);
        Item item = itemstack.getItem();
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayerSP player = minecraft.player;

        Vec3d currentPos = Minecraft.getMinecraft().player.getPositionEyes(partialTicks);
        Vec3d playerPos = player.getPositionEyes(partialTicks);

        if(itemstack != null && item instanceof ItemFirearm){
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 0.0F, 0.0F);
            //GlStateManager.scale(-0.5F,-0.5F,-0.5F);
            //GlStateManager.rotate(0F,0.0F,360.0F,0F);
            //GlStateManager.rotate(player.prevRotationYawHead,0.0F,0.1F,0F);
            //GlStateManager.rotate(playerPos.xCoord-currentPos.xCoord, playerPos.yCoord-currentPos.yCoord, playerPos.zCoord-currentPos.zCoord);

            GlStateManager.rotate(player.getRotationYawHead(),0,1,0);
            GlStateManager.rotate(player.cameraPitch,1,0,0);

            //minecraft.player.getHorizontalFacing().getDirectionVec().getX();
            minecraft.getItemRenderer().renderItem(entitylivingbaseIn,itemstack, ItemCameraTransforms.TransformType.HEAD);
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}