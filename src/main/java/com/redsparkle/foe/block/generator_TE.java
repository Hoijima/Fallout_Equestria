package com.redsparkle.foe.block;

import com.redsparkle.api.block.TileEntityDefault;

public class generator_TE extends TileEntityDefault {
    private String customInventoryName;

    public void setCustomInventoryName(String customInventoryName) {
        this.customInventoryName = customInventoryName;
    }
}