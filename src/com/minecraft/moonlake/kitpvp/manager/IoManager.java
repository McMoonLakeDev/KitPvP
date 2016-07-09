package com.minecraft.moonlake.kitpvp.manager;

import com.minecraft.moonlake.util.Util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MoonLake on 2016/5/25.
 */
public final class IoManager extends KitPvPManager {

    /**
     * 将输入流写出到指定文件对象
     *
     * @param out 输出文件对象
     * @param is 输入流
     */
    public static void outInputSteam(File out, InputStream is) {

        outInputSteam(out, is, 1024);
    }

    /**
     * 将输入流写出到指定文件对象
     *
     * @param out 输出文件对象
     * @param is 输入流
     * @param buf 缓冲区大小
     */
    public static void outInputSteam(File out, InputStream is, int buf) {

        FileOutputStream fs = null;

        try {

            fs = new FileOutputStream(out);
            byte[] buff = new byte[buf];

            int len;

            while((len = is.read(buff)) > 0) {

                fs.write(buff, 0, len);
            }
            fs.close();
            is.close();
        }
        catch (Exception e) {

            getMain().log("写出输入流文件时异常: " + e.getMessage());

            if(getMain().isDebug()) {

                e.printStackTrace();
            }
        }
    }

    /**
     * 动态读取语言文件并加载到 Map 集合
     *
     * @param lang 语言文件
     * @return Map 集合 文件不存在则 空集合
     */
    public static Map<String, String> readLangFile(File lang) {

        Map<String, String> temp = new HashMap<>();

        if(lang.exists()) {

            BufferedReader bf = null;
            InputStreamReader isr = null;
            String prefix = ConfigManager.get("Prefix").asString();

            try {

                isr = new InputStreamReader(new FileInputStream(lang), "UTF-8");
                bf = new BufferedReader(isr);
                String buf = null;

                while ((buf = bf.readLine()) != null) {

                    if(!buf.equals("") && !buf.startsWith("#")) {

                        if(buf.contains("=")) {

                            int $ = buf.indexOf("=");

                            String key = buf.substring(0, $);
                            String value = buf.substring($ + 1);

                            if(value.charAt(0) != '#') {

                                temp.put(key, Util.color(prefix + value));
                            }
                            else {
                                // unwanted prefix
                                temp.put(key, Util.color(value.substring(1)));
                            }
                        }
                    }
                }
            }
            catch (Exception e) {

                getMain().log("读取语言文件时异常: " + e.getMessage());

                if(getMain().isDebug()) {

                    e.printStackTrace();
                }
            }
            finally {

                try {

                    if(isr != null) {

                        isr.close();
                    }
                    if(bf != null) {

                        bf.close();
                    }
                }
                catch (Exception e) {

                    getMain().log("关闭语言文件流时异常: " + e.getMessage());

                    if(getMain().isDebug()) {

                        e.printStackTrace();
                    }
                }
            }
        }
        return temp;
    }
}
