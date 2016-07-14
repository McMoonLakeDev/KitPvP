package com.minecraft.moonlake.kitpvp.listeners.occupa;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.occupa.OccupaType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by MoonLake on 2016/7/13.
 */
public class OccupaDamageListener implements Listener {

    private final KitPvP main;

    public OccupaDamageListener(KitPvP main) {

        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMagician(EntityDamageByEntityEvent event) {

        Entity source = event.getEntity();
        Entity damager = event.getDamager();

        if(source != null && damager != null && damager instanceof Player) {

            KitPvPPlayer kitPvPPlayer = AccountManager.get(damager.getName());

            if(kitPvPPlayer == null) return;
            if(kitPvPPlayer.getOccupa() == null) return;
            if(kitPvPPlayer.getOccupaType() != OccupaType.MAGICIAN) return;
            if(kitPvPPlayer.getItemInMainHand() == null) return;
            if(kitPvPPlayer.getItemInMainHand().getType() != OccupaType.MAGICIAN.getWeapon()) return;

            source.setFireTicks(30);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRanger(EntityShootBowEvent event) {

        Entity source = event.getEntity();

        if(source instanceof Player && event.getProjectile() instanceof Arrow) {

            KitPvPPlayer kitPvPPlayer = AccountManager.get(source.getName());

            if(kitPvPPlayer == null) return;
            if(kitPvPPlayer.getOccupa() == null) return;
            if(kitPvPPlayer.getOccupaType() != OccupaType.RANGER) return;
            if(!kitPvPPlayer.getBukkitPlayer().hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) return;

            Arrow arrow = (Arrow) event.getProjectile();

            arrow.setFireTicks(120);
            arrow.setShooter(kitPvPPlayer.getBukkitPlayer());
            arrow.spigot().setDamage(arrow.spigot().getDamage() - 2d);
        }
    }
}
