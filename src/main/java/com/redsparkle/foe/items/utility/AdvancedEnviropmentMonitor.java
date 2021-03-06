package com.redsparkle.foe.items.utility;

import com.redsparkle.api.items.helpers.Item_Instances.Item_Utility;
import com.redsparkle.foe.Init.InitCreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by NENYN on 12/16/2016.
 */
public class AdvancedEnviropmentMonitor extends Item_Utility {
    public AdvancedEnviropmentMonitor(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(InitCreativeTabs.Fallout_meds);   // the item will appear on the Miscellaneous tab in creative
    }

    // adds 'tooltip' text
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        tooltip.add("Before-War tech");
        tooltip.add("Makes beep sounds and shows Environment hazards ");
    }
}
