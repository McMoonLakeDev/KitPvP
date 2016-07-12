package com.minecraft.moonlake.kitpvp.listeners.gui;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import com.minecraft.moonlake.kitpvp.manager.GUIManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

/**
 * Created by MoonLake on 2016/7/11.
 */
public class OccupaGUIListener implements Listener {

    private final KitPvP main;

    public OccupaGUIListener(KitPvP main) {

        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent event) {

        if(event.getClickedInventory() == null) return;
        if(!GUIManager.isOccupaGUI(event.getClickedInventory())) return;
        if(event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        if(!(event.getWhoClicked() instanceof Player)) return;

        KitPvPPlayer kitPvPPlayer = AccountManager.get(event.getWhoClicked().getName());
        if(kitPvPPlayer == null) return;

        kitPvPPlayer.getOccupaGUI().onOccupaGUIClick(event);

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(InventoryClickEvent event) {

        if(event.getClickedInventory() == null) return;
        if(event.getClickedInventory().getType() != InventoryType.PLAYER) return;

        if(!(event.getWhoClicked() instanceof Player)) return;

        KitPvPPlayer kitPvPPlayer = AccountManager.get(event.getWhoClicked().getName());
        if(kitPvPPlayer == null) return;
        if(kitPvPPlayer.getTopInventory() == null) return;

        boolean result = GUIManager.isOccupaGUI(event.getClickedInventory());

        if(result) {

            event.setCancelled(true);
            kitPvPPlayer.updateInventory();
        }
    }
}
