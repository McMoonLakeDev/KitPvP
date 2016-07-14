package com.minecraft.moonlake.kitpvp.util;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.account.PlayerAccount;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.util.player.PlayerUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by MoonLake on 2016/7/9.
 */
public class AccountUtil implements PlayerAccount {

    private final KitPvP main;
    private final Map<String, KitPvPPlayer> accountMap;

    public AccountUtil(KitPvP main) {

        this.main = main;
        this.accountMap = new HashMap<>();
    }

    /**
     * 创建指定玩家的职业战争玩家对象
     *
     * @param name 玩家名
     * @return 职业战争玩家对象
     */
    @Override
    public KitPvPPlayer create(String name) {

        KitPvPPlayer kitPvPPlayer = get(name);

        if(kitPvPPlayer == null) {

            kitPvPPlayer = new PlayerUtil(name);

            accountMap.put(name, kitPvPPlayer);
        }
        return kitPvPPlayer;
    }

    /**
     * 获取指定玩家的职业战争玩家对象
     *
     * @param name 玩家名
     * @return 职业战争玩家对象 没有则返回 null
     */
    @Override
    public KitPvPPlayer get(String name) {

        return has(name) ? accountMap.get(name) : null;
    }

    /**
     * 获取指定玩家是否拥有职业战争玩家对象
     *
     * @param name 玩家名
     * @return true 则拥有玩家对象 else 没有
     */
    @Override
    public boolean has(String name) {

        return accountMap.containsKey(name);
    }

    /**
     * 关闭并清除指定玩家的职业战争玩家对象
     *
     * @param name 玩家名
     */
    @Override
    public void remove(String name) {

        if(has(name)) {

            accountMap.remove(name);
        }
    }

    /**
     * 获取职业战争所有的在线玩家集合
     *
     * @return 玩家集合
     */
    @Override
    public Set<KitPvPPlayer> getOnlinePlayers() {

        return new HashSet<>(accountMap.values());
    }
}
