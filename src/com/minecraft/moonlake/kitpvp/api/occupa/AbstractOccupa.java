package com.minecraft.moonlake.kitpvp.api.occupa;

import com.minecraft.moonlake.kitpvp.KitPvPPlugin;
import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.Skill;
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
     * 将指定物品栈对象设置为无法破坏
     *
     * @param item 物品栈
     * @return 物品栈对象
     */
    protected final ItemStack unbreakable(ItemStack item) {

        return getMain().getMoonLake().getItemlib().setUnbreakable(item, true);
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
}
