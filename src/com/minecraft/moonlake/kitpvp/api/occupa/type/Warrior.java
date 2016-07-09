package com.minecraft.moonlake.kitpvp.api.occupa.type;

import com.minecraft.moonlake.api.itemlib.ItemBuilder;
import com.minecraft.moonlake.kitpvp.api.occupa.AbstractOccupa;
import com.minecraft.moonlake.kitpvp.api.occupa.OccupaType;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.type.Shake;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by MoonLake on 2016/7/9.
 */
public class Warrior extends AbstractOccupa {

    public Warrior() {

        super(OccupaType.WARRIOR);

        addSkill(new Shake());
    }

    /**
     * 获取此职业的武器
     *
     * @return 武器
     */
    @Override
    public ItemStack getWeapon() {

        ItemStack item = getMain().getMoonLake().getItemlib().create(Material.IRON_SWORD, 0, 1, "&a碧水剑");
        item = unbreakable(item);

        return item;
    }

    /**
     * 获取此职业的护甲
     *
     * @return 护甲
     */
    @Override
    public ItemStack[] getArmors() {

        return new ItemStack[] {

                unbreakable(new ItemBuilder(Material.DIAMOND_BOOTS, 0).build()),
                unbreakable(new ItemBuilder(Material.IRON_LEGGINGS, 0).build()),
                unbreakable(new ItemBuilder(Material.IRON_CHESTPLATE, 0).build()),
                null
        };
    }
}
