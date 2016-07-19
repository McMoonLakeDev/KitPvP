package com.minecraft.moonlake.kitpvp.api.occupa.skill.type;

import com.minecraft.moonlake.kitpvp.api.event.entity.EntityDamageBySkillEvent;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.AbstractSkill;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.manager.EntityManager;
import com.minecraft.moonlake.particle.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 * Created by MoonLake on 2016/7/12.
 */
public class FlameDragonBreath extends AbstractSkill {

    public FlameDragonBreath() {

        super("FlameDragonBreath");

        setDisplayName("炎龙之息");
        setCoolDown(40);
        setCombo(new SkillComboType[] { SkillComboType.RIGHT, SkillComboType.RIGHT, SkillComboType.RIGHT });
    }

    /**
     * 将此技能强制释放
     *
     * @param owner 释放者
     */
    @Override
    public void cast(KitPvPPlayer owner) {

        owner.getWorld().playSound(owner.getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 1f, 1f);

        new BukkitRunnable() {

            int living = 0;
            double t = 0;

            @Override
            public void run() {

                if(living >= 3 * 20) {

                    cancel();
                }
                t += 1d;
                Vector direction = owner.getLocation().getDirection().normalize();
                double x = direction.getX() * t;
                double y = direction.getY() * t + 1.5d;
                double z = direction.getZ() * t;

                Location location = owner.getLocation().clone().add(x, y, z);

                ParticleEffect.FLAME.display(location, 32d, 0.8f, 0.5f, 0.8f, 0.1f, 50);

                for(LivingEntity entity : EntityManager.getEntityInRadius(location, 1d, owner)) {

                    EntityDamageBySkillEvent edbse = new EntityDamageBySkillEvent(entity, FlameDragonBreath.this, owner);
                    Bukkit.getServer().getPluginManager().callEvent(edbse);

                    if(!edbse.isCancelled()) {

                        entity.setFireTicks(10);
                        entity.damage(1d, owner.getBukkitPlayer());
                    }
                }
                location.subtract(x, y, z);

                if(t >= 6d) {

                    t = 0d;
                }
                living++;
            }
        }.runTaskTimer(getMain().getMain(), 10L, 1L);
    }
}
