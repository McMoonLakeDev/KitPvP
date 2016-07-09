package com.minecraft.moonlake.kitpvp.api.occupa;

import com.minecraft.moonlake.kitpvp.api.occupa.skill.Skill;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by MoonLake on 2016/7/9.
 */
public interface Occupa {

    /**
     * 获取此职业的类型
     *
     * @return 类型
     */
    OccupaType getType();

    /**
     * 获取此职业的技能集合
     *
     * @return 技能集合
     */
    List<Skill> getSkillList();

    /**
     * 将此职业添加指定技能对象
     *
     * @param skill 技能
     */
    void addSkill(Skill skill);

    /**
     * 获取此职业的最大血量
     *
     * @return 最大血量
     */
    double getMaxHealth();

    /**
     * 获取此职业的武器
     *
     * @return 武器
     */
    ItemStack getWeapon();

    /**
     * 获取此职业的护甲
     *
     * @return 护甲
     */
    ItemStack[] getArmors();
}
