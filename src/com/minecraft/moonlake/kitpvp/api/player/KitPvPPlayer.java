package com.minecraft.moonlake.kitpvp.api.player;

import com.minecraft.moonlake.api.player.MoonLakePlayer;
import com.minecraft.moonlake.exception.player.PlayerNotOnlineException;
import com.minecraft.moonlake.kitpvp.api.occupa.Occupa;
import com.minecraft.moonlake.kitpvp.api.occupa.OccupaGUI;
import com.minecraft.moonlake.kitpvp.api.occupa.OccupaType;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillCombo;
import com.minecraft.moonlake.kitpvp.api.player.bossbar.KitPvPBossbar;
import com.minecraft.moonlake.kitpvp.api.player.scoreboard.KitPvPScoreboard;
import com.minecraft.moonlake.kitpvp.rank.KitPvPRank;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Map;

/**
 * Created by MoonLake on 2016/7/9.
 */
public interface KitPvPPlayer extends MoonLakePlayer {

    /**
     * 获取此玩家的名称
     *
     * @return 名称
     */
    String getName();

    /**
     * 获取此玩家的 Bukkit 玩家对象
     *
     * @return Bukkit 玩家对象
     */
    Player getBukkitPlayer();

    /**
     * 获取此玩家的职业
     *
     * @return 职业对象
     */
    Occupa getOccupa();

    /**
     * 获取此玩家的职业 GUI 对象
     *
     * @return 职业 GUI 对象
     */
    OccupaGUI getOccupaGUI();

    /**
     * 获取此玩家的职业类型
     *
     * @return 职业类型
     */
    OccupaType getOccupaType();

    /**
     * 设置此玩家的职业
     *
     * @param occupa 职业对象
     */
    void setOccupa(Occupa occupa);

    /**
     * 获取此玩家的技能组合对象
     *
     * @return 技能组合对象
     */
    SkillCombo getSkillCombo();

    /**
     * 获取此玩家的职业战争计分板对象
     *
     * @return 计分板对象
     */
    KitPvPScoreboard getKitPvPScoreboard();

    /**
     * 获取此玩家的击杀数
     *
     * @return 击杀数
     */
    int getKill();

    /**
     * 设置此玩家的击杀数
     *
     * @param newKill 新的击杀数
     */
    void setKill(int newKill);

    /**
     * 获取此玩家的死亡数
     *
     * @return 死亡数
     */
    int getDeath();

    /**
     * 设置此玩家的死亡数
     *
     * @param newDeath 新的死亡数
     */
    void setDeath(int newDeath);

    /**
     * 获取此玩家的 KD 比值
     *
     * @return KD 比值
     */
    double getKD();

    /**
     * 获取此玩家的 Rank 等级
     *
     * @return Rank
     */
    KitPvPRank getRank();

    /**
     * 设置此玩家的 Rank 等级
     *
     * @param kitPvPRank Rank
     */
    void setRank(KitPvPRank kitPvPRank);

    /**
     * 获取此玩家的血条对象
     *
     * @return 血条对象
     */
    KitPvPBossbar getKitPvPBossbar();

    /**
     * 给此玩家发送语言文件指定 键 的值
     *
     * @param key 键
     */
    void l18n(String key);

    /**
     * 给此玩家发送语言文件指定 键 的值
     *
     * @param key 键
     * @param args 参数
     */
    void l18n(String key, Object... args);

    /**
     * 获取此玩家的击杀者玩家对象
     *
     * @return 击杀者玩家对象 没有则返回 null
     */
    KitPvPPlayer getKiller();

    /**
     * 获取此玩家的显示名称
     *
     * @return 显示名称
     */
    String getDisplayName();

    /**
     * 设置此玩家的显示名称
     *
     * @param displayName 显示名称
     */
    void setDisplayName(String displayName);

    /**
     * 获取此玩家的 TAB 列表名称
     *
     * @return 列表名称
     */
    String getListName();

    /**
     * 设置此玩家的 TAB 列表名称
     *
     * @param listName 列表名称
     */
    void setListName(String listName);

    /**
     * 将此玩家强制发送聊天文本
     *
     * @param msg 文本消息
     */
    void chat(String msg);

    /**
     * 给此玩家发送消息
     *
     * @param msg 消息
     */
    void send(String msg);

    /**
     * 给此玩家发送消息
     *
     * @param msg 消息
     */
    void send(String[] msg);

    /**
     * 给此玩家发送基础消息
     *
     * @param bc 基础消息
     */
    void send(BaseComponent bc);

    /**
     * 给此玩家发送基础消息
     *
     * @param bcs 基础消息
     */
    void send(BaseComponent... bcs);

    /**
     * 获取此玩家的当前血量
     *
     * @return 血量
     */
    double getHealth();

    /**
     * 获取此玩家的最大生命
     *
     * @return 最大血量
     */
    double getMaxHealth();

    /**
     * 给予此玩家指定数量的血量
     *
     * @param amount 数量
     */
    void giveHealth(double amount);

    /**
     * 减少此玩家指定数量的血量
     *
     * @param amount 数量
     */
    void takeHealth(double amount);

    /**
     * 获取此玩家的当前经验
     *
     * @return 经验
     */
    float getXp();

    /**
     * 获取此玩家的当前等级
     *
     * @return 等级
     */
    int getLevel();

    /**
     * 设置此玩家的当前血量
     *
     * @param health 血量
     */
    void setHealth(double health);

    /**
     * 设置此玩家的最大血量
     *
     * @param maxHealth 最大血量
     */
    void setMaxHealth(double maxHealth);

    /**
     * 给予此玩家指定经验
     *
     * @param xp 经验
     */
    void giveXp(float xp);

    /**
     * 设置此玩家的经验值
     *
     * @param xp 经验值
     */
    void setXp(float xp);

    /**
     * 设置此玩家的当前等级
     *
     * @param level 等级
     */
    void setLevel(int level);

    /**
     * 获取此玩家的飞行速度
     *
     * @return 飞行速度
     */
    float getFlySpeed();

    /**
     * 获取此玩家的饱食度
     *
     * @return 饱食度
     */
    int getFoodLevel();

    /**
     * 设置此玩家的飞行速度
     *
     * @param flySpeed 飞行速度
     */
    void setFlySpeed(float flySpeed);

    /**
     * 设置此玩家的饱食度
     *
     * @param foodLevel 饱食度
     */
    void setFoodLevel(int foodLevel);

    /**
     * 获取此玩家的计分板对象
     *
     * @return 计分板对象
     */
    Scoreboard getScoreboard();

    /**
     * 设置此玩家的计分板对象
     *
     * @param scoreboard 计分板对象
     */
    void setScoreboard(Scoreboard scoreboard);

    /**
     * 获取此玩家在观察者模式的追踪目标实体
     *
     * @return 目标实体 没有则返回 null
     */
    Entity getSpectatorTarget();

    /**
     * 设置此玩家在观察者模式的追踪目标实体对象
     *
     * @param entity 实体对象
     */
    void setSpectatorTarget(Entity entity);

    /**
     * 将此玩家强制受到伤害
     *
     * @param damage  伤害
     * @param damager 伤害者
     */
    void damage(double damage, LivingEntity damager);

    /**
     * 获取此玩家的当前所在位置
     *
     * @return 位置
     */
    Location getLocation();

    /**
     * 获取此玩家的物品栏背包
     *
     * @return 物品栏
     */
    PlayerInventory getInventory();

    /**
     * 判断此对象是否和另个对象完全符合
     *
     * @param object 对象
     * @return 是否符合、一致、匹配
     */
    boolean equals(Object object);

    /**
     * 获取此玩家是否之前玩过服务器
     *
     * @return true 之前玩过 else 第一次玩服务器
     */
    boolean hasBeforePlayed();

    /**
     * 获取此玩家是否在地面
     *
     * @return 是否在地面
     */
    boolean isOnGround();

    /**
     * 获取此玩家是否飞行模式中
     *
     * @return true 则为飞行模式 else 没有
     */
    boolean isFly();

    /**
     * 获取此玩家是否允许飞行
     *
     * @return true 则允许飞行 else 不允许
     */
    boolean isAllowFly();

    /**
     * 设置此玩家是否允许飞行
     *
     * @param flag 是否允许
     */
    void setAllowFly(boolean flag);

    /**
     * 获取此玩家的所在世界对象
     *
     * @return 世界
     */
    World getWorld();

    /**
     * 更新此玩家的物品栏背包
     */
    void updateInventory();

    /**
     * 将此玩家正在打开的物品栏关闭
     */
    void closeInventory();

    /**
     * 获取此玩家的行走速度
     *
     * @return 行走速度
     */
    float getWalkSpeed();

    /**
     * 设置此玩家的行走速度
     *
     * @param speed 行走速度
     */
    void setWalkSpeed(float speed);

    /**
     * 获取此玩家的当前位置 X 坐标
     *
     * @return X 坐标
     */
    int getX();

    /**
     * 获取此玩家的当前位置 Y 坐标
     *
     * @return Y 坐标
     */
    int getY();

    /**
     * 获取此玩家的当前位置 Z 坐标
     *
     * @return Z 坐标
     */
    int getZ();

    /**
     * 获取此玩家的当前位置 X 坐标
     *
     * @return X 坐标
     */
    double getDoubleX();

    /**
     * 获取此玩家的当前位置 Y 坐标
     *
     * @return Y 坐标
     */
    double getDoubleY();

    /**
     * 获取此玩家的当前位置 Z 坐标
     *
     * @return Z 坐标
     */
    double getDoubleZ();

    /**
     * 将此玩家传送到指定实体身边
     *
     * @param entity 实体
     */
    void teleport(Entity entity);

    /**
     * 将此玩家传送到指定位置
     *
     * @param location 位置
     */
    void teleport(Location location);

    /**
     * 将此玩家传送到玩家当前世界的指定 xyz 坐标
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param z Z 坐标
     */
    void teleport(int x, int y, int z);

    /**
     * 将此玩家传送到玩家当前世界的指定 xyz 坐标
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param z Z 坐标
     */
    void teleport(double x, double y, double z);

    /**
     * 将此玩家传送到指定世界的指定 xyz 坐标
     *
     * @param world 指定世界
     * @param x     X 坐标
     * @param y     Y 坐标
     * @param z     Z 坐标
     */
    void teleport(World world, int x, int y, int z);

    /**
     * 将此玩家传送到指定世界的指定 xyz 坐标
     *
     * @param world 指定世界
     * @param x     X 坐标
     * @param y     Y 坐标
     * @param z     Z 坐标
     */
    void teleport(World world, double x, double y, double z);

    /**
     * 获取此玩家准星的目标方块
     *
     * @param distance 距离
     * @return 准星的方块 没有则返回 null
     */
    Block getTargetBlock(int distance);

    /**
     * 给此玩家在当前位置播放音效
     *
     * @param sound  音效名
     * @param volume 音量
     * @param pitch  音调
     */
    void playSound(String sound, float volume, float pitch);

    /**
     * 给此玩家在当前位置播放音效
     *
     * @param sound  音效
     * @param volume 音量
     * @param pitch  音调
     */
    void playSound(Sound sound, float volume, float pitch);

    /**
     * 给此玩家在当前位置播放音符
     *
     * @param instrument 仪器
     * @param note 音符
     */
    void playNote(Instrument instrument, Note note);

    /**
     * 将指定音效停止播放给此玩家
     *
     * @param sound 音效名
     */
    void stopSound(String sound);

    /**
     * 将指定音效停止播放给此玩家
     *
     * @param sound 音效
     */
    void stopSound(Sound sound);

    /**
     * 获取玩家当前位置距离目标位置的距离
     *
     * @param target 目标位置
     * @return 距离
     */
    double distance(Location target);

    /**
     * 获取此玩家的主手中物品
     *
     * @return 主手中物品
     */
    ItemStack getItemInMainHand();

    /**
     * 获取此玩家的副手中物品
     *
     * @return 副手中物品
     */
    ItemStack getItemInOffHand();

    /**
     * 设置此玩家的主手中物品
     *
     * @param item 物品栈
     */
    void setItemInMainHand(ItemStack item);

    /**
     * 设置此玩家的副手中物品
     *
     * @param item 物品栈
     */
    void setItemInOffHand(ItemStack item);

    /**
     * 给此玩家打开指定物品栏对象
     *
     * @param inv 物品栏对象
     */
    void openInventory(Inventory inv);

    /**
     * 获取此玩家打开的上面物品栏对象
     *
     * @return 上面物品栏对象
     */
    Inventory getTopInventory();

    /**
     * 将此玩家添加药水效果
     *
     * @param type 药水效果类型
     * @param level 等级
     * @param time 时间 (Tick)
     */
    void addPotionEffect(PotionEffectType type, int level, int time);

    /**
     * 将此玩家添加药水效果
     *
     * @param type 药水效果类型
     * @param level 等级
     * @param time 时间 (Tick)
     * @param ambient 是否产生更多的药水半透明粒子
     */
    void addPotionEffect(PotionEffectType type, int level, int time, boolean ambient);

    /**
     * 将此玩家添加药水效果
     *
     * @param type 药水效果类型
     * @param level 等级
     * @param time 时间 (Tick)
     * @param ambient 是否产生更多的药水半透明粒子
     * @param particles 是否拥有粒子效果
     */
    void addPotionEffect(PotionEffectType type, int level, int time, boolean ambient, boolean particles);

    /**
     * 将此玩家添加药水效果
     *
     * @param type  药水效果类型
     * @param level 等级
     * @param time  时间 (Tick)
     * @param ambient 是否产生更多的药水半透明粒子
     * @param particles 是否拥有粒子效果
     * @param color 药水粒子的颜色
     */
    void addPotionEffect(PotionEffectType type, int level, int time, boolean ambient, boolean particles, Color color);

    /**
     * 获取此玩家是否拥有药水效果
     *
     * @param type 药水效果类型
     * @return true 则拥有此效果类型 else 没有
     */
    boolean hasPotionEffect(PotionEffectType type);

    /**
     * 清除此玩家的指定药水效果
     *
     * @param type 药水效果类型
     */
    void removePotionEffect(PotionEffectType type);

    /**
     * 给玩家背包给予指定物品栈
     *
     * @param items 物品栈
     * @return 未成功添加到玩家背包的物品栈集合
     */
    @Deprecated
    Map<Integer, ItemStack> addItemStack(ItemStack... items);

    /**
     * 给玩家背包清除指定物品栈
     *
     * @param items 物品栈
     * @return 未成功清除到玩家背包的物品栈集合
     */
    @Deprecated
    Map<Integer, ItemStack> removeItemStack(ItemStack... items);

    /**
     * 获取此玩家是否拥有指定权限
     *
     * @param permission 权限
     * @return true 拥有此权限 else 没有
     */
    boolean hasPermission(String permission);

    /**
     * 将此玩家进行踢出服务器
     */
    void onKick();

    /**
     * 将此玩家进行踢出服务器
     *
     * @param message 踢出的消息
     */
    void onKick(String message);

    /**
     * 将此玩家进行封禁名称
     */
    void onBanName();

    /**
     * 将此玩家进行封禁名称
     *
     * @param cause 原因
     */
    void onBanName(String cause);

    /**
     * 将此玩家进行封禁名称
     *
     * @param cause 原因
     * @param time 封禁的时间
     */
    void onBanName(String cause, Date time);

    /**
     * 将此玩家进行封禁 IP
     */
    void onBanIp();

    /**
     * 将此玩家进行封禁 IP
     *
     * @param cause 原因
     */
    void onBanIp(String cause);

    /**
     * 将此玩家进行封禁 IP
     *
     * @param cause 原因
     * @param time 封禁的时间
     */
    void onBanIp(String cause, Date time);

    /**
     * 将此玩家进行解封
     */
    void onUnBan();

    /**
     * 获取此玩家是否潜行中
     *
     * @return true 则潜行 else 没有
     */
    boolean isShift();

    /**
     * 获取此玩家是否冲刺中
     *
     * @return true 则冲刺中 else 没有
     */
    boolean isSprinting();

    /**
     * 获取此玩家是否是管理员
     *
     * @return true 则是管理员 else 不是
     */
    boolean isOp();

    /**
     * 设置此玩家的矢量
     *
     * @param vector 矢量
     */
    void setVelocity(Vector vector);

    /**
     * 设置此玩家的无敌时间 (Tick)
     *
     * @param ticks 时间
     */
    void setNoDamageTicks(int ticks);

    /**
     * 将此玩家从交通工具上下来
     */
    void onEject();

    /**
     * 在玩家当前位置创建爆炸
     *
     * @param power 强度
     */
    void createExplosion(float power);

    /**
     * 在玩家当前位置创建爆炸
     *
     * @param power 强度
     * @param setFire 是否着火
     */
    void createExplosion(float power, boolean setFire);

    /**
     * 在玩家当前位置创建爆炸
     *
     * @param power 强度
     * @param setFire 是否着火
     * @param breakBlock 是否破坏方块
     */
    void createExplosion(float power, boolean setFire, boolean breakBlock);

    /**
     * 将此玩家强制执行命令
     *
     * @param command 命令
     * @return true 则执行成功 else 没有
     */
    boolean performCommand(String command);

    /**
     * 将指定目标玩家进行隐藏
     *
     * @param target 目标玩家名
     */
    void onHide(String target);

    /**
     * 将指定目标玩家进行显示
     *
     * @param target 目标玩家名
     */
    void onShow(String target);

    /**
     * 获取此玩家是否能看到指定目标玩家
     *
     * @param target 目标玩家名
     * @return true 则可以看到 else 看不到
     */
    boolean canSee(String target);

    /**
     * 设置此玩家的客户端时间
     *
     * @param time 时间
     */
    void setTime(long time);

    /**
     * 设置此玩家的客户端时间
     *
     * @param time 时间
     * @param relative 是否相对的
     */
    void setTime(long time, boolean relative);

    /**
     * 设置此玩家的客户端天气
     *
     * @param weather 天气类型
     */
    void setWeather(WeatherType weather);

    /**
     * 获取此玩家的游戏模式
     *
     * @return 游戏模式
     */
    GameMode getGameMode();

    /**
     * 设置此玩家的游戏模式
     *
     * @param mode 模式
     */
    void setGameMode(int mode);

    /**
     * 设置此玩家的游戏模式
     *
     * @param mode 模式
     */
    void setGameMode(GameMode mode);

    /**
     * 重置此玩家的血量
     */
    void resetMaxHealth();

    /**
     * 清除此玩家的所有药水效果
     */
    void clearPotionEffect();

    /**
     * 获取此玩家的眼部位置
     *
     * @return 眼部位置
     */
    Location getEyeLocation();

    /**
     * 获取此玩家的方向矢量对象
     *
     * @return 方向矢量
     */
    Vector getDirection();

    /**
     * 获取此玩家的最后受伤原因
     *
     * @return 受伤原因
     */
    EntityDamageEvent getLastDamageCause();

    /**
     * 获取此玩家的网络套接字地址
     *
     * @return 网络套接字地址
     */
    InetSocketAddress getAddress();

    /**
     * 获取此玩家的网络套接字地址 IP
     *
     * @return 网络套接字地址 IP 如果未解析则返回 127.0.0.1
     */
    String getIp();

    /**
     * 获取此玩家的网络套接字地址端口号
     *
     * @return 网络套接字地址端口号
     */
    int getPort();

    /**
     * 获取此玩家的网络 Ping 值
     *
     * @return Ping 值
     */
    int getPing();

    /**
     * 给玩家发送标题数据包
     *
     * @param player 玩家名
     * @param title 标题
     * @throws PlayerNotOnlineException 玩家不在线则抛出异常
     */
    void sendTitlePacket(String title);

    /**
     * 给玩家发送标题数据包
     *
     * @param player 玩家名
     * @param title 标题
     * @param subTitle 子标题
     * @throws PlayerNotOnlineException 玩家不在线则抛出异常
     */
    void sendTitlePacket(String title, String subTitle);

    /**
     * 给玩家发送标题数据包
     *
     * @param player 玩家名
     * @param title 标题
     * @param drTime 淡入时间
     * @param plTime 停留时间
     * @param dcTime 淡出时间
     * @throws PlayerNotOnlineException 玩家不在线则抛出异常
     */
    void sendTitlePacket(String title, int drTime, int plTime, int dcTime);

    /**
     * 给玩家发送标题数据包
     *
     * @param player 玩家名
     * @param title 标题
     * @param subTitle 子标题
     * @param drTime 淡入时间
     * @param plTime 停留时间
     * @param dcTime 淡出时间
     * @throws PlayerNotOnlineException 玩家不在线则抛出异常
     */
    void sendTitlePacket(String title, String subTitle, int drTime, int plTime, int dcTime);

    /**
     * 给玩家发送中心聊天数据包
     *
     * @param player 玩家名
     * @param message 消息
     * @throws PlayerNotOnlineException 玩家不在线则抛出异常
     */
    void sendMainChatPacket(String message);

    /**
     * 给玩家发送Tab列表数据包
     *
     * @param player 玩家名
     * @param header 头文本
     * @param footer 脚文本
     * @throws PlayerNotOnlineException 玩家不在线则抛出异常
     */
    void sendTabListPacket(String header, String footer);

    /**
     * 给玩家发送物品冷却时间数据包
     *
     * @param player 玩家名
     * @param type 物品类型
     * @param tick 冷却的时间
     */
    void sendItemCooldownPacket(Material type, int tick);

    /**
     * 获取玩家某个物品是否还有冷却时间
     *
     * @param player 玩家名
     * @param type 物品类型
     * @return true 还有冷却时间 else 无冷却时间
     */
    boolean hasItemCooldown(Material type);

    /**
     * 比较两个对象
     *
     * @param target 目标对象
     * @return true 则对象相同 else 不同
     */
    @Override
    int compareTo(MoonLakePlayer target);
}
