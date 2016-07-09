package com.minecraft.moonlake.kitpvp.api.occupa.type;

import com.minecraft.moonlake.kitpvp.api.occupa.AbstractOccupa;
import com.minecraft.moonlake.kitpvp.api.occupa.OccupaType;
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

        return null;
    }

    /**
     * 获取此职业的护甲
     *
     * @return 护甲
     */
    @Override
    public ItemStack[] getArmors() {

        return new ItemStack[0];
    }
}
