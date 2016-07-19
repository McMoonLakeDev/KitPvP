package com.minecraft.moonlake.kitpvp.listeners.occupa;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.occupa.OccupaType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import com.minecraft.moonlake.kitpvp.manager.DataManager;
import com.minecraft.moonlake.manager.EntityManager;
import com.minecraft.moonlake.manager.RandomManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
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
        else if(source != null && damager != null && damager instanceof Snowball) {

            if(damager.hasMetadata("GUNNERAMMO") && ((Snowball)damager).getShooter() instanceof Player) {

                KitPvPPlayer kitPvPPlayer = AccountManager.get(((Player)((Snowball)damager).getShooter()).getName());
                EntityManager.realDamage((LivingEntity)source, kitPvPPlayer, 1d);
            }
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

            Arrow arrow = (Arrow) event.getProjectile();

            if(kitPvPPlayer.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {

                arrow.setFireTicks(120);
                arrow.setShooter(kitPvPPlayer.getBukkitPlayer());
                arrow.spigot().setDamage(arrow.spigot().getDamage() - 2d);
            }
            else {

                int result = (int) RandomManager.getRandom().nextDouble() * 100;

                if(result >= 70) {

                    arrow.spigot().setDamage(arrow.spigot().getDamage() - 2d * 2);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent event) {

        KitPvPPlayer kitPvPPlayer = AccountManager.get(event.getPlayer().getName());

        if(event.getItem() == null) return;
        if(event.getItem().getType() == Material.AIR) return;
        if(kitPvPPlayer == null) return;
        if(kitPvPPlayer.getOccupa() == null) return;
        if(kitPvPPlayer.getOccupaType() != OccupaType.GUNNER) return;
        if(kitPvPPlayer.getGameMode() == GameMode.CREATIVE) return;
        if(kitPvPPlayer.getGameMode() == GameMode.SPECTATOR) return;
        if(kitPvPPlayer.getOccupa().getWeaponType() != event.getItem().getType()) return;
        if(DataManager.contains(kitPvPPlayer.getLocation())) return;

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            Snowball ammo = kitPvPPlayer.launcherProjectile(Snowball.class);
            ammo.setBounce(false);
            ammo.setShooter(kitPvPPlayer.getBukkitPlayer());
            ammo.setMetadata("GUNNERAMMO", new FixedMetadataValue(main.getMain(), "1"));
            ammo.getWorld().playSound(ammo.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 10f, 1f);
        }
    }
}
