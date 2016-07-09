package com.minecraft.moonlake.kitpvp.api.config;

import com.minecraft.moonlake.kitpvp.data.ConversionData;

import java.util.List;

/**
 * Created by MoonLake on 2016/7/9.
 */
public interface KitPvPConfig {

    /**
     * 获取配置文件基础数据
     *
     * @param key 键
     * @return 基础数据
     */
    BaseConfig get(String key);

    interface BaseConfig extends ConversionData {

        /**
         * 将数据转换到字符串集合
         *
         * @return 字符串集合
         */
        List<String> asStringList();
    }
}
