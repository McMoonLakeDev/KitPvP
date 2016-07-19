package com.minecraft.moonlake.kitpvp.api.occupa.skill.type;

import com.minecraft.moonlake.kitpvp.api.event.entity.EntityDamageBySkillEvent;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.AbstractSkill;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.manager.BlockManager;
import com.minecraft.moonlake.manager.EntityManager;
import com.minecraft.moonlake.particle.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MoonLake on 2016/7/9.
 */
public class Shake extends AbstractSkill {

    public Shake() {

        super("Shake");

        setCoolDown(58);
        setDisplayName("山崩地裂");
        setCombo(new SkillComboType[] { SkillComboType.RIGHT, SkillComboType.LEFT, SkillComboType.RIGHT });
    }

    /**
     * 将此技能强制释放
     *
     * @param owner 释放者
     */
    @Override
    public void cast(KitPvPPlayer owner) {

        Vector vector = owner.getLocation().getDirection().multiply(1.2d).setY(0.6d);
        owner.setNoDamageTicks(100);
        owner.setVelocity(vector);

        new BukkitRunnable() {

            int living = 0;
            boolean done = false;
            BukkitRunnable shake = null;

            @Override
            public void run() {

                if(living >= 12 * 20) {

                    done = true;
                }
                if(done) {

                    if(shake != null) {

                        shake.cancel();
                    }
                    cancel();
                }
                ParticleEffect.FLAME.display(owner.getLocation(), 32, 0.4f, 0.16f, 0.4f, 0f, 3);

                if(owner.isOnGround()) {

                    if(shake == null) {

                        owner.setVelocity(new Vector(0d, 1.2d, 0d));
                        ParticleEffect.FLAME.display(owner.getLocation(), 32, 2f, 0.6f, 2f, 0f, 100);

                        shake = new BukkitRunnable() {

                            int count = 0;
                            boolean state = false;
                            List<LivingEntity> source = new ArrayList<>();

                            @Override
                            public void run() {

                                if(owner.isOnGround() && !state) {

                                    state = true;
                                    owner.getWorld().playSound(owner.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 10f, 1f);
                                    ParticleEffect.EXPLOSION_LARGE.display(owner.getLocation().add(0d, 0.8d, 0d), 32, 2.5f, 0.5f, 2.5f, 0f, 20);
                                }
                                if(state) {

                                    if(count >= 5) {

                                        done = true;

                                        for(LivingEntity entity : source) {

                                            EntityManager.realDamage(entity, owner, 6d);
                                        }
                                        return;
                                    }
                                    for(Block block : BlockManager.getFallingBlocksInRadius(owner.getLocation().clone().add(0d, -1d, 0d), count)) {

                                        FallingBlock fallingBlock = block.getWorld().spawnFallingBlock(block.getLocation().clone().add(0d, 1.1d, 0d), block.getType(), block.getData());
                                        fallingBlock.setVelocity(new Vector(0f, 0.3f, 0f));
                                        fallingBlock.setDropItem(false);
                                        fallingBlock.setHurtEntities(false);

                                        getMain().getFallingBlockListener().getFallingBlockList().add(fallingBlock);

                                        for(LivingEntity entity : EntityManager.getEntityInRadius(fallingBlock.getLocation(), 3.5d, owner)) {

                                            EntityDamageBySkillEvent edbse = new EntityDamageBySkillEvent(entity, Shake.this, owner);
                                            Bukkit.getServer().getPluginManager().callEvent(edbse);

                                            if(!edbse.isCancelled() && !source.contains(entity)) {

                                                source.add(entity);
                                                entity.setVelocity(new Vector(0d, 0.6d, 0d));
                                            }
                                        }
                                    }
                                    count++;
                                }
                            }
                        };
                        shake.runTaskTimer(getMain().getMain(), 10L, 1L);
                    }
                }
            }
        }.runTaskTimer(getMain().getMain(), 10L, 1L);
    }
}
