package com.minecraft.moonlake.kitpvp.api.occupa;

import com.minecraft.moonlake.kitpvp.api.occupa.type.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MoonLake on 2016/7/9.
 */
public enum OccupaType {

    /**
     * 职业类型: 未知
     */
    UNKNOW("Unknow", "未知", null),
    /**
     * 职业类型: 剑客
     */
    WARRIOR("Warrior", "剑客", Warrior.class),
    /**
     * 职业类型: 刺客
     */
    ASSASSIN("Assassin", "刺客", Assassin.class),
    /**
     * 职业类型: 魔法师
     */
    MAGICIAN("Magician", "魔法师", Magician.class),
    /**
     * 职业类型: 游侠
     */
    RANGER("Ranger", "游侠", Ranger.class),
    /**
     * 职业类型: 坦克
     */
    TANK("Tank", "坦克", Tank.class),
    /**
     * 职业类型: 牧师
     */
    PASTOR("Pastor", "牧师", Pastor.class),
    /**
     * 职业类型: 机枪手
     */
    GUNNER("Gunner", "机枪手", Gunner.class),
    ;

    private String type;
    private String name;
    private Class<? extends Occupa> clazz;
    private final static Map<String, OccupaType> TYPE_MAP;
    private final static Map<String, OccupaType> NAME_MAP;

    static {

        TYPE_MAP = new HashMap<>();
        NAME_MAP = new HashMap<>();

        for(OccupaType occupaType : values()) {

            TYPE_MAP.put(occupaType.type.toLowerCase(), occupaType);
            NAME_MAP.put(occupaType.name.toLowerCase(), occupaType);
        }
    }

    OccupaType(String type, String name, Class<? extends Occupa> clazz) {

        this.type = type;
        this.name = name;
        this.clazz = clazz;
    }

    public String getType() {

        return type;
    }

    public String getName() {

        return name;
    }

    public Class<? extends Occupa> getClazz() {

        return clazz;
    }

    public static OccupaType fromType(String type) {

        return TYPE_MAP.get(type.toLowerCase());
    }

    public static OccupaType fromName(String name) {

        return NAME_MAP.get(name.toLowerCase());
    }
}
