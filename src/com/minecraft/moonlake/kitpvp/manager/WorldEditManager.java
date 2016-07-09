package com.minecraft.moonlake.kitpvp.manager;

import com.sk89q.worldedit.bukkit.selections.Selection;
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
    public static Selection getSelection(Player player) {

        return getMain().getWorldEdit().getSelection(player);
    }
}
