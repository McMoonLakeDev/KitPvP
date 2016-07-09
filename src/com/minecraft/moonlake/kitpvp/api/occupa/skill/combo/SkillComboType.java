package com.minecraft.moonlake.kitpvp.api.occupa.skill.combo;

/**
 * Created by MoonLake on 2016/7/9.
 */
public enum SkillComboType {

    /**
     * 技能组合类型: 左
     */
    LEFT(1, "Left", "左"),
    /**
     * 技能组合类型: 右
     */
    RIGHT(2, "Right", "右"),
    ;

    private int id;
    private String type;
    private String name;
    private final static SkillComboType[] COMBOS;

    static {

        COMBOS = new SkillComboType[] { LEFT, RIGHT };
    }

    SkillComboType(int id, String type, String name) {

        this.id = id;
        this.type = type;
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public String getType() {

        return type;
    }

    public String getName() {

        return name;
    }

    public static SkillComboType getById(int id) {

        if (id < 0 || id >= COMBOS.length) {

            return null;
        }
        return COMBOS[id];
    }
}
