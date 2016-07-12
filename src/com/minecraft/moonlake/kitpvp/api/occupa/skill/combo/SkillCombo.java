package com.minecraft.moonlake.kitpvp.api.occupa.skill.combo;

import com.minecraft.moonlake.kitpvp.api.occupa.skill.Skill;

import java.util.List;

/**
 * Created by MoonLake on 2016/7/9.
 */
public interface SkillCombo {

    /**
     * 应用下次组合类型
     *
     * @param comboType 组合类型
     */
    void applyClick(SkillComboType comboType);

    /**
     * 清除全部组合
     */
    void clearCombo();

    /**
     * 获取当前组合的 ID
     *
     * @return 组合 ID
     */
    int getCurrentComboId();

    /**
     * 获取当前组合的字符串
     *
     * @return 组合字符串
     */
    String getCurrentComboString();

    /**
     * 获取当前组合的索引
     *
     * @return 已经组合的索引
     */
    int getComboIndex();

    /**
     * 设置此技能组合对象的技能集合
     *
     * @param skillList 技能集合
     */
    void setSkill(List<Skill> skillList);

    /**
     * 清除此技能组合对象的技能集合
     */
    void clearSkill();

    /**
     * 重置技能组合对象类的冷却时间
     */
    void resetCoolDown();
}
