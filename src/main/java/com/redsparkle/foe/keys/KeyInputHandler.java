package com.redsparkle.foe.keys;

import com.redsparkle.api.Capability.Player.Inventory.IAdvProvider;
import com.redsparkle.api.Capability.Player.saddlegun_shooting.ITrigger_item_Provider;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.Item_Instances.Item_SaggleBagGun;
import com.redsparkle.foe.ClientOnlyProxy;
import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageAdvInv;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientTrigger_Item;
import com.redsparkle.foe.network.MessageGunReload;
import com.redsparkle.foe.network.MessageUpdateSLSClientOnDemand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

/**
 * Created by NENYN on 1/12/2017.
 */
public class KeyInputHandler {
    public boolean activated = false;
    public int count = 0;
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Keyboard.enableRepeatEvents(true);
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;

        if (keyHandler.pipbuck.isPressed()) {
            if (mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(0) != ItemStack.EMPTY) {
                main.simpleNetworkWrapper.sendToServer(new MessageUpdateSLSClientOnDemand());
                player.openGui(main.instance, 0, mc.world, (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ);
                activated = true;
            }

        }
        if (keyHandler.testButton.isPressed()) {

        }
        if (keyHandler.saddlebags.isPressed()) {
            main.simpleNetworkWrapper.sendToServer(new MessageAdvInv("sync_and_gui"));

        }


        if (keyHandler.sbag_shooter.isPressed()) {

            if (player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(5).getItem() != Items.AIR
                    && player.getHeldItemMainhand().getItem() == Items.AIR
                    && player.getHeldItemOffhand().getItem() == Items.AIR
                    ) {
                if (mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()) {
                    main.simpleNetworkWrapper.sendToServer(new MessageUpdateClientTrigger_Item(false));
                } else {
                    main.simpleNetworkWrapper.sendToServer(new MessageUpdateClientTrigger_Item(true));
                }
            }

        }

        if (Keyboard.getEventKey() == keyHandler.fire_RSB.getKeyCode()
                && !mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
                && player.getHeldItemMainhand().getItem() instanceof Item_Firearm
                ) {


            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKeyState()) {
                    if (Keyboard.isRepeatEvent()) {
                        // Key held down
                        if (((Item_Firearm) mc.player.getHeldItemMainhand().getItem()).autofireSupport) {
                            ClientOnlyProxy.FireMessage("gun_main");
                        }
                    } else {
                        // Key pressed
                        ClientOnlyProxy.FireMessage("gun_main");

                    }
                    // Key released

                }
            }
        }




        if (Keyboard.getEventKey() == keyHandler.fire_LSB.getKeyCode()
                && mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
                && mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem() instanceof Item_SaggleBagGun) {

            if (Keyboard.getEventKeyState()) {
                if (Keyboard.isRepeatEvent()) {
                    // Key held down
                    if (((Item_SaggleBagGun) mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem()).autofireSupport) {
                        ClientOnlyProxy.FireMessage("gun_saddlebagLS");
                    }
                } else {
                    // Key pressed
                    ClientOnlyProxy.FireMessage("gun_saddlebagLS");
                }
                // Key released
            }
        }

        if (Keyboard.getEventKey() == keyHandler.fire_RSB.getKeyCode()
                && mc.player.getCapability(ITrigger_item_Provider.TRIGGER_ITEM, null).getStatus()
                && mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem() instanceof Item_SaggleBagGun) {

            if (Keyboard.getEventKeyState()) {
                if (Keyboard.isRepeatEvent()) {
                    // Key held down
                    if (((Item_SaggleBagGun) mc.player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem()).autofireSupport) {
                        ClientOnlyProxy.FireMessage("gun_saddlebagRS");
                    }
                } else {
                    // Key pressed
                    ClientOnlyProxy.FireMessage("gun_saddlebagRS");
                }
                // Key released
            }
        }

        if (keyHandler.reload.isPressed()) {
            if(player.getHeldItemMainhand().getItem() instanceof Item_Firearm){
                main.simpleNetworkWrapper.sendToServer(new MessageGunReload("gun_main"));
            }
        }
        if (keyHandler.reloadLSB.isPressed()) {
            if(player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(6).getItem() != Items.AIR){
                main.simpleNetworkWrapper.sendToServer(new MessageGunReload("gun_saddlebagLS"));
            }
        }
        if (keyHandler.reloadRSB.isPressed()) {
            if(player.getCapability(IAdvProvider.Adv_Inv, null).getStackInSlot(7).getItem() != Items.AIR) {
                main.simpleNetworkWrapper.sendToServer(new MessageGunReload("gun_saddlebagRS"));
            }
        }



    }

//    @SubscribeEvent(receiveCanceled = true)
//    public void onMouseInput(InputEvent.MouseInputEvent event) {
//        {
//            //LogHelper.info("At least I get called");
//            if (Minecraft.getMinecraft().gameSettings.keyBindAttack.isPressed()) {
//                System.out.println("Left button pressed");
//                event.setCanceled(true);
//            }
//            if (Minecraft.getMinecraft().gameSettings.keyBindUseItem.isPressed()) {
//                System.out.println("Right button pressed");
//                event.setCanceled(true);
//            }
//        }
//    }
}