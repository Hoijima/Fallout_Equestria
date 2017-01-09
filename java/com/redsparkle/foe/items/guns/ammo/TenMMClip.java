package com.redsparkle.foe.items.guns.ammo;

import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.utils.InventoryManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by NENYN on 1/2/2017.
 */
public class TenMMClip extends Item {

    public int MaxDamage = 13;
    public TenMMClip()
    {
        final int NUMBER_OF_BOXES = 1;
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_ammo);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(MaxDamage);

    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add("Ammo clip for 10mm pistol");
    }


    public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn)
    {
        worldIn.setActiveHand(playerIn);
        return new ActionResult(EnumActionResult.SUCCESS, worldIn.getHeldItem(playerIn));
    }


    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        ItemStack ammo = new ItemStack(ItemInit.tenMMAmmo);
        EntityPlayer playerIn = (EntityPlayer) entityLiving;
        playerIn.getHeldItem(EnumHand.MAIN_HAND);
        if(stack.getItemDamage() <= MaxDamage){
            if(InventoryManager.findItemOffBar(playerIn, ammo) == ItemStack.EMPTY){return  stack;}
            else {
                InventoryManager.findItemOffBar(playerIn, ammo).shrink(1);
                stack.setItemDamage(stack.getItemDamage() - 1);
                return stack;
            }
        }else{
            return stack;
        }
    }


    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.NONE;
    }
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 16;
    }

}
