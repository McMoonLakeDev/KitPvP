package com.minecraft.moonlake.kitpvp.api.language;

/**
 * Created by MoonLake on 2016/7/9.
 */
public interface KitPvPLanguage {

    /**
     * 加载月色之湖职业战争语言文件
     */
    void loadKitPvPLanguage();

    /**
     * 获取指定语言文件的指定 键 的值
     *
     * @param key 键
     * @return 值
     */
    String l18n(String key);

    /**
     * 给此玩家发送语言文件指定 键 的值
     *
     * @param key 键
     * @param args 参数
     */
    String l18n(String key, Object... args);
}
