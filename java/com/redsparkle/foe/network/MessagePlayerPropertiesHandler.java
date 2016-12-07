package com.redsparkle.foe.network;

import com.redsparkle.foe.Init.FOECapabilitiesInit;
import com.redsparkle.foe.capa.IRadiationCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by NENYN on 12/6/2016.
 */
public class MessagePlayerPropertiesHandler implements IMessageHandler<MessagePlayerProperties, IMessage> {
    @Override
    public IMessage onMessage(final MessagePlayerProperties message, MessageContext ctx) {
        System.err.println("TargetEffectMessageToClient received on wrong side:" + ctx.side);
        return null;
    }
}



