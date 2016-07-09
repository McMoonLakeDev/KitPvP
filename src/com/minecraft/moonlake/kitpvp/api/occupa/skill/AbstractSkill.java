package com.minecraft.moonlake.kitpvp.api.occupa.skill;

import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.SkillComboManager;

/**
 * Created by MoonLake on 2016/7/9.
 */
public abstract class AbstractSkill implements Skill {

    private final String name;
    private int comboId;
    private int cooldown;
    private String displayName;

    public AbstractSkill(String name) {

        this.name = name;
        this.cooldown = 0;
        this.displayName = name;
    }

    /**
     * 获取此技能的技能名
     *
     * @return 技能名
     */
    @Override
    public String getName() {

        return name;
    }

    /**
     * 获取此技能的组合键 ID
     *
     * @return 组合键 ID
     */
    @Override
    public int getCombo() {

        return comboId;
    }

    /**
     * 设置此技能的组合键类型
     *
     * @param comboType 类型
     */
    @Override
    public void setCombo(SkillComboType[] comboType) {

        this.comboId = SkillComboManager.convertCombo(comboType);
    }

    /**
     * 获取此技能的显示名称
     *
     * @return 显示名
     */
    @Override
    public String getDisplayName() {

        return displayName;
    }

    /**
     * 设置此技能的显示名称
     *
     * @param displayName 显示名
     */
    @Override
    public void setDisplayName(String displayName) {

        this.displayName = displayName;
    }

    /**
     * 获取此技能的冷却时间
     *
     * @return 冷却时间
     */
    @Override
    public int getCoolDown() {

        return cooldown;
    }

    /**
     * 设置此技能的冷却时间
     *
     * @param cooldown 冷却时间
     */
    @Override
    public void setCoolDown(int cooldown) {

        this.cooldown = cooldown;
    }

    /**
     * 将此技能强制释放
     *
     * @param kitPvPPlayer 释放者
     */
    public abstract void cast(KitPvPPlayer kitPvPPlayer);
}
