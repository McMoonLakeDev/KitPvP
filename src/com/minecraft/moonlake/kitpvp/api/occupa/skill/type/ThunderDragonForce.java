package com.minecraft.moonlake.kitpvp.api.occupa.skill.type;

import com.minecraft.moonlake.kitpvp.api.event.entity.EntityDamageBySkillEvent;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.AbstractSkill;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.manager.EntityManager;
import com.minecraft.moonlake.particle.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

/**
 * Created by MoonLake on 2016/7/13.
 */
public class ThunderDragonForce extends AbstractSkill {

    public ThunderDragonForce() {

        super("ThunderDragonForce");

        setDisplayName("雷龙之力");
        setCoolDown(25);
        setCombo(new SkillComboType[] { SkillComboType.RIGHT, SkillComboType.LEFT, SkillComboType.LEFT });
    }

    @Override
    public void cast(KitPvPPlayer owner) {

        Block target = owner.getTargetBlock(20);

        if(target != null) {

            target.getWorld().strikeLightningEffect(target.getLocation());

            ParticleEffect.FLAME.display(target.getLocation().clone().add(0d, 1.2d, 0d), 32, 2f, 0.6f, 2f, 0f, 150);

            for(LivingEntity entity : EntityManager.getEntityInRadius(target.getLocation().clone().add(0d, 1d, 0d), 3d, owner)) {

                EntityDamageBySkillEvent edbse = new EntityDamageBySkillEvent(entity, ThunderDragonForce.this, owner);
                Bukkit.getServer().getPluginManager().callEvent(edbse);

                if(!edbse.isCancelled()) {

                    EntityManager.realDamage(entity, owner, 3d);
                    entity.setFireTicks(70);
                    entity.setVelocity(new Vector(0d, 0.2d, 0d));
                }
            }
        }
    }
}
