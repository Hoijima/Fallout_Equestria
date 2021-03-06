package com.redsparkle.foe.network.ClientServerOneClass;

import com.redsparkle.api.Capability.Player.Inventory.IAdvInventory;
import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.foe.ClientOnlyProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by hoijima on 18.07.17.
 */
public class MessageAdvInv_SYNC_op implements IMessage {
    public NonNullList<String> item_id = NonNullList.withSize(12, "item.air");
    public NonNullList<Integer> item_count = NonNullList.withSize(12, 0);
    public NonNullList<Integer> item_damage = NonNullList.withSize(12, 0);
    public IAdvInventory iAdvInventory;
    public String playerName;

    public MessageAdvInv_SYNC_op() {
    }

    public MessageAdvInv_SYNC_op(EntityPlayer player) {
        this.iAdvInventory = player.getCapability(IAdvProvider.Adv_Inv, null);
        for (int i = 0; i < 12; i++) {
            String item_name = delegeteName(iAdvInventory.getStackInSlot(i).getItem());
            item_id.set(i, item_name);
            item_count.set(i, iAdvInventory.getStackInSlot(i).getCount());
            item_damage.set(i, iAdvInventory.getStackInSlot(i).getItemDamage());
        }
        this.playerName = player.getName().toString();
    }

    public String delegeteName(Item item) {
        String name = item.delegate.name().toString();//+":"+item.getUnlocalizedName();
        return name;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, playerName);
        for (int i = 0; i < 12; i++) {
            ByteBufUtils.writeUTF8String(buf, new String(item_id.get(i) + "," + item_count.get(i) + "," + item_damage.get(i)));
        }
    }

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf) {
        ByteBufUtils.readUTF8String(buf);
        for (int i = 0; i < 12; i++) {
            String recivedmessage = ByteBufUtils.readUTF8String(buf);
            String[] parts = recivedmessage.split(",");
            item_id.set(i, parts[0]);
            item_count.set(i, Integer.parseInt(parts[1]));
            item_damage.set(i, Integer.parseInt(parts[2]));
        }
    }

    public static class HandlerClient implements IMessageHandler<MessageAdvInv_SYNC_op, IMessage> {
        @Override
        public IMessage onMessage(MessageAdvInv_SYNC_op message, MessageContext ctx) {
            ClientOnlyProxy.handleAdv_SYNC_op(message);
            return null;
        }
    }

}
