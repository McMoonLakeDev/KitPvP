package com.minecraft.moonlake.kitpvp.api.occupa.skill.type;

import com.minecraft.moonlake.kitpvp.api.occupa.skill.AbstractSkill;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.EntityManager;
import com.minecraft.moonlake.kitpvp.particle.ParticleEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 * Created by MoonLake on 2016/7/10.
 */
public class Hasaki extends AbstractSkill {

    public Hasaki() {

        super("Hasaki");

        setDisplayName("旋风斩");
        setCoolDown(50);
        setCombo(new SkillComboType[] { SkillComboType.RIGHT, SkillComboType.LEFT, SkillComboType.LEFT });
    }

    /**
     * 将此技能强制释放
     *
     * @param owner 释放者
     */
    @Override
    public void cast(KitPvPPlayer owner) {

        new BukkitRunnable() {

            Location location = owner.getLocation();
            Vector direction = location.getDirection().normalize();
            double t = 0, phi = 0;

            @Override
            public void run() {

                t += 1d;
                double x = direction.getX() * t;
                double y = direction.getY() * t + 1.3d;
                double z = direction.getZ() * t;

                location.add(x, y, z);

                if(t == 1d) {

                    location.getWorld().playSound(location, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10f, 1f);
                    location.getWorld().spawnParticle(Particle.SWEEP_ATTACK, location, 1);
                }
                phi += Math.PI / 2;

                double x2 = 0d, y2 = 0d, z2 = 0d;
                Location location2 = location.clone();

                for(double t2 = 0d; t2 <= 6 * Math.PI; t2 += Math.PI / 8) {

                    for(double i = 0; i <= 1; i++) {

                        x2 = 0.3d * (2 * Math.PI - t2) * 0.58d * Math.cos(t2 + phi + i * Math.PI);
                        y2 = 0.3d * t2 - 4d;
                        z2 = 0.3d * (2 * Math.PI - t2) * 0.58d * Math.sin(t2 + phi + i * Math.PI);

                        location2.add(x2, y2, z2);

                        ParticleEffect.CLOUD.display(location2, 32d, 0f, 0f, 0f, 0f, 1);

                        location2.subtract(x2, y2, z2);
                    }
                }
                for(LivingEntity entity : EntityManager.getEntityInRadius(location, 1.6d, owner)) {

                    EntityManager.realDamage(entity, owner, 10d);
                    entity.setVelocity(new Vector(0d, 0.75d, 0d));
                    entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 5f, 1f);
                }
                location.subtract(x, y, z);

                if(t > 15) {

                    owner.send("hasaki task done.");

                    cancel();
                }
            }
        }.runTaskTimer(getMain().getMain(), 0L, 1L);
    }
}
