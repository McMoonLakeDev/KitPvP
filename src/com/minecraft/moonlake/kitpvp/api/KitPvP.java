package com.minecraft.moonlake.kitpvp.api;

import com.minecraft.moonlake.api.MoonLake;
import com.minecraft.moonlake.economy.api.MoonLakeEconomy;
import com.minecraft.moonlake.kitpvp.KitPvPPlugin;
import com.minecraft.moonlake.kitpvp.api.account.PlayerAccount;
import com.minecraft.moonlake.kitpvp.api.config.KitPvPConfig;
import com.minecraft.moonlake.kitpvp.listeners.block.FallingBlockListener;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;

import java.io.File;
import java.io.InputStream;

/**
 * Created by MoonLake on 2016/7/9.
 */
public interface KitPvP extends KitPvPInfo {

    /**
     * 获取插件的主类对象
     *
     * @return 主类
     */
    KitPvPPlugin getMain();

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
     * 获取月色之湖职业战争配置文件对象
     *
     * @return 配置文件对象
     */
    FileConfiguration getConfig();

    /**
     * 获取月色之湖职业战争配置文件对象
     *
     * @return 配置文件对象
     */
    KitPvPConfig getKConfig();

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

    /**
     * 获取月色之湖职业战争服务器
     *
     * @return Server
     */
    Server getServer();

    /**
     * 获取插件数据目录
     *
     * @return 目录
     */
    File getDataFolder();

    /**
     * 将插件内的资源文件保存到外部路径
     *
     * @param url 路径
     * @param replace 是否替换
     */
    void saveResource(String url, boolean replace);

    /**
     * 获取插件内的资源文件并转换为输入流
     *
     * @param url 路径
     * @return 输入流
     */
    InputStream getResource(String url);

    /**
     * 获取指定语言文件的指定 键 的值
     *
     * @param key 键
     * @return 值
     */
    String l18n(String key);

    /**
     * 获取指定语言文件的指定 键 的值
     *
     * @param key 键
     * @param args 参数
     * @return 值
     */
    String l18n(String key, Object... args);

    /**
     * 获取此插件是否为 Debug 调试模式
     *
     * @return true 则是 else 不是
     */
    boolean isDebug();

    /**
     * 获取掉落方块事件监听者对象
     *
     * @return 监听者对象
     */
    FallingBlockListener getFallingBlockListener();
}
