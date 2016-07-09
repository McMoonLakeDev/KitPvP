package com.minecraft.moonlake.kitpvp.api;

import com.minecraft.moonlake.api.MoonLake;
import com.minecraft.moonlake.economy.api.MoonLakeEconomy;
import com.minecraft.moonlake.kitpvp.api.account.PlayerAccount;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.plugin.PluginManager;

/**
 * Created by MoonLake on 2016/7/9.
 */
public interface KitPvP extends KitPvPInfo {

    /**
     * 获取月色之湖前置核心 API 插件实例对象
     *
     * @return MoonLake
     */
    MoonLake getMoonLake();

    /**
     * 月色之湖月色之湖经济插件实例对象
     *
     * @return MoonLakeEconomy
     */
    MoonLakeEconomy getMoonLakeEconomy();

    /**
     * 获取职业战争玩家账户系统
     *
     * @return PlayerAccount
     */
    PlayerAccount getAccount();

    /**
     * 给控制台输出日志信息
     *
     * @param msg 日志
     */
    void log(String msg);

    /**
     * 获取前置创世神 WorldEdit 插件实例对象
     *
     * @return WorldEdit
     */
    WorldEditPlugin getWorldEdit();

    /**
     * 获取插件的类加载器实例对象
     *
     * @return 类加载器
     */
    ClassLoader getClassLoader$();

    /**
     * 获取职业战争插件管理对象
     *
     * @return 插件管理对象
     */
    PluginManager getPluginManager();
}
