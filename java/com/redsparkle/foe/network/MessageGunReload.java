package com.redsparkle.foe.network;

import com.redsparkle.foe.DedicatedServerProxy;
import com.redsparkle.foe.items.guns.LaserPistol;
import com.redsparkle.foe.items.guns.TenMM;
import com.redsparkle.foe.network.helpers.gunReload;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by NENYN on 1/13/2017.
 */
public class MessageGunReload implements IMessage {


    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageGunReload, IMessage> {

        @Override
        public IMessage onMessage(MessageGunReload message, MessageContext ctx) {

            final EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            DedicatedServerProxy.handleReloadMessage(player);
            return null;
        }


    }
}
