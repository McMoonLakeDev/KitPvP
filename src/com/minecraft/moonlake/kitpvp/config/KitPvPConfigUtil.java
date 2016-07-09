package com.minecraft.moonlake.kitpvp.config;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.config.KitPvPConfig;
import com.minecraft.moonlake.kitpvp.util.Conversions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MoonLake on 2016/7/9.
 */
public class KitPvPConfigUtil implements KitPvPConfig {

    private final KitPvP main;

    public KitPvPConfigUtil(KitPvP main) {

        this.main = main;
    }

    /**
     * 获取配置文件基础数据
     *
     * @param key 键
     * @return 基础数据
     */
    @Override
    public BaseConfig get(String key) {

        return new BaseConfigUtil(main.getMain().getConfig().get(key));
    }


    public static class BaseConfigUtil implements BaseConfig {

        private Object obj;

        public BaseConfigUtil(Object obj) {

            this.obj = obj;
        }

        /**
         * 将数据转换到字符串
         *
         * @return 字符串
         */
        @Override
        public String asString() {

            return obj != null ? obj.toString() : "";
        }

        /**
         * 将数据转换到整数
         *
         * @return 整数
         */
        @Override
        public int asInt() {

            return obj != null ? Conversions.toInt(obj) : 0;
        }

        /**
         * 将数据转换到双精度浮点数
         *
         * @return 双精度浮点数
         */
        @Override
        public double asDouble() {

            return obj != null ? Conversions.toDouble(obj) : 0d;
        }

        /**
         * 将数据转换到单精度浮点数
         *
         * @return 单精度浮点数
         */
        @Override
        public float asFloat() {

            return obj != null ? Conversions.toFloat(obj) : 0f;
        }

        /**
         * 将数据转换到长整数
         *
         * @return 长整数
         */
        @Override
        public long asLong() {

            return obj != null ? Conversions.toLong(obj) : 0L;
        }

        /**
         * 将数据转换到短整数
         *
         * @return 短整数
         */
        @Override
        public short asShort() {

            return obj != null ? Conversions.toShort(obj) : 0;
        }

        /**
         * 将数据转换到字符串集合
         *
         * @return 字符串集合
         */
        @Override
        public List<String> asStringList() {

            return obj != null && obj instanceof List ? (List)obj : new ArrayList<>();
        }

        /**
         * 将数据转换到字符
         *
         * @return 字符
         */
        @Override
        public char asChar() {

            return obj != null && obj instanceof Character ? (char)obj : '\0';
        }

        /**
         * 将数据转换到布尔值
         *
         * @return 布尔值
         */
        @Override
        public boolean asBoolean() {

            return obj != null && obj instanceof Boolean ? (Boolean)obj : Boolean.FALSE;
        }

        /**
         * 将数据转换到字节
         *
         * @return 字节
         */
        @Override
        public byte asByte() {

            return obj != null ? Conversions.toByte(obj) : 0;
        }
    }
}
