package com.minecraft.moonlake.kitpvp.manager;

import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by MoonLake on 2016/5/17.
 */
public final class SkillComboManager extends KitPvPManager {

    public static int convertCombo(SkillComboType[] combos) {

        int id = 0;

        for (SkillComboType combo : combos) {

            id <<= 2;
            id |= combo.getId();
        }
        return id;
    }

    public static int convertCombo(SkillComboType[] combos, int amount) {

        int id = 0;

        for (int i = 0; (i < combos.length) && (i < amount); i++) {

            id <<= 2;
            id |= combos[i].getId();
        }
        return id;
    }

    public static String getComboString(int combo) {

        if (combo == -1) {

            return "";
        }
        return getComboString(convertId(combo));
    }

    public static String getComboString(List<SkillComboType> combos)  {

        if (combos == null) {

            return "";
        }

        String result = "";

        for (SkillComboType combo : combos) {

            if (result.length() > 0) {

                result = result + " + ";
            }
            result = result + combo.getType();
        }
        return result;
    }

    public static List<SkillComboType> convertId(int id) {

        ArrayList<SkillComboType> combos = new ArrayList<>();

        while (id > 0) {

            SkillComboType click = SkillComboType.getById(id & 0x3);

            if (click == null) {

                return null;
            }
            combos.add(click);
            id >>= 2;
        }
        Collections.reverse(combos);
        return combos;
    }
}
