package com.redsparkle.api.items.helpers.armor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

/**
 * Created by hoijima on 3/20/2017.
 */
public class ItemLegs extends ItemArmor {
    public ItemLegs(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
    }
}
