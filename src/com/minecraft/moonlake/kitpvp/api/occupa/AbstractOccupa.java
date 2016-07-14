package com.minecraft.moonlake.kitpvp.api.occupa;

import com.minecraft.moonlake.kitpvp.KitPvPPlugin;
import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.Skill;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MoonLake on 2016/7/9.
 */
public abstract class AbstractOccupa implements Occupa {

    private final OccupaType type;
    private final List<Skill> skillList;

    public AbstractOccupa(OccupaType type) {

        this.type = type;
        this.skillList = new ArrayList<>();
    }

    /**
     * 获取职业战争 KitPvP 实例对象
     *
     * @return 实例
     */
    protected final KitPvP getMain() {

        return KitPvPPlugin.getInstances();
    }

    /**
     * 获取此职业的类型
     *
     * @return 类型
     */
    @Override
    public OccupaType getType() {

        return type;
    }

    /**
     * 获取此职业的技能集合
     *
     * @return 技能集合
     */
    @Override
    public List<Skill> getSkillList() {

        return new ArrayList<>(skillList);
    }

    /**
     * 将此职业添加指定技能对象
     *
     * @param skill 技能
     */
    @Override
    public void addSkill(Skill skill) {

        skillList.add(skill);
    }

    /**
     * 获取此职业的最大血量
     *
     * @return 最大血量
     */
    public double getMaxHealth() {

        return 20d;
    }

    /**
     * 获取此职业的武器攻击力
     *
     * @return 武器攻击力
     */
    public double getWeaponDamage() {

        return 1d;
    }

    /**
     * 获取此职业的武器类型
     *
     * @return 武器类型
     */
    @Override
    public final Material getWeaponType() {

        return type.getWeapon();
    }

    /**
     * 获取此职业是否持盾
     *
     * @return true 则持盾 else 不持盾
     */
    public boolean hasShield() {

        return false;
    }

    /**
     * 获取此职业的武器
     *
     * @return 武器
     */
    public abstract ItemStack getWeapon();

    /**
     * 获取此职业的护甲
     *
     * @return 护甲
     */
    public abstract ItemStack[] getArmors();

    /**
     * 检测此职业的第一次组合
     *
     * @param type 组合类型
     * @return true 则通过 else 不通过
     */
    public abstract boolean checkComboFirst(SkillComboType type);
}
