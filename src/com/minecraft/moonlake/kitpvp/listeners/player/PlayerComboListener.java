package com.minecraft.moonlake.kitpvp.listeners.player;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by MoonLake on 2016/7/9.
 */
public class PlayerComboListener implements Listener {

    private final KitPvP main;

    public PlayerComboListener(KitPvP main) {

        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent event) {

        KitPvPPlayer kitPvPPlayer = AccountManager.get(event.getPlayer().getName());

        if(kitPvPPlayer == null) return;
        if(kitPvPPlayer.getOccupa() == null) return;

        event.setCancelled(true);

        if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {

            kitPvPPlayer.getSkillCombo().applyClick(SkillComboType.LEFT);
        }
        else if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            kitPvPPlayer.getSkillCombo().applyClick(SkillComboType.RIGHT);
        }
    }
}
