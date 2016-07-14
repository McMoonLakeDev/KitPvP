package com.minecraft.moonlake.kitpvp.listeners.player;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.language.l18n;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import com.minecraft.moonlake.util.Util;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by MoonLake on 2016/7/13.
 */
public class PlayerSignListener implements Listener {

    private final KitPvP main;

    public PlayerSignListener(KitPvP main) {

        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSign(SignChangeEvent event) {

        if(!(event.getBlock().getState() instanceof Sign)) return;

        Player player = event.getPlayer();

        if(!player.hasPermission("moonlake.kitpvp.sign.create")) {

            player.sendMessage(l18n.$("player.sign.create.permission.notHave"));
            return;
        }
        if(event.getLine(0).equalsIgnoreCase("kitpvp")) {

            event.setLine(0, Util.color("[&cKitPvP&0]"));
            event.setLine(1, Util.color("&a职业战争 v2.0"));
            event.setLine(2, Util.color("&a(╯‵□′)╯︵┻━┻"));
            event.setLine(3, Util.color("&0(Right Click)"));
            player.sendMessage(l18n.$("player.sign.create.occupa"));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSignInteract(PlayerInteractEvent event) {

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            Block block = event.getClickedBlock();
            if(block == null || !(block.getState() instanceof Sign)) return;
            Player player = event.getPlayer();

            if(!player.hasPermission("moonlake.kitpvp.sign.use")) {

                player.sendMessage(l18n.$("player.sign.use.permission.notHave"));
                return;
            }
            Sign sign = (Sign)block.getState();

            if(sign.getLine(0).equalsIgnoreCase(Util.color("[&cKitPvP&0]"))) {

                KitPvPPlayer kitPvPPlayer = AccountManager.get(player.getName());

                kitPvPPlayer.getOccupaGUI().openGUI();
            }
        }
    }
}
