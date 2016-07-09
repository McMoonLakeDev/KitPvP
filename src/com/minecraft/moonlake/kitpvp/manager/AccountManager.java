package com.minecraft.moonlake.kitpvp.manager;

import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;

/**
 * Created by MoonLake on 2016/7/9.
 */
public final class AccountManager extends KitPvPManager {

    /**
     * 创建指定玩家的职业战争玩家对象
     *
     * @param name 玩家名
     * @return 职业战争玩家对象
     */
    public static KitPvPPlayer create(String name) {

        return getMain().getAccount().create(name);
    }

    /**
     * 获取指定玩家的职业战争玩家对象
     *
     * @param name 玩家名
     * @return 职业战争玩家对象 没有则返回 null
     */
    public static KitPvPPlayer get(String name) {

        return getMain().getAccount().get(name);
    }

    /**
     * 获取指定玩家是否拥有职业战争玩家对象
     *
     * @param name 玩家名
     * @return true 则拥有玩家对象 else 没有
     */
    public static boolean has(String name) {

        return getMain().getAccount().has(name);
    }

    /**
     * 关闭并清除指定玩家的职业战争玩家对象
     *
     * @param name 玩家名
     */
    public static void remove(String name) {

        getMain().getAccount().remove(name);
    }
}
