package com.redsparkle.foe.Init;

import com.redsparkle.api.utils.GlobalNames;
import com.redsparkle.foe.main;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hoijima on 14.12.16.
 */

@Mod.EventBusSubscriber(modid = main.MODID)
public class BlockInit {
//    public static final SparkleColaMachineBlock SparkleColaMachineBlock = new SparkleColaMachineBlock(GlobalNames.SPCmachine);



    public static final String[] blocksNames = new String[]{
//            GlobalNames.NAMES,

    };

    public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();

        final Block[] blocks = {
                //OBJECTS
        };
    }

    @SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
        final ItemBlock[] items = {
                // new ItemBlock(OBJECT),

        };

        final IForgeRegistry<Item> registry = event.getRegistry();
        for (int i = 0; i < (items.length - 1); i++) {
            items[i].setUnlocalizedName(blocksNames[i]);
            items[i].setRegistryName(blocksNames[i]);
//            registry.register(items[i]);
            ITEM_BLOCKS.add(items[i]);
        }
        //registerTileEntity(items, GlobalNames.name);

    }


    private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
        GameRegistry.registerTileEntity(tileEntityClass, GlobalNames.Domain + name);
    }
}

