package com.minecraft.moonlake.kitpvp.api.player.scoreboard;

/**
 * Created by MoonLake on 2016/7/11.
 */
public interface KitPvPScoreboard {

    /**
     * 注册此玩家的职业战争计分板
     */
    void register();

    /**
     * 卸载此玩家的职业战争计分板
     */
    void unregister();

    /**
     * 更新此玩家的杀敌数
     *
     * @param newKill 新的杀敌数
     */
    void updateKill(int newKill);

    /**
     * 更新此玩家的死亡数
     *
     * @param newDeath 新的死亡数
     */
    void updateDeath(int newDeath);
}
