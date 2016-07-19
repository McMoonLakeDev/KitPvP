package com.minecraft.moonlake.kitpvp.api.occupa.skill.type;

import com.minecraft.moonlake.api.itemlib.ItemBuilder;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.AbstractSkill;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.manager.EntityManager;
import com.minecraft.moonlake.manager.RandomManager;
import com.minecraft.moonlake.particle.ParticleEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by MoonLake on 2016/7/19.
 */
public class Molotov extends AbstractSkill {

    public Molotov() {

        super("Molotov");

        setDisplayName("燃烧弹");
        setCoolDown(50);
        setCombo(new SkillComboType[] { SkillComboType.LEFT, SkillComboType.LEFT, SkillComboType.RIGHT });
    }

    @Override
    public void cast(KitPvPPlayer owner) {

        final Item object = owner.getWorld().dropItem(owner.getEyeLocation(), new ItemBuilder(Material.INK_SACK, 1, RandomManager.getRandomUUID().toString()).build());
        object.setVelocity(owner.getDirection().clone().multiply(0.8d).setY(0.25d));
        object.setCustomName(getDisplayName());
        object.setCustomNameVisible(true);

        new BukkitRunnable() {

            int living = 0;
            boolean done = false;
            boolean state = false;
            BukkitRunnable molotov = null;

            @Override
            public void run() {

                if(living > 8 * 20) {

                    done = true;
                }
                if(done) {

                    if(object != null) {

                        object.remove();
                    }
                    if(molotov != null) {

                        molotov.cancel();
                    }
                    cancel();
                }
                if(!state && object.isOnGround()) {

                    state = true;

                    if(molotov == null) {

                        molotov = new BukkitRunnable() {

                            Location location = object.getLocation();

                            @Override
                            public void run() {

                                ParticleEffect.FLAME.display(location, 32d, 2.5f, 0.5f, 2.5f, 0f, 50);

                                for(LivingEntity entity : EntityManager.getEntityInRadius(location, 5d)) {

                                    entity.setFireTicks(20);
                                }
                            }
                        };
                        molotov.runTaskTimer(getMain().getMain(), 10L, 1L);

                        if(object != null) {

                            object.remove();
                        }
                    }
                }
                living++;
            }
        }.runTaskTimer(getMain().getMain(), 20L, 1L);
    }
}
