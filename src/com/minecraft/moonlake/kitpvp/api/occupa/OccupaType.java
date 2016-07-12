package com.minecraft.moonlake.kitpvp.api.occupa;

import com.minecraft.moonlake.kitpvp.KitPvPPlugin;
import com.minecraft.moonlake.kitpvp.api.occupa.type.*;
import com.minecraft.moonlake.kitpvp.questbuy.BuyType;
import com.minecraft.moonlake.reflect.Reflect;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MoonLake on 2016/7/9.
 */
public enum OccupaType {

    /**
     * 职业类型: 剑客
     */
    WARRIOR("Warrior", "剑客", null, Material.DIAMOND_SWORD, Warrior.class, OccupaInfo.WARRIOR),
    /**
     * 职业类型: 刺客
     */
    ASSASSIN("Assassin", "刺客", new BuyType(BuyType.Type.MONEY, 500), Material.SHEARS, Assassin.class, OccupaInfo.ASSASSIN),
    /**
     * 职业类型: 游侠
     */
    RANGER("Ranger", "游侠", new BuyType(BuyType.Type.MONEY, 380), Material.BOW, Ranger.class, OccupaInfo.RANGER),
    /**
     * 职业类型: 坦克
     */
    TANK("Tank", "坦克", new BuyType(BuyType.Type.MONEY, 450), Material.CLAY_BRICK, Tank.class, OccupaInfo.TANK),
    /**
     * 职业类型: 魔法师
     */
    MAGICIAN("Magician", "魔法师", new BuyType(BuyType.Type.POINT, 1000), Material.BLAZE_ROD, Magician.class, OccupaInfo.MAGICIAN),
    ;

    private String type;
    private String name;
    private BuyType buyType;
    private Material weapon;
    private Class<? extends Occupa> clazz;
    private OccupaInfo info;
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

    OccupaType(String type, String name, BuyType buyType, Material weapon, Class<? extends Occupa> clazz, OccupaInfo info) {

        this.type = type;
        this.name = name;
        this.buyType = buyType;
        this.weapon = weapon;
        this.clazz = clazz;
        this.info = info;
    }

    public String getType() {

        return type;
    }

    public String getName() {

        return name;
    }

    public BuyType getBuyType() {

        return buyType;
    }

    public Material getWeapon() {

        return weapon;
    }

    public Class<? extends Occupa> getClazz() {

        return clazz;
    }

    public OccupaInfo getInfo() {

        return info;
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
