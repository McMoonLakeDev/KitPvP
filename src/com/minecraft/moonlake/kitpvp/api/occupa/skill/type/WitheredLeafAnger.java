package com.minecraft.moonlake.kitpvp.api.occupa.skill.type;

import com.minecraft.moonlake.kitpvp.api.occupa.skill.AbstractSkill;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by MoonLake on 2016/7/14.
 */
public class WitheredLeafAnger extends AbstractSkill {

    public WitheredLeafAnger() {

        super("WitheredLeafAnger");

        setDisplayName("枯叶之怒");
        setCoolDown(60);
        setCombo(new SkillComboType[] { SkillComboType.LEFT, SkillComboType.LEFT, SkillComboType.RIGHT });
    }

    @Override
    public void cast(KitPvPPlayer owner) {

        owner.getBukkitPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15 * 20, 0, true, true));
    }
}
