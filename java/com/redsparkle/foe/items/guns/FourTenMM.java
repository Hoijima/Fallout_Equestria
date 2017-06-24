package com.redsparkle.foe.items.guns;

import com.redsparkle.foe.Init.SoundInit;
import com.redsparkle.foe.capa.skills.SkillsFactoryProvider;
import com.redsparkle.foe.creativeTabs.InitCreativeTabs;
import com.redsparkle.foe.items.guns.ammo.TenMM.TenMMClip;
import com.redsparkle.foe.items.guns.bulletFired.EntityBullet;
import com.redsparkle.foe.items.guns.inits.ItemFirearm;
import com.redsparkle.foe.utils.GlobalWeaponsStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by NENYN on 1/5/2017.
 */
public class FourTenMM extends ItemFirearm {

    public boolean isGun;
    public int clipRounds = GlobalWeaponsStats.FourclipRounds;


    public FourTenMM() {
        this.setMaxStackSize(1);
        this.setMaxDamage(clipRounds);
        this.setCreativeTab(InitCreativeTabs.Fallout_guns);
        isGun = true;


    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        if (nbt == null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setBoolean("isgun", isGun);
            stack.setTagCompound(tag);
        }

        return super.initCapabilities(stack, nbt);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        this.bullet = new EntityBullet(worldIn, playerIn);
        this.damage = GlobalWeaponsStats.FourDamage + playerIn.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getSmallGuns();

        if (!playerIn.capabilities.isCreativeMode) {
            if (itemstack.getItemDamage() >= (GlobalWeaponsStats.FourclipRounds-1)) {
                if (findAmmo(playerIn) == ItemStack.EMPTY) {
                    // ---------------_EMPTY CLIP
                    worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.guns[4], SoundCategory.HOSTILE, 1F, 0.4F);
                    return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                }

            } else {

                worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.guns[3], SoundCategory.HOSTILE, 15F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

                worldIn.spawnEntity(bullet(worldIn, playerIn));


                itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                playerIn.cameraYaw = -0.1F;
                return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
            }


        } else {
            worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.guns[3], SoundCategory.HOSTILE, 15F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            worldIn.spawnEntity(bullet(worldIn, playerIn));


            playerIn.cameraYaw = -0.1F;
        }
        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }


    @Override
    public boolean isAmmo(ItemStack stack) {
        return stack.getItem() instanceof TenMMClip;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("14 MM pistol");
        tooltip.add("Clip size: " + (clipRounds - 2));
        tooltip.add("Base Damage: " + GlobalWeaponsStats.FourDamage);
        tooltip.add("Your Damage: " + damage);

    }

}




