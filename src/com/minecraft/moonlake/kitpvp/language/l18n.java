package com.minecraft.moonlake.kitpvp.language;

import com.minecraft.moonlake.kitpvp.manager.KitPvPManager;

/**
 * Created by MoonLake on 2016/5/24.
 */
public final class l18n extends KitPvPManager {

    /**
     * 获取指定语言文件的指定 键 的值
     *
     * @param key 键
     * @return 值
     */
    public static String $(String key) {

        return getMain().l18n(key);
    }

    /**
     * 获取指定语言文件的指定 键 的值
     *
     * @param key 键
     * @param args 参数
     * @return 值
     */
    public static String $(String key, Object... args) {

        return getMain().l18n(key, args);
    }
}
