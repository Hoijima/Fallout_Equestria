package com.redsparkle.foe.items.guns;
import com.redsparkle.api.Capability.Items.Gun.GunFactoryProvider;
import com.redsparkle.api.Capability.Items.Gun.IGunInterface;
import com.redsparkle.api.items.helpers.Item_Instances.Item_Firearm;
import com.redsparkle.api.items.helpers.guns.GlobalsGunStats;
import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.ammo.FlareShell.FlareShell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
/**
 * Created by hoijima on 24.06.17.
 */
public class FlareGun extends Item_Firearm {
    public FlareGun() {
        this.clipRounds = GlobalsGunStats.FLARE_GUN.Clipsize();
        this.setMaxStackSize(1);
        this.setMaxDamage(clipRounds);
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        this.cameraYaw = -0.1F;
        this.gunName = "Pre war Flare launcher";
        this.projectile = "flare";
    }
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        IGunInterface iammo;
        if (stack.hasCapability(GunFactoryProvider.GUN, null)) {
            iammo = stack.getCapability(GunFactoryProvider.GUN, null);
            if (iammo.getMaxAmmo() == 0) {
                iammo.setMaxAmmo(clipRounds);
            }
            stack.setItemDamage(iammo.getMaxAmmo() - iammo.getAmmo());
        }
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        this.shot = SoundInit.flaregun_shot;
        this.dry = SoundInit.flaregun_dry;
        ItemStack itemstack = playerIn.getHeldItem(hand);
        this.damage = GlobalsGunStats.FLARE_GUN.getDamage();
        this.BaseDamage = GlobalsGunStats.FLARE_GUN.getDamage();
        ItemStack caseStack = ItemStack.EMPTY;
        IGunInterface igun = itemstack.getCapability(GunFactoryProvider.GUN, null);
        return shoot(playerIn, igun, worldIn, caseStack, itemstack);
    }
    @Override
    public boolean isAmmo(ItemStack stack) {
        return stack.getItem() instanceof FlareShell;
    }
}
