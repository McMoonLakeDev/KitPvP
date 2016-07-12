package com.minecraft.moonlake.kitpvp.listeners.player;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.occupa.type.Warrior;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import com.minecraft.moonlake.kitpvp.manager.DataManager;
import com.minecraft.moonlake.kitpvp.manager.OccupaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by MoonLake on 2016/7/9.
 */
public class PlayerBaseListener implements Listener {

    public final KitPvP main;

    public PlayerBaseListener(KitPvP main) {

        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if(!AccountManager.has(player.getName())) {

            KitPvPPlayer kitPvPPlayer = AccountManager.create(player.getName());
            kitPvPPlayer.getScoreboard().register();
            kitPvPPlayer.getOccupaGUI().loadKitPvPOccupaData();

            OccupaManager.initOccupaPlayer(kitPvPPlayer, new Warrior());
        }
        if(DataManager.isSetLobbyPoint()) {

            event.getPlayer().teleport(DataManager.getLobbyPoint());
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event) {

        KitPvPPlayer kitPvPPlayer = AccountManager.get(event.getPlayer().getName());

        if(kitPvPPlayer != null) {

            kitPvPPlayer.getScoreboard().unregister();
        }
        AccountManager.remove(event.getPlayer().getName());
    }
}
