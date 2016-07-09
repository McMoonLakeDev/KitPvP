package com.minecraft.moonlake.kitpvp.api.occupa.skill.type;

import com.minecraft.moonlake.kitpvp.api.occupa.skill.AbstractSkill;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;

/**
 * Created by MoonLake on 2016/7/9.
 */
public class Shake extends AbstractSkill {

    // skill test
    public Shake() {

        super("Shake");

        setCoolDown(50);
        setDisplayName("山崩地裂");
        setCombo(new SkillComboType[] { SkillComboType.RIGHT, SkillComboType.LEFT, SkillComboType.RIGHT });
    }

    /**
     * 将此技能强制释放
     *
     * @param kitPvPPlayer 释放者
     */
    @Override
    public void cast(KitPvPPlayer kitPvPPlayer) {

    }
}
