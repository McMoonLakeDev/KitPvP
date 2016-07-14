package com.minecraft.moonlake.kitpvp.listeners.player;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import com.minecraft.moonlake.kitpvp.manager.ConfigManager;
import com.minecraft.moonlake.util.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by MoonLake on 2016/7/14.
 */
public class PlayerChatListener implements Listener {

    private final KitPvP main;
    private final String format;

    public PlayerChatListener(KitPvP main) {

        this.main = main;
        this.format = ConfigManager.get("PlayerChat.Format").asString();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {

        event.setFormat(toFromat(AccountManager.get(event.getPlayer().getName()), event.getMessage()));
    }

    private String toFromat(KitPvPPlayer kitPvPPlayer, String message) {

        String string = new String(format)
                .replace("%rank", kitPvPPlayer.getRank().getRankName())
                .replace("%player", kitPvPPlayer.getName())
                .replace("%message", Util.fColor(message));

        return Util.color(string);
    }
}
