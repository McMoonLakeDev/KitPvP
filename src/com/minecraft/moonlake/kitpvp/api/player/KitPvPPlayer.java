package com.minecraft.moonlake.kitpvp.api.player;

import com.minecraft.moonlake.kitpvp.api.occupa.Occupa;
import com.minecraft.moonlake.kitpvp.api.occupa.OccupaType;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillCombo;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

/**
 * Created by MoonLake on 2016/7/9.
 */
public interface KitPvPPlayer {

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
     * 设置此玩家的最大生命
     *
     * @param maxHealth 最大生命
     */
    void setMaxHealth(double maxHealth);

    /**
     * 设置此玩家的当前生命
     *
     * @param health 当前生命
     */
    void setHealth(double health);

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
     * @param x X 坐标
     * @param y Y 坐标
     * @param z Z 坐标
     */
    void teleport(World world, int x, int y, int z);

    /**
     * 将此玩家传送到指定世界的指定 xyz 坐标
     *
     * @param world 指定世界
     * @param x X 坐标
     * @param y Y 坐标
     * @param z Z 坐标
     */
    void teleport(World world, double x, double y, double z);

    /**
     * 给玩家发送中心聊天数据包
     *
     * @param message 消息
     */
    void sendMainChatPacket(String message);

    /**
     * 给此玩家在当前位置播放音效
     *
     * @param sound 音效
     * @param volume 音量
     * @param pitch 音调
     */
    void playSound(Sound sound, float volume, float pitch);

    /**
     * 设置此玩家的无敌时间 (Tick)
     *
     * @param ticks 时间
     */
    void setNoDamageTicks(int ticks);

    /**
     * 设置此玩家的矢量
     *
     * @param vector 矢量
     */
    void setVector(Vector vector);
}
