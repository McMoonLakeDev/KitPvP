package com.minecraft.moonlake.kitpvp.questbuy;

import com.minecraft.moonlake.api.itemlib.ItemBuilder;
import com.minecraft.moonlake.kitpvp.KitPvPPlugin;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.GUIManager;
import com.minecraft.moonlake.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by MoonLake on 2016/7/12.
 */
public abstract class QuestBuy implements Listener {

    private final KitPvPPlayer kitPvPPlayer;
    private final Inventory gui;
    private final ItemStack item;

    private boolean done;
    private int runTime = 15;
    private BukkitRunnable runnable;

    public QuestBuy(KitPvPPlayer kitPvPPlayer, ItemStack item, String title) {

        this.item = item;
        this.kitPvPPlayer = kitPvPPlayer;
        this.gui = Bukkit.getServer().createInventory(kitPvPPlayer, 6 * 9, Util.color(title));
        this.init();

        KitPvPPlugin.getInstances().getPluginManager().registerEvents(this, KitPvPPlugin.getInstances().getMain());
    }

    private void init() {

        this.gui.setItem(GUIManager.getSlot(5, 2), this.item);
        this.gui.setItem(GUIManager.getSlot(5, 5), new ItemBuilder(Material.WATCH, 0, "&b确认倒计时").setAmount(runTime).build());

        GUIManager.setConfirmAndCancelToGUI(gui);
    }

    /**
     * 将请求购买物品栏 GUI 打开给玩家
     */
    public void openQuestBuy() {

        kitPvPPlayer.closeInventory();
        kitPvPPlayer.openInventory(gui);
        kitPvPPlayer.playSound(Sound.ENTITY_ARROW_HIT_PLAYER, 10f, 1f);

        runnable = new BukkitRunnable() {

            int runTime = 15;

            @Override
            public void run() {

                if(done) {

                    kitPvPPlayer.closeInventory();
                    close();
                    cancel();
                }
                if(runTime <= 0) {

                    kitPvPPlayer.closeInventory();
                    kitPvPPlayer.l18n("player.questBuy.overtime");
                    close();
                    cancel();
                }
                else {

                    ItemStack overtime = gui.getItem(GUIManager.getSlot(5, 5));
                    overtime.setAmount(runTime);
                    gui.setItem(GUIManager.getSlot(5, 5), overtime);
                }
                runTime--;
            }
        };
        runnable.runTaskTimerAsynchronously(KitPvPPlugin.getInstances().getMain(), 0L, 20L);
    }

    /**
     * 确认购买
     */
    public abstract void confirm();

    /**
     * 取消购买
     */
    public abstract void cancel();

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if(event.getClickedInventory() == null) return;
        if(!event.getClickedInventory().getTitle().equalsIgnoreCase(gui.getTitle())) return;

        ItemStack item = event.getCurrentItem();
        if(item == null || item.getType() == Material.AIR) return;

        if(done) {

            event.setCancelled(true);
            kitPvPPlayer.updateInventory();
            return;
        }
        if(item.getType() != Material.WOOL) {

            event.setCancelled(true);
            kitPvPPlayer.updateInventory();
        }
        else if(item.getItemMeta().getDisplayName().equalsIgnoreCase(Util.color("&a确认"))) {

            done = true;
            confirm();
            event.setCancelled(true);
            kitPvPPlayer.updateInventory();
        }
        else if(item.getItemMeta().getDisplayName().equalsIgnoreCase(Util.color("&c取消"))) {

            done = true;
            cancel();
            event.setCancelled(true);
            kitPvPPlayer.updateInventory();
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {

        if(event.getInventory() == null) return;
        if(!event.getInventory().getTitle().equalsIgnoreCase(gui.getTitle())) return;

        if(runnable != null) {

            runnable.cancel();
        }
        close();
    }

    /**
     * 关闭类实现把并释放事件
     */
    public void close() {

        HandlerList.unregisterAll(this);
    }
}
