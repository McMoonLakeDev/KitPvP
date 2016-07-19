package com.minecraft.moonlake.kitpvp.api.occupa.skill.type;

import com.minecraft.moonlake.kitpvp.api.event.entity.EntityDamageBySkillEvent;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.AbstractSkill;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.DataManager;
import com.minecraft.moonlake.manager.EntityManager;
import com.minecraft.moonlake.manager.VectorManager;
import com.minecraft.moonlake.particle.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by MoonLake on 2016/7/13.
 */
public class RKO extends AbstractSkill {

    public RKO() {

        super("RKO");

        setDisplayName("RKO!");
        setCoolDown(45);
        setCombo(new SkillComboType[] { SkillComboType.RIGHT, SkillComboType.RIGHT, SkillComboType.LEFT });
    }

    @Override
    public void cast(KitPvPPlayer owner) {

        Location location = owner.getEyeLocation().clone().add(0d, 1d, 0d);

        new BukkitRunnable() {

            int living = 0;
            boolean state = false;
            BufferedImage RKO_IMAGE = DataManager.getRKOImage();

            @Override
            public void run() {

                if(living >= 3 * 20) {

                    cancel();
                }
                for(int y = 0; y < RKO_IMAGE.getHeight(); y += 1) {

                    for(int x = 0; x < RKO_IMAGE.getWidth(); x += 1) {

                        if(RKO_IMAGE.getRGB(x, y) == Color.black.getRGB()) {

                            Vector vector = new Vector(RKO_IMAGE.getWidth() / 2f - x, RKO_IMAGE.getHeight() / 2f - y, 0f).multiply(0.2f);
                            VectorManager.rotateAroundAxisY(vector, -location.getYaw() * 0.017453292f);
                            ParticleEffect.FLAME.display(location.add(vector), 32f, 0f, 0f, 0f, 0f, 1);
                            location.subtract(vector);
                        }
                    }
                }
                if(!state) {

                    state = true;

                    owner.getWorld().playSound(owner.getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 5f, 1f);

                    for(LivingEntity entity : EntityManager.getEntityInRadius(owner.getLocation(), 5d, owner)) {

                        EntityDamageBySkillEvent edbse = new EntityDamageBySkillEvent(entity, RKO.this, owner);
                        Bukkit.getServer().getPluginManager().callEvent(edbse);

                        if(!edbse.isCancelled()) {

                            EntityManager.realDamage(entity, owner, 5d);
                            entity.setVelocity(new Vector(0d, 1d, 0d));
                        }
                    }
                }
                living++;
            }
        }.runTaskTimer(getMain().getMain(), 10L, 1L);
    }
}
