package com.minecraft.moonlake.kitpvp.api.occupa;

import com.minecraft.moonlake.kitpvp.KitPvPPlugin;
import com.minecraft.moonlake.kitpvp.api.occupa.type.*;
import com.minecraft.moonlake.reflect.Reflect;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MoonLake on 2016/7/9.
 */
public enum OccupaType {

    /**
     * 职业类型: 未知
     */
    UNKNOW("Unknow", "未知", null, null),
    /**
     * 职业类型: 剑客
     */
    WARRIOR("Warrior", "剑客", Material.DIAMOND_SWORD, Warrior.class),
    /**
     * 职业类型: 刺客
     */
    ASSASSIN("Assassin", "刺客", Material.SHEARS, Assassin.class),
    /**
     * 职业类型: 魔法师
     */
    MAGICIAN("Magician", "魔法师", Material.BLAZE_ROD, Magician.class),
    /**
     * 职业类型: 游侠
     */
    RANGER("Ranger", "游侠", Material.BOW, Ranger.class),
    /**
     * 职业类型: 坦克
     */
    TANK("Tank", "坦克", Material.CLAY_BRICK, Tank.class),
    ;

    private String type;
    private String name;
    private Material weapon;
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

    OccupaType(String type, String name, Material weapon, Class<? extends Occupa> clazz) {

        this.type = type;
        this.name = name;
        this.weapon = weapon;
        this.clazz = clazz;
    }

    public String getType() {

        return type;
    }

    public String getName() {

        return name;
    }

    public Material getWeapon() {

        return weapon;
    }

    public Class<? extends Occupa> getClazz() {

        return clazz;
    }

    public <T extends Occupa> T newInstance(Object... argsObject) {

        T t = null;

        try {

            t = (T) Reflect.instantiateObject(getClazz(), argsObject);
        }
        catch (Exception e) {

            KitPvPPlugin.getInstances().log("获取职业类实例对象时异常: " + e.getMessage());

            if(KitPvPPlugin.getInstances().isDebug()) {

                e.printStackTrace();
            }
        }
        return t;
    }

    public static OccupaType fromType(String type) {

        return TYPE_MAP.get(type.toLowerCase());
    }

    public static OccupaType fromName(String name) {

        return NAME_MAP.get(name.toLowerCase());
    }
}
