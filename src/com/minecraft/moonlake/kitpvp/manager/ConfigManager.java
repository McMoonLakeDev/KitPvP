package com.minecraft.moonlake.kitpvp.manager;

import com.minecraft.moonlake.kitpvp.api.config.KitPvPConfig;

/**
 * Created by MoonLake on 2016/5/23.
 */
public final class ConfigManager extends KitPvPManager {

    /**
     * 获取配置文件基础数据
     *
     * @param key 键
     * @return 基础数据
     */
    public static KitPvPConfig.BaseConfig get(String key) {

        return getMain().getKConfig().get(key);
    }
}
