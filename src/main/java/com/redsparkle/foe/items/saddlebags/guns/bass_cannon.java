package com.redsparkle.foe.items.saddlebags.guns;

import com.redsparkle.api.items.helpers.Item_Instances.Item_SaggleBagGun;
import com.redsparkle.foe.Init.GlobalsGunStats;
import net.minecraft.item.Item;

/**
 * Created by hoijima on 31.08.17.
 */
public class bass_cannon extends Item_SaggleBagGun {

    public bass_cannon(String itemName, String side, GlobalsGunStats params, Item ClipItem, Item AmmoItem) {
        super(itemName, side, params, ClipItem, AmmoItem);
    }
}
