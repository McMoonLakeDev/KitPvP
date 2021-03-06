package com.minecraft.moonlake.kitpvp.api.occupa;

import com.minecraft.moonlake.kitpvp.api.occupa.skill.Skill;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import org.bukkit.Material;
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
     * 获取此职业的武器攻击力
     *
     * @return 武器攻击力
     */
    double getWeaponDamage();

    /**
     * 获取此职业的武器类型
     *
     * @return 武器类型
     */
    Material getWeaponType();

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

    /**
     * 获取此职业是否持盾
     *
     * @return true 则持盾 else 不持盾
     */
    boolean hasShield();

    /**
     * 检测此职业的第一次组合
     *
     * @param type 组合类型
     * @return true 则通过 else 不通过
     */
    boolean checkComboFirst(SkillComboType type);
}
