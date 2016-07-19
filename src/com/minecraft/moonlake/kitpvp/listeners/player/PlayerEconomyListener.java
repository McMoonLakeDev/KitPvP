package com.minecraft.moonlake.kitpvp.listeners.player;

import com.minecraft.moonlake.economy.api.event.PlayerMoneyChangeEvent;
import com.minecraft.moonlake.economy.api.event.PlayerPointChangeEvent;
import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by MoonLake on 2016/7/17.
 */
public class PlayerEconomyListener implements Listener {

    private final KitPvP main;

    public PlayerEconomyListener(KitPvP main) {

        this.main = main;
    }

    @EventHandler
    public void onMoney(PlayerMoneyChangeEvent event) {

        if(event.getPlayer() != null) {

            AccountManager.get(event.getPlayer().getName()).getKitPvPBossbar().update();
        }
    }

    @EventHandler
    public void onPoint(PlayerPointChangeEvent event) {

        if(event.getPlayer() != null) {

            AccountManager.get(event.getPlayer().getName()).getKitPvPBossbar().update();
        }
    }
}
