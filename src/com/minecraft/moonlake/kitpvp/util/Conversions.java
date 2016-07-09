package com.minecraft.moonlake.kitpvp.util;

import com.minecraft.moonlake.kitpvp.KitPvPPlugin;
import com.minecraft.moonlake.kitpvp.api.KitPvP;

/**
 * Created by MoonLake on 2016/6/7.
 */
public final class Conversions {

    private final static KitPvP MAIN;

    static {

        MAIN = KitPvPPlugin.getInstances();
    }

    /**
     * 获取月色之湖 KitPvP 主类实例对象
     *
     * @return KitPvP
     */
    protected static KitPvP getMain() {

        return MAIN;
    }

    /**
     * 将指定对象转换到整数型对象值
     *
     * @param object 对象
     * @return 整数型对象值 异常返回 0
     */
    public static int toInt(Object object) {

        if(object != null) {

            if(object instanceof Number) {

                return ((Number)object).intValue();
            }
            else {

                try {

                    return Integer.parseInt(object.toString());
                }
                catch (Exception e) {

                    if(getMain().isDebug()) {

                        e.printStackTrace();
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 将指定对象转换到单精度浮点数型对象值
     *
     * @param object 对象
     * @return 单精度浮点数型对象值 异常返回 0f
     */
    public static float toFloat(Object object) {

        if(object != null) {

            if(object instanceof Number) {

                return ((Number)object).floatValue();
            }
            else {

                try {

                    return Float.parseFloat(object.toString());
                }
                catch (Exception e) {

                    if(getMain().isDebug()) {

                        e.printStackTrace();
                    }
                }
            }
        }
        return 0f;
    }

    /**
     * 将指定对象转换到双精度浮点数型对象值
     *
     * @param object 对象
     * @return 双精度浮点数型对象值 异常返回 0d
     */
    public static double toDouble(Object object) {

        if(object != null) {

            if(object instanceof Number) {

                return ((Number)object).doubleValue();
            }
            else {

                try {

                    return Double.parseDouble(object.toString());
                }
                catch (Exception e) {

                    if(getMain().isDebug()) {

                        e.printStackTrace();
                    }
                }
            }
        }
        return 0d;
    }

    /**
     * 将指定对象转换到长整数型对象值
     *
     * @param object 对象
     * @return 长整数型对象值 异常返回 0L
     */
    public static long toLong(Object object) {

        if(object != null) {

            if(object instanceof Number) {

                return ((Number)object).longValue();
            }
            else {

                try {

                    return Long.parseLong(object.toString());
                }
                catch (Exception e) {

                    if(getMain().isDebug()) {

                        e.printStackTrace();
                    }
                }
            }
        }
        return 0L;
    }

    /**
     * 将指定对象转换到短整数型对象值
     *
     * @param object 对象
     * @return 短整数型对象值 异常返回 0
     */
    public static short toShort(Object object) {

        if(object != null) {

            if(object instanceof Number) {

                return ((Number)object).shortValue();
            }
            else {

                try {

                    return Short.parseShort(object.toString());
                }
                catch (Exception e) {

                    if(getMain().isDebug()) {

                        e.printStackTrace();
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 将指定对象转换到字节型对象值
     *
     * @param object 对象
     * @return 字节型对象值 异常返回 0
     */
    public static byte toByte(Object object) {

        if(object != null) {

            if(object instanceof Number) {

                return ((Number)object).byteValue();
            }
            else {

                try {

                    return Byte.parseByte(object.toString());
                }
                catch (Exception e) {

                    if(getMain().isDebug()) {

                        e.printStackTrace();
                    }
                }
            }
        }
        return 0;
    }
}
