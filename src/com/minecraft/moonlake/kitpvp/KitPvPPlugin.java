package com.minecraft.moonlake.kitpvp;

import com.minecraft.moonlake.MoonLakePlugin;
import com.minecraft.moonlake.api.MoonLake;
import com.minecraft.moonlake.economy.Economy;
import com.minecraft.moonlake.economy.api.MoonLakeEconomy;
import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.account.PlayerAccount;
import com.minecraft.moonlake.kitpvp.api.config.KitPvPConfig;
import com.minecraft.moonlake.kitpvp.api.language.KitPvPLanguage;
import com.minecraft.moonlake.kitpvp.config.KitPvPConfigUtil;
import com.minecraft.moonlake.kitpvp.language.KitPvPLanguageUtil;
import com.minecraft.moonlake.kitpvp.listeners.block.FallingBlockListener;
import com.minecraft.moonlake.kitpvp.listeners.player.PlayerBaseListener;
import com.minecraft.moonlake.kitpvp.listeners.player.PlayerComboListener;
import com.minecraft.moonlake.kitpvp.manager.ConfigManager;
import com.minecraft.moonlake.kitpvp.util.AccountUtil;
import com.minecraft.moonlake.util.Util;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by MoonLake on 2016/6/28.
 */
public class KitPvPPlugin extends JavaPlugin implements KitPvP {

    private MoonLake moonLake;
    private MoonLakeEconomy moonLakeEconomy;
    private PlayerAccount playerAccount;
    private KitPvPConfig kitPvPConfig;
    private KitPvPLanguage kitPvPLanguage;

    private ClassLoader classLoader;
    private PluginManager pluginManager;
    private WorldEditPlugin worldEditPlugin;
    private final PluginDescriptionFile pdf;
    private final ConsoleCommandSender console;

    private static KitPvP MAIN;
    private static boolean DEBUG;

    private FallingBlockListener fallingBlockListener;

    public KitPvPPlugin() {

        this.pdf = this.getDescription();
        this.console = this.getServer().getConsoleSender();
    }

    @Override
    public void onEnable() {

        setupDepend();

        MAIN = this;

        classLoader = getClassLoader();
        pluginManager = getServer().getPluginManager();

        initFolder();

        kitPvPConfig = new KitPvPConfigUtil(getInstance());

        initDebugMode();

        kitPvPLanguage = new KitPvPLanguageUtil(getInstance());
        kitPvPLanguage.loadKitPvPLanguage();

        playerAccount = new AccountUtil(getInstance());

        fallingBlockListener = new FallingBlockListener(getInstance());

        pluginManager.registerEvents(fallingBlockListener, this);
        pluginManager.registerEvents(new PlayerBaseListener(getInstance()), this);
        pluginManager.registerEvents(new PlayerComboListener(getInstance()), this);

        this.log("月色之湖职业战争 KitPvP 插件 v" + getPluginVersion() + " 成功加载.");
    }

    @Override
    public void onDisable() {


    }

    /**
     * 加载插件前置需求
     */
    private void setupDepend() {

        if(!this.setupMoonLake()) {

            this.log("前置月色之湖核心API插件加载失败.");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if(!this.setupMoonLakeEconomy()) {

            this.log("前置月色之湖经济插件加载失败.");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if(!this.setupWorldEdit()) {

            this.log("前置创世神 WorldEdit 插件加载失败.");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    /**
     * 初始化插件的 Debug 调试模式
     */
    private void initDebugMode() {

        DEBUG = ConfigManager.get("Debug").asBoolean();

        if(DEBUG) {

            log("已启动插件 Debug 调试模式.");
        }
    }

    /**
     * 初始化插件本地数据目录
     */
    private void initFolder() {

        if(!getDataFolder().exists())
            getDataFolder().mkdir();
        File config = new File(getDataFolder(), "config.yml");
        if(!config.exists())
            saveDefaultConfig();
    }

    /**
     * 加载月色之湖前置核心 API 插件
     *
     * @return 是否加载成功
     */
    private boolean setupMoonLake() {

        Plugin plugin = this.getServer().getPluginManager().getPlugin("MoonLake");
        return plugin != null && plugin instanceof MoonLakePlugin && (this.moonLake = ((MoonLakePlugin)plugin).getInstance()) != null;
    }

    /**
     * 加载月色之湖前置经济插件
     *
     * @return 是否加载成功
     */
    private boolean setupMoonLakeEconomy() {

        Plugin plugin = this.getServer().getPluginManager().getPlugin("MoonLakeEconomy");
        return plugin != null && plugin instanceof Economy && (this.moonLakeEconomy = ((Economy)plugin).getEconomy()) != null;
    }

    /**
     * 加载前置 WorldEdit 创世神插件
     *
     * @return 是否加载成功
     */
    private boolean setupWorldEdit() {

        Plugin plugin = this.getServer().getPluginManager().getPlugin("WorldEdit");
        return plugin != null && plugin instanceof WorldEditPlugin && (this.worldEditPlugin = (WorldEditPlugin)plugin) != null;
    }

    /**
     * 获取职业战争 KitPvP 实例对象
     *
     * @return 实例
     */
    public KitPvP getInstance() {

        return MAIN;
    }

    /**
     * 获取职业战争 KitPvP 实例对象
     *
     * @return 实例
     */
    @Deprecated
    public static KitPvP getInstances() {

        return MAIN;
    }

    /**
     * 获取插件的主类对象
     *
     * @return 主类
     */
    @Override
    public KitPvPPlugin getMain() {

        return this;
    }

    /**
     * 获取月色之湖前置核心 API 插件实例对象
     *
     * @return MoonLake
     */
    @Override
    public MoonLake getMoonLake() {

        return moonLake;
    }

    /**
     * 月色之湖月色之湖经济插件实例对象
     *
     * @return MoonLakeEconomy
     */
    @Override
    public MoonLakeEconomy getMoonLakeEconomy() {

        return moonLakeEconomy;
    }

    /**
     * 获取职业战争玩家账户系统
     *
     * @return PlayerAccount
     */
    @Override
    public PlayerAccount getAccount() {

        return playerAccount;
    }

    /**
     * 给控制台输出日志信息
     *
     * @param msg 日志
     */
    @Override
    public void log(String msg) {

        this.console.sendMessage("[MoonLakeMMORPG] " + Util.color(msg));
    }

    /**
     * 获取前置创世神 WorldEdit 插件实例对象
     *
     * @return WorldEdit
     */
    @Override
    public WorldEditPlugin getWorldEdit() {

        return worldEditPlugin;
    }

    /**
     * 获取月色之湖职业战争配置文件对象
     *
     * @return 配置文件对象
     */
    @Override
    public KitPvPConfig getKConfig() {

        return kitPvPConfig;
    }

    /**
     * 获取插件的类加载器实例对象
     *
     * @return 类加载器
     */
    @Override
    public ClassLoader getClassLoader$() {

        return classLoader;
    }

    /**
     * 获取月色之湖大型多人在线角色扮演插件管理对象
     *
     * @return 插件管理对象
     */
    @Override
    public PluginManager getPluginManager() {

        return pluginManager;
    }

    /**
     * 获取指定语言文件的指定 键 的值
     *
     * @param key 键
     * @return 值
     */
    @Override
    public String l18n(String key) {

        return kitPvPLanguage.l18n(key);
    }

    /**
     * 获取指定语言文件的指定 键 的值
     *
     * @param key  键
     * @param args 参数
     * @return 值
     */
    @Override
    public String l18n(String key, Object... args) {

        return kitPvPLanguage.l18n(key, args);
    }

    /**
     * 获取此插件是否为 Debug 调试模式
     *
     * @return true 则是 else 不是
     */
    @Override
    public boolean isDebug() {

        return DEBUG;
    }

    /**
     * 获取掉落方块事件监听者对象
     *
     * @return 监听者对象
     */
    @Override
    public FallingBlockListener getFallingBlockListener() {

        return fallingBlockListener;
    }

    /**
     * 获取插件的称号
     *
     * @return Prefix
     */
    @Override
    public String getPluginPrefix() {

        return pdf.getPrefix();
    }

    /**
     * 获取插件的名字
     *
     * @return Name
     */
    @Override
    public String getPluginName() {

        return pdf.getName();
    }

    /**
     * 获取插件的主类
     *
     * @return MainClass
     */
    @Override
    public String getPluginMain() {

        return pdf.getMain();
    }

    /**
     * 获取插件的版本
     *
     * @return Version
     */
    @Override
    public String getPluginVersion() {

        return pdf.getVersion();
    }

    /**
     * 获取插件的网站
     *
     * @return Website
     */
    @Override
    public String getPluginWebsite() {

        return pdf.getWebsite();
    }

    /**
     * 获取插件的简介
     *
     * @return Description
     */
    @Override
    public String getPluginDescription() {

        return pdf.getDescription();
    }

    /**
     * 获取插件的作者
     *
     * @return Auther
     */
    @Override
    public Set<String> getPluginAuthers() {

        return new HashSet<String>(pdf.getAuthors());
    }

    /**
     * 获取插件的依赖
     *
     * @return Depend
     */
    @Override
    public Set<String> getPluginDepends() {

        return new HashSet<String>(pdf.getDepend());
    }

    /**
     * 获取插件的软依赖
     *
     * @return SoftDepend
     */
    @Override
    public Set<String> getPluginSoftDepends() {

        return new HashSet<String>(pdf.getSoftDepend());
    }
}
