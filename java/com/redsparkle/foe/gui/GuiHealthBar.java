package com.redsparkle.foe.gui;

import net.minecraft.block.material.Material;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.server.MinecraftServer;
import org.lwjgl.opengl.GL11;

import com.redsparkle.foe.main;
import com.redsparkle.foe.capa.IRadiationCapability;
import com.redsparkle.foe.capa.RadsFactoryProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiHealthBar extends GuiIngame {
	private Minecraft mc;
	private MinecraftServer server;
	private GuiHealthBar instance;
	private int pRads;
	private int RADS_CAP = 500;
	private static final ResourceLocation texturepath = new ResourceLocation(main.MODID.toLowerCase(), "textures/gui/bar.png");
	public GuiHealthBar(Minecraft mc, MinecraftServer minecraftServer){
		super(mc);
		this.mc = mc;
	}
	public void setInstance(GuiHealthBar instance){
		this.instance = instance;
	}
	public void setRads(int rads){
		pRads = rads;
	}
	public static GuiHealthBar getInstance(){
		return null;
	}
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {

		if(event.isCancelable() || event.getType() != ElementType.EXPERIENCE)
		{
			return;
		}
		if(this.mc.thePlayer.isInsideOfMaterial(Material.PORTAL))return;
		if(true){
		int xPos = 10;
		int yPos = 2;

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		FontRenderer fontrenderer = this.mc.fontRendererObj;
		this.mc.renderEngine.bindTexture(texturepath);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		ScaledResolution scaledresolution = new ScaledResolution(this.mc);
		int k = scaledresolution.getScaledWidth();
		int l = scaledresolution.getScaledHeight();
		int rads;
		try {
			rads = server.getServer().getEntityWorld().getPlayerEntityByName(mc.thePlayer.getDisplayNameString()).getEntityWorld().getCapability(RadsFactoryProvider.RADIATION_CAPABILITY,null).getRadiation();
		}
		catch(NullPointerException e){
			rads = 0;
			System.out.println("NullPtrExeption");
		}
		int barCalc;
		
		barCalc = Math.round(RADS_CAP*rads/100);//calculates percentage of full
		
		this.drawTexturedModalRect(20, 150, 0, 0, 83, 9);
		
		if (barCalc < 500){
			this.drawTexturedModalRect(23,153,0,25,59,9);
			String s1 = Integer.toString(barCalc);
			 fontrenderer.drawStringWithShadow("Rads:", 0, 0, 5010157);
		}
		
		

		}
	}
}



//TODO: Rewrite according to https://github.com/TVoidS/MANA-Mod/blob/master/java/com/tvoids/firstmod/init/GuiBuffBar.java

