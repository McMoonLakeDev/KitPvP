package com.minecraft.moonlake.kitpvp.util.player;

import com.minecraft.moonlake.api.nms.packet.PacketPlayOutChat;
import com.minecraft.moonlake.kitpvp.api.occupa.Occupa;
import com.minecraft.moonlake.kitpvp.api.occupa.OccupaGUI;
import com.minecraft.moonlake.kitpvp.api.occupa.OccupaType;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillCombo;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.api.player.scoreboard.KitPvPScoreboard;
import com.minecraft.moonlake.kitpvp.language.l18n;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import com.minecraft.moonlake.kitpvp.manager.EntityManager;
import com.minecraft.moonlake.kitpvp.rank.KitPvPRank;
import com.minecraft.moonlake.kitpvp.scoreboard.KitPvPScoreboardUtil;
import com.minecraft.moonlake.kitpvp.util.skill.SkillComboUtil;
import com.minecraft.moonlake.util.Util;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by MoonLake on 2016/7/9.
 */
public class PlayerUtil implements KitPvPPlayer {

    private final String name;
    private final Player player;
    private Occupa occupa;
    private OccupaGUI occupaGUI;
    private OccupaType occupaType;
    private SkillCombo skillCombo;
    private KitPvPScoreboard scoreboard;

    private int kill;
    private int death;
    private KitPvPRank rank;

    public PlayerUtil(String name) {

        this.name = name;
        this.player = Bukkit.getServer().getPlayer(name);

        this.occupaGUI = new OccupaGUI(this);
        this.skillCombo = new SkillComboUtil(this, 3);
        this.scoreboard = new KitPvPScoreboardUtil(this);
    }

    /**
     * 获取此玩家的名称
     *
     * @return 名称
     */
    @Override
    public String getName() {

        return name;
    }

    /**
     * 获取此玩家的 Bukkit 玩家对象
     *
     * @return Bukkit 玩家对象
     */
    @Override
    public Player getBukkitPlayer() {

        return player;
    }

    /**
     * 获取此玩家的职业
     *
     * @return 职业对象
     */
    @Override
    public Occupa getOccupa() {

        return occupa;
    }

    /**
     * 获取此玩家的职业 GUI 对象
     *
     * @return 职业 GUI 对象
     */
    @Override
    public OccupaGUI getOccupaGUI() {

        return occupaGUI;
    }

    /**
     * 获取此玩家的职业类型
     *
     * @return 职业类型
     */
    @Override
    public OccupaType getOccupaType() {

        return occupaType;
    }

    /**
     * 设置此玩家的职业
     *
     * @param occupa 职业对象
     */
    @Override
    public void setOccupa(Occupa occupa) {

        this.occupa = occupa;
        this.occupaType = occupa.getType();
    }

    /**
     * 获取此玩家的技能组合对象
     *
     * @return 技能组合对象
     */
    @Override
    public SkillCombo getSkillCombo() {

        return skillCombo;
    }

    /**
     * 获取此玩家的职业战争计分板对象
     *
     * @return 计分板对象
     */
    @Override
    public KitPvPScoreboard getScoreboard() {

        return scoreboard;
    }

    /**
     * 获取此玩家的击杀数
     *
     * @return 击杀数
     */
    @Override
    public int getKill() {

        return kill;
    }

    /**
     * 设置此玩家的击杀数
     *
     * @param newKill 新的击杀数
     */
    @Override
    public void setKill(int newKill) {

        this.kill = newKill;
    }

    /**
     * 获取此玩家的死亡数
     *
     * @return 死亡数
     */
    @Override
    public int getDeath() {

        return death;
    }

    /**
     * 设置此玩家的死亡数
     *
     * @param newDeath 新的死亡数
     */
    @Override
    public void setDeath(int newDeath) {

        this.death = newDeath;
    }

    /**
     * 获取此玩家的 KD 比值
     *
     * @return KD 比值
     */
    @Override
    public double getKD() {

        if(kill > 0 && death > 0) {

            double kd = (double)kill / (double)death;

            return Util.rounding(kd, 2);
        }
        return 0.0d;
    }

    /**
     * 获取此玩家的 Rank 等级
     *
     * @return Rank
     */
    @Override
    public KitPvPRank getRank() {

        return rank;
    }

    /**
     * 设置此玩家的 Rank 等级
     *
     * @param kitPvPRank Rank
     */
    @Override
    public void setRank(KitPvPRank kitPvPRank) {

        this.rank = kitPvPRank;
    }

    /**
     * 给此玩家发送消息
     *
     * @param msg 消息
     */
    @Override
    public void send(String msg) {

        getBukkitPlayer().sendMessage(Util.color(msg));
    }

    /**
     * 给此玩家发送消息
     *
     * @param msg 消息
     */
    @Override
    public void send(String[] msg) {

        getBukkitPlayer().sendMessage(Util.color(msg));
    }

    /**
     * 给此玩家发送语言文件指定 键 的值
     *
     * @param key 键
     */
    @Override
    public void l18n(String key) {

        send(l18n.$(key));
    }

    /**
     * 给此玩家发送语言文件指定 键 的值
     *
     * @param key  键
     * @param args 参数
     */
    @Override
    public void l18n(String key, Object... args) {

        send(l18n.$(key, args));
    }

    /**
     * 获取此玩家的当前血量
     *
     * @return 血量
     */
    @Override
    public double getHealth() {

        return getBukkitPlayer().getHealth();
    }

    /**
     * 获取此玩家的最大生命
     *
     * @return 最大血量
     */
    @Override
    public double getMaxHealth() {

        return getBukkitPlayer().getMaxHealth();
    }

    /**
     * 设置此玩家的最大生命
     *
     * @param maxHealth 最大生命
     */
    @Override
    public void setMaxHealth(double maxHealth) {

        getBukkitPlayer().setMaxHealth(maxHealth);
    }

    /**
     * 设置此玩家的当前生命
     *
     * @param health 当前生命
     */
    @Override
    public void setHealth(double health) {

        getBukkitPlayer().setHealth(health);
    }

    /**
     * 给玩家发送中心聊天数据包
     *
     * @param message 消息
     */
    @Override
    public void sendMainChatPacket(String message) {

        new PacketPlayOutChat(Util.color(message), PacketPlayOutChat.Mode.MAIN).send(getName());
    }

    /**
     * 获取此玩家的当前所在位置
     *
     * @return 位置
     */
    @Override
    public Location getLocation() {

        return getBukkitPlayer().getLocation();
    }

    /**
     * 获取此玩家的物品栏背包
     *
     * @return 物品栏
     */
    @Override
    public PlayerInventory getInventory() {

        return getBukkitPlayer().getInventory();
    }

    /**
     * 获取此玩家的主手中物品
     *
     * @return 主手中物品
     */
    @Override
    public ItemStack getItemInMainHand() {

        return getInventory().getItemInMainHand();
    }

    /**
     * 获取此玩家的副手中物品
     *
     * @return 副手中物品
     */
    @Override
    public ItemStack getItemInOffHand() {

        return getInventory().getItemInOffHand();
    }

    /**
     * 设置此玩家的主手中物品
     *
     * @param item 物品栈
     */
    @Override
    public void setItemInMainHand(ItemStack item) {

        getInventory().setItemInMainHand(item);
    }

    /**
     * 设置此玩家的副手中物品
     *
     * @param item 物品栈
     */
    @Override
    public void setItemInOffHand(ItemStack item) {

        getInventory().setItemInOffHand(item);
    }

    /**
     * 给此玩家打开指定物品栏对象
     *
     * @param inv 物品栏对象
     */
    @Override
    public void openInventory(Inventory inv) {

        getBukkitPlayer().openInventory(inv);
    }

    /**
     * 获取此玩家打开的上面物品栏对象
     *
     * @return 上面物品栏对象
     */
    @Override
    public Inventory getTopInventory() {

        return getBukkitPlayer().getOpenInventory() != null ? getBukkitPlayer().getOpenInventory().getTopInventory() : null;
    }

    @Override
    public boolean equals(Object object) {

        if(object != null) {

            if(object instanceof KitPvPPlayer) {

                KitPvPPlayer obj = (KitPvPPlayer)object;

                return obj.getName().equals(this.getName());
            }
        }
        return false;
    }

    /**
     * 获取此玩家是否之前玩过服务器
     *
     * @return true 之前玩过 else 第一次玩服务器
     */
    @Override
    public boolean hasBeforePlayed() {

        return getBukkitPlayer().hasPlayedBefore();
    }

    /**
     * 获取此玩家是否在地面
     *
     * @return 是否在地面
     */
    @Override
    public boolean isOnGround() {

        return getBukkitPlayer().isOnGround();
    }

    /**
     * 获取此玩家的所在世界对象
     *
     * @return 世界
     */
    @Override
    public World getWorld() {

        return getBukkitPlayer().getWorld();
    }

    /**
     * 更新此玩家的物品栏背包
     */
    @Override
    public void updateInventory() {

        getBukkitPlayer().updateInventory();
    }

    /**
     * 将此玩家正在打开的物品栏关闭
     */
    @Override
    public void closeInventory() {

        getBukkitPlayer().closeInventory();
    }

    /**
     * 获取此玩家的当前位置 X 坐标
     *
     * @return X 坐标
     */
    @Override
    public int getX() {

        return getLocation().getBlockX();
    }

    /**
     * 获取此玩家的当前位置 Y 坐标
     *
     * @return Y 坐标
     */
    @Override
    public int getY() {

        return getLocation().getBlockY();
    }

    /**
     * 获取此玩家的当前位置 Z 坐标
     *
     * @return Z 坐标
     */
    @Override
    public int getZ() {

        return getLocation().getBlockZ();
    }

    /**
     * 获取此玩家的当前位置 X 坐标
     *
     * @return X 坐标
     */
    @Override
    public double getDoubleX() {

        return getLocation().getX();
    }

    /**
     * 获取此玩家的当前位置 Y 坐标
     *
     * @return Y 坐标
     */
    @Override
    public double getDoubleY() {

        return getLocation().getY();
    }

    /**
     * 获取此玩家的当前位置 Z 坐标
     *
     * @return Z 坐标
     */
    @Override
    public double getDoubleZ() {

        return getLocation().getZ();
    }

    /**
     * 将此玩家传送到指定实体身边
     *
     * @param entity 实体
     */
    @Override
    public void teleport(Entity entity) {

        getBukkitPlayer().teleport(entity);
    }

    /**
     * 将此玩家传送到指定位置
     *
     * @param location 位置
     */
    @Override
    public void teleport(Location location) {

        getBukkitPlayer().teleport(location);
    }

    /**
     * 将此玩家传送到玩家当前世界的指定 xyz 坐标
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param z Z 坐标
     */
    @Override
    public void teleport(int x, int y, int z) {

        teleport(new Location(getWorld(), x, y, z));
    }

    /**
     * 将此玩家传送到玩家当前世界的指定 xyz 坐标
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param z Z 坐标
     */
    @Override
    public void teleport(double x, double y, double z) {

        teleport(new Location(getWorld(), x, y, z));
    }

    /**
     * 将此玩家传送到指定世界的指定 xyz 坐标
     *
     * @param world 指定世界
     * @param x     X 坐标
     * @param y     Y 坐标
     * @param z     Z 坐标
     */
    @Override
    public void teleport(World world, int x, int y, int z) {

        teleport(new Location(world, x, y, z));
    }

    /**
     * 将此玩家传送到指定世界的指定 xyz 坐标
     *
     * @param world 指定世界
     * @param x     X 坐标
     * @param y     Y 坐标
     * @param z     Z 坐标
     */
    @Override
    public void teleport(World world, double x, double y, double z) {

        teleport(new Location(world, x, y, z));
    }

    /**
     * 给此玩家在当前位置播放音效
     *
     * @param sound  音效
     * @param volume 音量
     * @param pitch  音调
     */
    @Override
    public void playSound(Sound sound, float volume, float pitch) {

        getBukkitPlayer().playSound(getLocation(), sound, volume, pitch);
    }

    /**
     * 设置此玩家的无敌时间 (Tick)
     *
     * @param ticks 时间
     */
    @Override
    public void setNoDamageTicks(int ticks) {

        getBukkitPlayer().setNoDamageTicks(ticks);
    }

    /**
     * 设置此玩家的矢量
     *
     * @param vector 矢量
     */
    @Override
    public void setVector(Vector vector) {

        getBukkitPlayer().setVelocity(vector);
    }

    @Override
    public void resetHealth() {

        getBukkitPlayer().resetMaxHealth();
    }

    @Override
    public void clearPotionEffect() {

        EntityManager.clearPotionEffect(this);
    }

    /**
     * 获取此玩家是否拥有指定权限
     *
     * @param permission 权限
     * @return true 拥有此权限 else 没有
     */
    @Override
    public boolean hasPermission(String permission) {

        return getBukkitPlayer().hasPermission(permission);
    }

    /**
     * 获取此玩家的最后受伤原因
     *
     * @return 受伤原因
     */
    @Override
    public EntityDamageEvent getLastDamageCause() {

        return getBukkitPlayer().getLastDamageCause();
    }

    /**
     * 获取此玩家的击杀者玩家对象
     *
     * @return 击杀者玩家对象 没有则返回 null
     */
    @Override
    public KitPvPPlayer getKiller() {

        return getBukkitPlayer().getKiller() != null ? AccountManager.get(getBukkitPlayer().getKiller().getName()) : null;
    }

    /**
     * 获取此玩家的眼部位置
     *
     * @return 眼部位置
     */
    @Override
    public Location getEyeLocation() {

        return getBukkitPlayer().getEyeLocation();
    }

    /**
     * 获取此玩家准星的目标方块
     *
     * @param distance 距离
     * @return 准星的方块 没有则返回 null
     */
    public Block getTargetBlock(int distance) {

        return getBukkitPlayer().getTargetBlock(new HashSet<Material>(Arrays.asList(Material.AIR)), distance);
    }
}
