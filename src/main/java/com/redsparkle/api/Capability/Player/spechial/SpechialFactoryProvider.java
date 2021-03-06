package com.redsparkle.api.Capability.Player.spechial;

import com.redsparkle.foe.main;
import com.redsparkle.foe.network.ClientServerOneClass.MessageUpdateClientServerSPECHIAL;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by hoijima on 01.03.17.
 */
public class SpechialFactoryProvider implements ISpechialCapability, ICapabilitySerializable<NBTTagCompound> {
    @CapabilityInject(ISpechialCapability.class)
    public static Capability<ISpechialCapability> SPECHIAL_CAPABILITY = null;
    private boolean dirty = true;
    private Integer
            StreinghtLevel, // real level
            PerceptionLevel, // real level
            EnduranceLevel, // real level
            CharismaLevel, // real level
            IntelligenceLevel, // real level
            AgilityLevel, // real level
            LuckLevel; // real level

    public SpechialFactoryProvider() {
    }

    public static ISpechialCapability instanceFor(EntityPlayer player) {
        return player.getCapability(SPECHIAL_CAPABILITY, null);
    }

    public Integer getStreinght() {
        return StreinghtLevel;
    }

    /*
    ##################################
    */
    public void setStreinght(Integer newStreinghtLevel) {
        StreinghtLevel = newStreinghtLevel;
    }

    public Integer getPerception() {
        return PerceptionLevel;
    }

    /*
    ##################################
    */
    public void setPerception(Integer newPerceptionLevel) {
        PerceptionLevel = newPerceptionLevel;
    }

    public Integer getEndurance() {
        return EnduranceLevel;
    }

    /*
    ##################################
    */
    public void setEndurance(Integer newEnduranceLevel) {
        EnduranceLevel = newEnduranceLevel;
    }

    public Integer getCharisma() {
        return CharismaLevel;
    }

    /*
    ##################################
    */
    public void setCharisma(Integer newCharismaLevel) {
        CharismaLevel = newCharismaLevel;
    }

    public Integer getIntelligence() {
        return IntelligenceLevel;
    }

    /*
    ##################################
    */
    public void setIntelligence(Integer newIntelligenceLevel) {
        IntelligenceLevel = newIntelligenceLevel;
    }

    public Integer getAgility() {
        return AgilityLevel;
    }

    /*
    ##################################
    */
    public void setAgility(Integer newAgilityLevel) {
        AgilityLevel = newAgilityLevel;
    }

    public Integer getLuck() {
        return LuckLevel;
    }

    /*
    ##################################
    */
    public void setLuck(Integer newLuckLevel) {
        LuckLevel = newLuckLevel;
    }

    @Override
    public void setAll(Integer all) {
        setStreinght(all);
        setPerception(all);
        setEndurance(all);
        setCharisma(all);
        setIntelligence(all);
        setAgility(all);
        setLuck(all);
    }

    /*
    ##################################
    */
    public void update(EntityPlayer player, World world, TickEvent.Phase phase) {
        // dn if i will ever need it
    }

    public void updateClient(EntityPlayer player) {
        if (!player.getEntityWorld().isRemote) {
            if (dirty)
                main.simpleNetworkWrapper.sendTo(new MessageUpdateClientServerSPECHIAL(this), (EntityPlayerMP) player);
            //dirty = false;
        }
    }

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == SPECHIAL_CAPABILITY;
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == SPECHIAL_CAPABILITY ? (T) this : null;
    }

    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("STR", StreinghtLevel);
        nbt.setInteger("PER", PerceptionLevel);
        nbt.setInteger("END", EnduranceLevel);
        nbt.setInteger("CHR", CharismaLevel);
        nbt.setInteger("INT", IntelligenceLevel);
        nbt.setInteger("AGI", AgilityLevel);
        nbt.setInteger("LUC", LuckLevel);
        return nbt;
    }

    public void deserializeNBT(NBTTagCompound nbt) {
        setStreinght(nbt.getInteger("STR"));
        setPerception(nbt.getInteger("PER"));
        setEndurance(nbt.getInteger("END"));
        setCharisma(nbt.getInteger("CHR"));
        setIntelligence(nbt.getInteger("INT"));
        setAgility(nbt.getInteger("AGI"));
        setLuck(nbt.getInteger("LUC"));
    }
}
