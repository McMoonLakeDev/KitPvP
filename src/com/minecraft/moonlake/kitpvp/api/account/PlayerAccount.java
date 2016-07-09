package com.minecraft.moonlake.kitpvp.api.account;

import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;

/**
 * Created by MoonLake on 2016/7/9.
 */
public interface PlayerAccount {

    /**
     * 创建指定玩家的职业战争玩家对象
     *
     * @param name 玩家名
     * @return 职业战争玩家对象
     */
    KitPvPPlayer create(String name);

    /**
     * 获取指定玩家的职业战争玩家对象
     *
     * @param name 玩家名
     * @return 职业战争玩家对象 没有则返回 null
     */
    KitPvPPlayer get(String name);

    /**
     * 获取指定玩家是否拥有职业战争玩家对象
     *
     * @param name 玩家名
     * @return true 则拥有玩家对象 else 没有
     */
    boolean has(String name);

    /**
     * 关闭并清除指定玩家的职业战争玩家对象
     *
     * @param name 玩家名
     */
    void remove(String name);
}
