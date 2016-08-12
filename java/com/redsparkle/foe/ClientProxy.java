package com.redsparkle.foe;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hoijima desu on 13.08.16 desu.
 */
public class ClientProxy extends CommonProxy {
    private final Minecraft mc = Minecraft.getMinecraft();

    public void PreInit(FMLPreInitializationEvent e) {


        super.PreInit(e);

    }

    @Override
    public void Init(FMLInitializationEvent e) {
        super.Init(e);

    }

    @Override
    public void PostInit(FMLPostInitializationEvent e) {

        super.PostInit(e);

    }

    @Override
    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        // Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
        // your packets will not work as expected because you will be getting a
        // client player even when you are on the server!
        // Sounds absurd, but it's true.

        // Solution is to double-check side before returning the player:
        return (ctx.side.isClient() ? mc.thePlayer : super.getPlayerEntity(ctx));
    }

    @Override
    public IThreadListener getThreadFromContext(MessageContext ctx) {
        return (ctx.side.isClient() ? mc : super.getThreadFromContext(ctx));
    }
}
