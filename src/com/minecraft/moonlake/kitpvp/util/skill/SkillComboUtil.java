package com.minecraft.moonlake.kitpvp.util.skill;

import com.minecraft.moonlake.kitpvp.api.occupa.skill.Skill;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillCombo;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillComboType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.language.l18n;
import com.minecraft.moonlake.kitpvp.manager.SkillComboManager;
import org.bukkit.Sound;

import java.util.*;

/**
 * Created by MoonLake on 2016/7/9.
 */
public class SkillComboUtil implements SkillCombo {

    private int comboIndex;
    private long comboTime;
    private final KitPvPPlayer kitPvPPlayer;
    private final SkillComboType[] combos;
    private final Map<Integer, Skill> skillMap;
    private final Map<Integer, Long> skillCoolDown;
    private final long interval;

    public SkillComboUtil(KitPvPPlayer kitPvPPlayer, int amount) {

        this.kitPvPPlayer = kitPvPPlayer;
        this.combos = new SkillComboType[amount + 1];
        this.skillMap = new HashMap<>();
        this.skillCoolDown = new HashMap<>();
        this.interval = 2000L;
    }

    /**
     * 应用下次组合类型
     *
     * @param comboType 组合类型
     */
    @Override
    public void applyClick(SkillComboType comboType) {

        checkExpired();

        combos[comboIndex++] = comboType;
        comboTime = System.currentTimeMillis();

        kitPvPPlayer.sendMainChatPacket(getCurrentComboString());
        kitPvPPlayer.playSound(Sound.UI_BUTTON_CLICK, 10f, 1f);

        int id = SkillComboManager.convertCombo(combos, comboIndex);
        if(comboIndex == combos.length || skillMap.containsKey(id)) {

            Skill skill = skillMap.get(id);

            if(skill != null) {

                if(skillCoolDown.containsKey(id)) {

                    long lastCastTime = skillCoolDown.get(id);

                    if(System.currentTimeMillis() - lastCastTime < skill.getCoolDown()) {

                        kitPvPPlayer.sendMainChatPacket(l18n.$("player.skill.cast.have.coolDown", getCurrentComboString(), d$n(skill), getCoolDown(id)));
                        return;
                    }
                }
                skill.cast(kitPvPPlayer);
                skillCoolDown.put(id, System.currentTimeMillis());

                kitPvPPlayer.sendMainChatPacket(l18n.$("player.skill.cast.success", getCurrentComboString(), d$n(skill)));

                // combo finish to break combo
                clearCombo();
            }
        }
    }

    private String d$n(Skill skill) {

        return skill.getDisplayName() != null ? skill.getDisplayName() : skill.getName();
    }

    private int getCoolDown(int id) {

        long lastCastTime = skillCoolDown.get(id);

        return (int)(System.currentTimeMillis() - lastCastTime) / 1000;
    }

    /**
     * 清除全部组合
     */
    @Override
    public void clearCombo() {

        this.comboIndex = 0;
    }

    /**
     * 获取当前组合的 ID
     *
     * @return 组合 ID
     */
    @Override
    public int getCurrentComboId() {

        return SkillComboManager.convertCombo(combos, comboIndex);
    }

    /**
     * 获取当前组合的字符串
     *
     * @return 组合字符串
     */
    @Override
    public String getCurrentComboString() {

        if (this.comboIndex == 0) {

            return "";
        }
        checkExpired();

        ArrayList<SkillComboType> active = new ArrayList<>(this.comboIndex);

        for (int i = 0; i < this.comboIndex; i++) {

            active.add(combos[i]);
        }
        return SkillComboManager.getComboString(active);
    }

    /**
     * 获取当前组合的索引
     *
     * @return 已经组合的索引
     */
    @Override
    public int getComboIndex() {

        return comboIndex;
    }

    /**
     * 设置此技能组合对象的技能集合
     *
     * @param skillList 技能集合
     */
    @Override
    public void setSkill(List<Skill> skillList) {

        if(skillList != null && !skillList.isEmpty()) {

            skillMap.clear();

            for(Skill skill : skillList) {

                skillMap.put(skill.getCombo(), skill);
            }
        }
    }

    /**
     * 检测组合是否应该破坏
     */
    private void checkExpired() {

        if(comboIndex == combos.length || System.currentTimeMillis() - comboTime > interval) {

            clearCombo();
        }
    }
}
