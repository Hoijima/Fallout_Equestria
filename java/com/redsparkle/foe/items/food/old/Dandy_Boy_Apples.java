package com.redsparkle.foe.items.food.old;

import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.food.FoodMultipleUse;

/**
 * Created by hoijima on 09.06.17.
 */
public class Dandy_Boy_Apples extends FoodMultipleUse {

    public int foodLvl = 0;

    public int NUMBER_OF_BOXES = 1;
    public int MaxDamage = 3;
    public int foodToAdd = 3;

    public Dandy_Boy_Apples() {
        this.setMaxStackSize(NUMBER_OF_BOXES);
        this.setCreativeTab(InitCreativeTabs.Fallout_Food);   // the item will appear on the Miscellaneous tab in creative
        this.setMaxDamage(MaxDamage);
        this.foodLvl = foodToAdd;

    }
}