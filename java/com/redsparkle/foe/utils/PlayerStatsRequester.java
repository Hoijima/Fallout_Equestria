package com.redsparkle.foe.utils;

import com.redsparkle.foe.capa.level.LevelFactoryProvider;
import com.redsparkle.foe.capa.rad.RadsFactoryProvider;
import com.redsparkle.foe.capa.skills.SkillsFactoryProvider;
import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
import com.redsparkle.foe.capa.water.WaterFactoryProvider;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by hoijima on 17.06.17.
 */
public class PlayerStatsRequester {

    public static Integer[] skills(EntityPlayer player){

        Integer[] skills = new Integer[]{//0-11
                player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getBigGuns(),
                player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getSmallGuns(),
                player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getEnergyWeapons(),
                player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getExplosives(),
                player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getMeleeWeapons(),
                player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getUnarmed(),
                player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getMedicine(),
                player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getLockpick(),
                player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getRepair(),
                player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getScience(),
                player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getSneak(),
                player.getCapability(SkillsFactoryProvider.SKILLS_CAPABILITY, null).getBarter()
        };
        
        
        
        return skills;
    }

    public static Integer[] lvl(EntityPlayer player){

        Integer[] playerParams = new Integer[]{
                player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getLevel(),
                player.getCapability(LevelFactoryProvider.LEVEL_CAPABILITY, null).getProgress()
            

        };



        return playerParams;
    }
    public static Integer[] spechials(EntityPlayer player){

        Integer[] spechials = {
                player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getStreinght(),
                player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getPerception(),
                player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getEndurance(),
                player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getCharisma(),
                player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getIntelligence(),
                player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getAgility(),
                player.getCapability(SpechialFactoryProvider.SPECHIAL_CAPABILITY, null).getLuck()
        };



        return spechials;
    }


    public static Integer[] additionalStats(EntityPlayer player){

        Integer[] player_RAD_WATER = new Integer[]{
                player.getCapability(RadsFactoryProvider.RADIATION_CAPABILITY, null).getRadiation(),
                player.getCapability(WaterFactoryProvider.WATER_CAPABILITY, null).getWater()

        };

        return player_RAD_WATER;
    }
}