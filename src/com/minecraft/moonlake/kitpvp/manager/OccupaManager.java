package com.minecraft.moonlake.kitpvp.manager;

import com.minecraft.moonlake.kitpvp.api.occupa.Occupa;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;

/**
 * Created by MoonLake on 2016/7/9.
 */
public final class OccupaManager extends KitPvPManager {

    /**
     * 初始化指定玩家的职业数据
     *
     * @param kitPvPPlayer 玩家
     */
    public static void initOccupaPlayer(KitPvPPlayer kitPvPPlayer) {

        initOccupaPlayer(kitPvPPlayer, kitPvPPlayer.getOccupa());
    }

    /**
     * 初始化指定玩家的职业数据
     *
     * @param kitPvPPlayer 玩家
     * @param occupa 职业对象
     */
    public static void initOccupaPlayer(KitPvPPlayer kitPvPPlayer, Occupa occupa) {

        if(kitPvPPlayer != null && occupa != null) {

            kitPvPPlayer.resetHealth();
            kitPvPPlayer.clearPotionEffect();
            kitPvPPlayer.getInventory().clear();

            kitPvPPlayer.setOccupa(occupa);
            kitPvPPlayer.setMaxHealth(occupa.getMaxHealth());
            kitPvPPlayer.setHealth(kitPvPPlayer.getMaxHealth());
            kitPvPPlayer.getInventory().setItem(0, occupa.getWeapon());
            kitPvPPlayer.getInventory().setArmorContents(occupa.getArmors());
            kitPvPPlayer.getSkillCombo().clearSkill();
            kitPvPPlayer.getSkillCombo().resetCoolDown();
            kitPvPPlayer.getSkillCombo().setSkill(occupa.getSkillList());
        }
    }
}
