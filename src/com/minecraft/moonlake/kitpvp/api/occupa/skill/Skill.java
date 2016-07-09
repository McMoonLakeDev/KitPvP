package com.minecraft.moonlake.kitpvp.api.occupa.skill;

import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;

/**
 * Created by MoonLake on 2016/7/9.
 */
public interface Skill {

    /**
     * 获取此技能的技能名
     *
     * @return 技能名
     */
    String getName();

    /**
     * 获取此技能的组合键 ID
     *
     * @return 组合键 ID
     */
    int getCombo();

    /**
     * 设置此技能的组合键类型
     *
     * @param comboType 类型
     */
    void setCombo(SkillComboType[] comboType);

    /**
     * 获取此技能的显示名称
     *
     * @return 显示名
     */
    String getDisplayName();

    /**
     * 设置此技能的显示名称
     *
     * @param displayName 显示名
     */
    void setDisplayName(String displayName);

    /**
     * 获取此技能的冷却时间
     *
     * @return 冷却时间
     */
    int getCoolDown();

    /**
     * 设置此技能的冷却时间
     *
     * @param cooldown 冷却时间
     */
    void setCoolDown(int cooldown);

    /**
     * 将此技能强制释放
     *
     * @param kitPvPPlayer 释放者
     */
    void cast(KitPvPPlayer kitPvPPlayer);
}
