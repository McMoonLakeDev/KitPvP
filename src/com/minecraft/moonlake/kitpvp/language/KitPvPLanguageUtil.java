package com.minecraft.moonlake.kitpvp.language;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.language.KitPvPLanguage;
import com.minecraft.moonlake.kitpvp.manager.ConfigManager;
import com.minecraft.moonlake.manager.IoManager;
import com.minecraft.moonlake.util.Util;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MoonLake on 2016/7/9.
 */
public class KitPvPLanguageUtil implements KitPvPLanguage {

    private final KitPvP main;
    private final String folder = "/language";

    private Map<String, String> kvMap;

    public KitPvPLanguageUtil(KitPvP main) {

        this.main = main;
        this.initFolder();
        this.kvMap = new HashMap<>();
    }

    private void initFolder() {

        File languange = new File(main.getDataFolder(), folder);
        if(!languange.exists())
            languange.mkdir();

        File defaultLocal = new File(main.getDataFolder(), folder + "/zh_CN.lang");
        if(!defaultLocal.exists()) {

            InputStream is = main.getResource("zh_CN.lang");
            IoManager.outInputSteam(defaultLocal, is);
        }
    }

    /**
     * 加载月色之湖职业战争语言文件
     */
    @Override
    public void loadKitPvPLanguage() {

        kvMap.clear();

        String setting = ConfigManager.get("Language").asString();
        File language = new File(main.getDataFolder(), folder + "/" + setting + ".lang");

        if(!language.exists()) {

            main.log("加载语言文件 '" + setting + "' 时异常: 未存在此文件.");
            language = new File(main.getDataFolder(), folder + "/zh_CN.lang");
            setting = "zh_CN";
        }
        kvMap = IoManager.readLangFile(ConfigManager.get("Prefix").asString(), language);
        main.log("成功载入 '" + setting + "' 语言文件文本消息.");
    }

    /**
     * 获取指定语言文件的指定 键 的值
     *
     * @param key 键
     * @return 值
     */
    @Override
    public String l18n(String key) {

        return kvMap.containsKey(key) ? kvMap.get(key) : "";
    }

    /**
     * 给此玩家发送语言文件指定 键 的值
     *
     * @param key  键
     * @param args 参数
     */
    @Override
    public String l18n(String key, Object... args) {

        return Util.mformat(l18n(key), args);
    }
}
