package com.redsparkle.foe.events;

import com.redsparkle.foe.Init.ItemInit;
import com.redsparkle.foe.gui.PipBuckGui;
import com.redsparkle.foe.gui.RadsOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;

/**
 * Created by NENYN on 12/25/2016.
 */
public class EventHandlerOverlayPipBuck {
    /*
    ##############################################################################
            GUI EVENTS SECTION
    ##############################################################################
    */

    public EventHandlerOverlayPipBuck(PipBuckGui i_HUDrenderer)
    {
        statusBarRenderer = i_HUDrenderer;
    }
    private PipBuckGui statusBarRenderer;

    /* The RenderGameOverlayEvent.Pre event is called before each game overlay element is
   * rendered. It is called multiple times. A list of existing overlay elements can be found
   * in net.minecraftforge.client.event.RenderGameOverlayEvent.
   *
   * If you want something to be rendered under an existing vanilla element, you would render
   * it here.
   *
   * Note that you can entirely remove the vanilla rendering by cancelling the event here.
   */

    @SubscribeEvent(receiveCanceled=true)
    public void onEvent(RenderGameOverlayEvent.Pre event) {
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().thePlayer;
        if (entityPlayerSP == null) return;  // just in case
        if (!entityPlayerSP.isCreative()) {
            // look for the ItemHUDactivator in the hotbar.  If not present, return without changing the HUD.
            boolean foundInHotbar = false;
            final int FIRST_HOTBAR_SLOT = 0;
            final int LAST_HOTBAR_SLOT_PLUS_ONE = FIRST_HOTBAR_SLOT + entityPlayerSP.inventory.getHotbarSize();
            for (int i = FIRST_HOTBAR_SLOT; i < LAST_HOTBAR_SLOT_PLUS_ONE; ++i) {
                ItemStack slotItemStack = entityPlayerSP.inventory.getStackInSlot(i);
                if (slotItemStack != null && slotItemStack.getItem() == ItemInit.pipbuck) {
                    foundInHotbar = true;
                    break;
                }
            }
            if (!foundInHotbar) return;
        }


        switch (event.getType()) {
            case HEALTH:
                statusBarRenderer.renderStatusBar(event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight());        /* Call a helper method so that this method stays organized */
        /* Don't render the vanilla heart bar */
                event.setCanceled(true);
                break;

            case ARMOR:
        /* Don't render the vanilla armor bar, it's part of the status bar in the HEALTH event */
                event.setCanceled(true);
                break;

            default: // If it's not one of the above cases, do nothing
                break;
        }
        new RadsOverlay(Minecraft.getMinecraft());

    }

    /* The RenderGameOverlayEvent.Post event is called after each game overlay element is rendered.
     * Similar to the RenderGameOverlayEvent.Pre event, it is called multiple times.
     *
     * If you want something to be rendered over an existing vanilla element, you would render
     * it here.
     */
    @SubscribeEvent(receiveCanceled=true)
    public void onEvent(RenderGameOverlayEvent.Post event) {

        switch (event.getType()) {
            case HEALTH:
                break;
            default: // If it's not one of the above cases, do nothing
                break;
        }
    }
}
