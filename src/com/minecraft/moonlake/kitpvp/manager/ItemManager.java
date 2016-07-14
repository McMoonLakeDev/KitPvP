package com.minecraft.moonlake.kitpvp.manager;

import com.minecraft.moonlake.api.itemlib.ItemBuilder;
import com.minecraft.moonlake.type.potion.PotionEffectEnum;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by MoonLake on 2016/7/13.
 */
public final class ItemManager extends KitPvPManager {

    /**
     * 获取普通生命药水物品栈
     *
     * @param amount 数量
     * @return 生命药水
     */
    public static ItemStack baseHealthPotion(int amount) {

        return getMain().getMoonLake().getItemlib().createSplashPotion(PotionEffectEnum.HEALING.getUp(), amount);
    }

    /**
     * 获取普通食物物品栈
     *
     * @param amount 数量
     * @return 食物
     */
    public static ItemStack baseFoodItemStack(int amount) {

        return new ItemBuilder(Material.COOKED_BEEF).setAmount(amount).build();
    }

    public static ItemStack baseShield() {

        return new ItemBuilder(Material.SHIELD).setUnbreakable(true).build();
    }
}
