package com.minecraft.moonlake.kitpvp.api.occupa.skill.type;

import com.minecraft.moonlake.kitpvp.api.occupa.skill.AbstractSkill;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.VectorManager;
import com.minecraft.moonlake.kitpvp.particle.ParticleEffect;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

/**
 * Created by MoonLake on 2016/7/13.
 */
public class ThunderDragonForce extends AbstractSkill {

    public ThunderDragonForce() {

        super("ThunderDragonForce");

        setDisplayName("雷龙之力");
        setCoolDown(38);
        setCombo(new SkillComboType[] { SkillComboType.RIGHT, SkillComboType.LEFT, SkillComboType.LEFT });
    }

    @Override
    public void cast(KitPvPPlayer owner) {

        Block target = owner.getTargetBlock(20);

        if(target != null) {

            target.getWorld().strikeLightningEffect(target.getLocation());

            for(int i = 0; i < 10; i++) {

                Vector vector = VectorManager.getRandomCircleVector().multiply(VectorManager.getRandom().nextDouble() * 0.6d);
                vector.setY(VectorManager.getRandom().nextFloat() * 1.8f);
                Location location = target.getLocation().clone().add(0d, 1d, 0d).clone().add(vector);

                ParticleEffect.FLAME.display(location, 32f, 0f, 0f, 0f, 0f, 10);

                location.subtract(vector);
            }
        }
    }
}
