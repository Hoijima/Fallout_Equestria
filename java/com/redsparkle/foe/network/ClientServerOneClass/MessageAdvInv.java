package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.api.capa.Inventory.IAdvInventory;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.DedicatedServerProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoijima on 18.07.17.
 */
public class MessageAdvInv implements IMessage {

    public List<ItemStack> itemArray = new ArrayList<ItemStack>();
    public String[] item_id = new String[12];
    public int[] item_count = new int[12];
    public int[] item_damage = new int[12];

    public IAdvInventory advInventory;

    public MessageAdvInv() {
    }

    public MessageAdvInv(IAdvInventory addinv) {
        this.advInventory = addinv;

        for (int i = 0; i < 12; i++) {
            item_id[i] = addinv.getStackInSlot(i).getUnlocalizedName();
            item_count[i] = addinv.getStackInSlot(i).getCount();
            item_count[i] = addinv.getStackInSlot(i).getItemDamage();
        }

    }


    @Override
    public void toBytes(ByteBuf buf) {
        for (int i = 0; i < 12; i++) {
            String message = new String(item_id[i] + "," + item_count[i] + "," + item_damage[i]);
            ByteBufUtils.writeUTF8String(buf, message);

        }
    }

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf) {
        for (int i = 0; i < 12; i++) {

            String recivedmessage = ByteBufUtils.readUTF8String(buf);
            String[] parts = recivedmessage.split(",");
            item_id[i] = parts[0];
            item_count[i] = Integer.parseInt(parts[1]);
            item_damage[i] = Integer.parseInt(parts[2]);

        }
    }

    public static class HandlerClient implements IMessageHandler<MessageAdvInv, IMessage> {

        @Override
        public IMessage onMessage(MessageAdvInv message, MessageContext ctx) {
            ClientOnlyProxy.handleAdv(message);
            return null;
        }
    }

    public static class HandlerServer implements IMessageHandler<MessageAdvInv, IMessage> {

        @Override
        public IMessage onMessage(MessageAdvInv message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().playerEntity;
            DedicatedServerProxy.handleAdv(message, playerMP);
            return null;
        }
    }


}
