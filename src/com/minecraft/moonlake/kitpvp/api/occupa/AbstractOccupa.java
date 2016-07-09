package com.minecraft.moonlake.kitpvp.api.occupa;

import com.minecraft.moonlake.kitpvp.api.occupa.skill.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MoonLake on 2016/7/9.
 */
public abstract class AbstractOccupa implements Occupa {

    private final OccupaType type;
    private final List<Skill> skillList;

    public AbstractOccupa(OccupaType type) {

        this.type = type;
        this.skillList = new ArrayList<>();
    }
}
