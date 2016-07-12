package com.minecraft.moonlake.kitpvp.manager;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.world.World;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Created by MoonLake on 2016/7/11.
 */
public final class DataManager extends KitPvPManager {

    private static Location lobbyPoint;
    private static CuboidRegion lobbyRegion;

    /**
     * 加载并读取职业战争大厅区域数据
     */
    public static void loadKitPvPLobbyData() {

        File lobby = new File(getMain().getDataFolder(), "lobby.yml");
        if(!lobby.exists()) return;

        YamlConfiguration config = YamlConfiguration.loadConfiguration(lobby);

        if(config.contains("KitPvP.World")) {

            lobbyRegion = WorldEditManager.newCuboidRegion(config.getString("KitPvP.World.Name"), config.getString("KitPvP.World.Pos1"), config.getString("KitPvP.World.Pos2"));
        }
        if(config.contains("KitPvP.Lobby")) {

            lobbyPoint = LocationManager.fromData(config.getString("KitPvP.Lobby"));
        }
    }

    /**
     * 设置职业战争的大厅区域
     *
     * @param selection 区域
     */
    public static void setKitPvPLobbyRegion(Selection selection) {

        if(selection != null) {

            World world = new BukkitWorld(selection.getWorld());
            lobbyRegion = new CuboidRegion(world, selection.getNativeMinimumPoint(), selection.getNativeMaximumPoint());

            File lobby = new File(getMain().getDataFolder(), "lobby.yml");
            if(!lobby.exists()) {

                try {

                    lobby.createNewFile();
                }
                catch (Exception e) {

                    getMain().log("创建职业战争大厅区域数据文件时异常: " + e.getMessage());

                    if(getMain().isDebug()) {

                        e.printStackTrace();
                    }
                }
            }
            YamlConfiguration config = YamlConfiguration.loadConfiguration(lobby);
            config.set("KitPvP.World.Name", world.getName());
            config.set("KitPvP.World.Pos1", VectorManager.toXYZ(selection.getNativeMinimumPoint(), true));
            config.set("KitPvP.World.Pos2", VectorManager.toXYZ(selection.getNativeMaximumPoint(), true));

            try {

                config.save(lobby);
            }
            catch (Exception e) {

                getMain().log("保存职业战争大厅区域数据文件时异常: " + e.getMessage());

                if(getMain().isDebug()) {

                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 设置职业战争大厅传送点
     *
     * @param location 位置
     */
    public static void setKitPvPLobbyPoint(Location location) {

        if(location != null) {

            lobbyPoint = location;

            File lobby = new File(getMain().getDataFolder(), "lobby.yml");
            if(!lobby.exists()) {

                try {

                    lobby.createNewFile();
                }
                catch (Exception e) {

                    getMain().log("创建职业战争大厅区域数据文件时异常: " + e.getMessage());

                    if(getMain().isDebug()) {

                        e.printStackTrace();
                    }
                }
            }
            YamlConfiguration config = YamlConfiguration.loadConfiguration(lobby);
            config.set("KitPvP.Lobby", LocationManager.toDataBit(location, 3, true));

            try {

                config.save(lobby);
            }
            catch (Exception e) {

                getMain().log("保存职业战争大厅区域数据文件时异常: " + e.getMessage());

                if(getMain().isDebug()) {

                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取是否已经设置好职业战争大厅
     *
     * @return true 则设置好 else 没有
     */
    public static boolean isSetLobby() {

        return lobbyRegion != null;
    }

    /**
     * 获取是否已经设置好职业战争大厅传送点
     *
     * @return true 则设置好 else 没有
     */
    public static boolean isSetLobbyPoint() {

        return lobbyPoint != null;
    }

    /**
     * 获取职业战争大厅是否包含指定位置
     *
     * @param location 位置
     * @return true 则包含 else 没有
     */
    public static boolean contains(Location location) {

        return isSetLobby() && lobbyRegion.contains(VectorManager.fromXYZ(location.getX(), location.getY(), location.getZ()));
    }

    /**
     * 获取职业战争大厅传送点位置对象
     *
     * @return 传送点位置对象 没有则返回 null
     */
    public static Location getLobbyPoint() {

        return lobbyPoint;
    }
}
