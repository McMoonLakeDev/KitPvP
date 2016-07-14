package com.minecraft.moonlake.kitpvp.api.occupa.skill.type;

import com.minecraft.moonlake.kitpvp.api.event.entity.EntityDamageBySkillEvent;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.AbstractSkill;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.EntityManager;
import com.minecraft.moonlake.kitpvp.particle.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MoonLake on 2016/7/10.
 */
public class GhostFlashBurst extends AbstractSkill {

    public GhostFlashBurst() {

        super("GhostFlashBurst");

        setDisplayName("鬼影闪 · 爆");
        setCoolDown(45);
        setCombo(new SkillComboType[] { SkillComboType.RIGHT, SkillComboType.RIGHT, SkillComboType.LEFT });
    }

    @Override
    public void cast(KitPvPPlayer owner) {

        Vector vector = owner.getLocation().getDirection().multiply(3d).setY(0.4d);
        owner.setVector(vector);

        new BukkitRunnable() {

            int time = 0;
            int living = 0;
            boolean done = false;
            BukkitRunnable burst = null;
            Location location = owner.getLocation();
            Vector direction = location.getDirection().normalize();
            List<LivingEntity> flashEntityList = new ArrayList<>();
            double t = 0d;

            @Override
            public void run() {

                if(living >= 5 * 20) {

                    done = true;
                }
                if(done) {

                    cancel();
                }
                if(burst == null) {

                    t += 1d;
                    double x = direction.getX() * t;
                    double z = direction.getZ() * t;

                    location.add(x, 0d, z);

                    for(LivingEntity entity : EntityManager.getEntityInRadius(location, 1.4d, owner)) {

                        EntityDamageBySkillEvent edbse = new EntityDamageBySkillEvent(entity, GhostFlashBurst.this, owner);
                        Bukkit.getServer().getPluginManager().callEvent(edbse);

                        if(!edbse.isCancelled() && !flashEntityList.contains(entity)) {

                            entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 5f, 1f);
                            entity.getWorld().spawnParticle(Particle.SWEEP_ATTACK, entity.getLocation(), 1);

                            flashEntityList.add(entity);
                        }
                    }
                    location.subtract(x, 0d, z);

                    ParticleEffect.FIREWORKS_SPARK.display(owner.getLocation(), 32f, 0f, 0f, 0f, 0f, 1);
                }
                if(owner.isOnGround() && time >= 30) {

                    if(flashEntityList.isEmpty()) {

                        done = true;
                        return;
                    }
                    if(burst == null) {

                        burst = new BukkitRunnable() {

                            @Override
                            public void run() {

                                for(LivingEntity entity : flashEntityList) {

                                    EntityManager.realDamage(entity, owner, 4d);
                                    entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1f, 1f);
                                    entity.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, entity.getLocation(), 1);
                                }
                                done = true;
                            }
                        };
                        burst.runTaskLater(getMain().getMain(), 20L);
                    }
                }
                time++;
                living++;
            }
        }.runTaskTimer(getMain().getMain(), 0L, 1L);
    }
}
