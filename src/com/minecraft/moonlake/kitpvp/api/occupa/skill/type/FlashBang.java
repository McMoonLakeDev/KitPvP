package com.minecraft.moonlake.kitpvp.api.occupa.skill.type;

import com.minecraft.moonlake.api.itemlib.ItemBuilder;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.AbstractSkill;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.manager.EntityManager;
import com.minecraft.moonlake.manager.RandomManager;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by MoonLake on 2016/7/13.
 */
public class FlashBang extends AbstractSkill {

    public FlashBang() {

        super("FlashBang");

        setDisplayName("闪光弹");
        setCoolDown(45);
        setCombo(new SkillComboType[] { SkillComboType.LEFT, SkillComboType.RIGHT, SkillComboType.RIGHT });
    }

    @Override
    public void cast(KitPvPPlayer owner) {

        final Item object = owner.getWorld().dropItem(owner.getEyeLocation(), new ItemBuilder(Material.INK_SACK, 8, RandomManager.getRandomUUID().toString()).build());
        object.setVelocity(owner.getDirection().clone().multiply(0.8d).setY(0.25d));
        object.setCustomName(getDisplayName());
        object.setCustomNameVisible(true);

        new BukkitRunnable() {

            int living = 0;
            boolean done = false;
            boolean state = false;
            BukkitRunnable flashBang = null;

            @Override
            public void run() {

                if(living >= 8 * 20) {

                    done = true;
                }
                if(done) {

                    if(object != null) {

                        object.remove();
                    }
                    cancel();
                }
                if(!state && object.isOnGround()) {

                    state = true;

                    if(flashBang == null) {

                        flashBang = new BukkitRunnable() {

                            @Override
                            public void run() {

                                object.getWorld().playSound(object.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.8f, 1f);
                                object.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, object.getLocation(), 1);

                                for(LivingEntity entity : EntityManager.getEntityInRadius(object.getLocation(), 8d)) {

                                    int flashBangTime = 8;
                                    float flashBangVolume = 5f;

                                    if(entity instanceof Player && ((Player)entity).getName().equalsIgnoreCase(owner.getName())) {

                                        flashBangTime -= 3;
                                        flashBangVolume -= 2f;
                                    }
                                    entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_ANVIL_PLACE, flashBangVolume, 1f);
                                    entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, flashBangTime * 20, 0, true, true));
                                }
                                done = true;
                            }
                        };
                        flashBang.runTaskLater(getMain().getMain(), 25L);
                    }
                }
                living++;
            }
        }.runTaskTimer(getMain().getMain(), 20L, 1L);
    }
}
