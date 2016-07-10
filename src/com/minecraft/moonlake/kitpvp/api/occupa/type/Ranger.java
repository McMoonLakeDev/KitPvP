package com.minecraft.moonlake.kitpvp.api.occupa.type;

import com.minecraft.moonlake.api.itemlib.ItemBuilder;
import com.minecraft.moonlake.kitpvp.api.occupa.AbstractOccupa;
import com.minecraft.moonlake.kitpvp.api.occupa.OccupaType;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by MoonLake on 2016/7/9.
 */
public class Ranger extends AbstractOccupa {

    public Ranger() {

        super(OccupaType.RANGER);
    }

    /**
     * 获取此职业的武器
     *
     * @return 武器
     */
    @Override
    public ItemStack getWeapon() {

        return new ItemBuilder(getWeaponType(), 0, "&6枯叶灵弓")
                .setUnbreakable(true)
                .build();
    }

    /**
     * 获取此职业的护甲
     *
     * @return 护甲
     */
    @Override
    public ItemStack[] getArmors() {

        return new ItemStack[] {

                new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(Color.GREEN).setUnbreakable(true).build(),
                new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherColor(Color.GREEN).setUnbreakable(true).build(),
                new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherColor(Color.GREEN).setUnbreakable(true).build(),
                new ItemBuilder(Material.LEATHER_HELMET).setLeatherColor(Color.GREEN).setUnbreakable(true).build(),
        };
    }

    /**
     * 检测此职业的第一次组合
     *
     * @param type 组合类型
     * @return true 则通过 else 不通过
     */
    @Override
    public boolean checkComboFirst(SkillComboType type) {

        return type != SkillComboType.RIGHT;
    }
}
