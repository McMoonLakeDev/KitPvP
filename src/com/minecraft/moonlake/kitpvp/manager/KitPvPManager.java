package com.minecraft.moonlake.kitpvp.manager;

import com.minecraft.moonlake.kitpvp.KitPvPPlugin;
import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.KitPvPCore;

/**
 * Created by MoonLake on 2016/7/9.
 */
public abstract class KitPvPManager implements KitPvPCore {

    private final static KitPvP MAIN;

    static {

        MAIN = KitPvPPlugin.getInstances();
    }

    /**
     * 获取职业战争 KitPvP 的实例对象
     *
     * @return KitPvP 实例
     */
    @Override
    public KitPvP getInstance() {

        return MAIN;
    }

    /**
     * 获取职业战争 KitPvP 的实例对象
     *
     * @return KitPvP 实例
     */
    protected static KitPvP getMain() {

        return MAIN;
    }
}
