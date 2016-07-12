package com.minecraft.moonlake.kitpvp.manager;

import com.minecraft.moonlake.api.itemlib.ItemBuilder;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by MoonLake on 2016/7/11.
 */
public final class GUIManager extends KitPvPManager {

    private final static String OCCUPA_GUI_TITLE = "【职业面板 v2.0】: ";

    private final static int[][] CONFIRM = {

                    {1, 4}, {2, 4}, {3, 4},
                    {1, 5}, {2, 5}, {3, 5},
                    {1, 6}, {2, 6}, {3, 6}
    };
    private final static int[][] CANCEL = {
                    {7, 4}, {8, 4}, {9, 4},
                    {7, 5}, {8, 5}, {9, 5},
                    {7, 6}, {8, 6}, {9, 6}
    };

    /**
     * 将二维坐标转换到 GUI 所需的索引值
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @return 索引值
     */
    public static int getSlot(int x, int y) {

        return (y * 9) - (9 - x) - 1;
    }

    /**
     * 格式化职业战争玩家的 GUI 标题
     *
     * @param kitPvPPlayer 玩家
     * @return 职业战争玩家的 GUI 标题
     */
    public static String formatOccupaGUITitle(KitPvPPlayer kitPvPPlayer) {

        return OCCUPA_GUI_TITLE + kitPvPPlayer.getName();
    }

    /**
     * 获取指定物品栏 GUI 是否为职业战争 GUI
     *
     * @param gui 物品栏 GUI
     * @return true 则是职业战争 GUI else 不是
     */
    public static boolean isOccupaGUI(Inventory gui) {

        return gui != null && gui.getHolder() instanceof KitPvPPlayer && gui.getTitle().contains(OCCUPA_GUI_TITLE);
    }

    /**
     * 将指定物品栏 GUI 对象添加确认和取消物品栈
     *
     * @param gui 物品栏 GUI
     */
    public static void setConfirmAndCancelToGUI(Inventory gui) {

        ItemStack confirm = new ItemBuilder(Material.WOOL, 5, "&a确认").build();
        ItemStack cancel = new ItemBuilder(Material.WOOL, 14, "&c取消").build();

        for(int i = 0; i < CONFIRM.length; i++) {

            gui.setItem(getSlot(CONFIRM[i][0], CONFIRM[i][1]), confirm);
        }
        for(int i = 0; i < CANCEL.length; i++) {

            gui.setItem(getSlot(CANCEL[i][0], CANCEL[i][1]), cancel);
        }
    }
}
