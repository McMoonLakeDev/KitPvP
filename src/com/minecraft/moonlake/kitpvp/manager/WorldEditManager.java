package com.minecraft.moonlake.kitpvp.manager;

import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.sk89q.worldedit.regions.CuboidRegion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by MoonLake on 2016/7/9.
 */
public final class WorldEditManager extends KitPvPManager {

    /**
     * 获取创世神玩家选择的区域对象
     *
     * @param player 玩家
     * @return 区域对象 没有则返回 null
     */
    public static Selection getSelection(KitPvPPlayer player) {

        return player != null ? getMain().getWorldEdit().getSelection(player.getBukkitPlayer()) : null;
    }

    /**
     * 获取创世神玩家选择的区域对象
     *
     * @param player 玩家
     * @return 区域对象 没有则返回 null
     */
    public static Selection getSelection(Player player) {

        return getMain().getWorldEdit().getSelection(player);
    }

    /**
     * 创建创世神 WorldEdit 的矩形区域对象
     *
     * @param worldName 世界名
     * @param pos1 位置点1
     * @param pos2 位置点2
     * @return 矩形区域对象
     */
    public static com.sk89q.worldedit.regions.CuboidRegion newCuboidRegion(String worldName, String pos1, String pos2) {

        com.sk89q.worldedit.world.World world = !worldName.equals("none") ? new BukkitWorld(Bukkit.getServer().getWorld(worldName)) : null;
        com.sk89q.worldedit.Vector vector1 = VectorManager.fromXYZ(pos1);
        com.sk89q.worldedit.Vector vector2 = VectorManager.fromXYZ(pos2);

        return world != null ? new CuboidRegion(world, vector1, vector2) : new CuboidRegion(vector1, vector2);
    }
}
