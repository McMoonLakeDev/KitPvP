package com.minecraft.moonlake.kitpvp.manager;

import org.bukkit.util.Vector;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by MoonLake on 2016/5/27.
 */
public final class VectorManager extends KitPvPManager {

    private final static Random random;
    public final static BufferedImage RKO_IMAGE;

    static {

        random = new Random(System.nanoTime());
        RKO_IMAGE = VectorManager.stringToBufferedImage(new Font("Tohoma", 0, 10), "RKO!");
    }

    /**
     * 获取随机类实例对象
     *
     * @return 随机类实例对象
     */
    public static Random getRandom() {

        return random;
    }

    /**
     * 获取随机矢量对象
     *
     * @return 随机矢量
     */
    public static Vector getRandomVector() {

        double x = random.nextDouble() * 2d - 1d;
        double y = random.nextDouble() * 2d - 1d;
        double z = random.nextDouble() * 2d - 1d;

        return new Vector(x, y, z).normalize();
    }

    /**
     * 获取随机圆环矢量对象
     *
     * @return 随机圆环矢量
     */
    public static Vector getRandomCircleVector() {

        double rnd = random.nextDouble() * 2d * Math.PI;
        double x = Math.cos(rnd);
        double z = Math.sin(rnd);

        return new Vector(x, 0d, z);
    }

    /**
     * 将 Bukkit 的 Vector 对象转换为 XYZ 坐标
     *
     * @param obuVector Vector 对象
     * @param isDouble 是否 Double 坐标
     * @return 坐标数据 ("0,0,0")
     */
    public static String toXYZ(Vector obuVector, boolean isDouble) {

        String data = "";

        if(isDouble) {

            data = obuVector.getX() + "," + obuVector.getY() + "," + obuVector.getZ();
        }
        else {

            data = obuVector.getBlockX() + "," + obuVector.getBlockY() + "," + obuVector.getBlockZ();
        }
        return data;
    }

    /**
     * 将 WorldEdit 的 Vector 对象转换为 XYZ 坐标
     *
     * @param oswVector Vector 对象
     * @param isDouble 是否 Double 坐标
     * @return 坐标数据 ("0,0,0")
     */
    public static String toXYZ(com.sk89q.worldedit.Vector oswVector, boolean isDouble) {

        String data = "";

        if(isDouble) {

            data = oswVector.getX() + "," + oswVector.getY() + "," + oswVector.getZ();
        }
        else {

            data = oswVector.getBlockX() + "," + oswVector.getBlockY() + "," + oswVector.getBlockZ();
        }
        return data;
    }

    /**
     * 将 XYZ 坐标数据转换到 WorldEdit 的 Vector 对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param z Z 坐标
     * @return Vector 对象
     */
    public static com.sk89q.worldedit.Vector fromXYZ(double x, double y, double z) {

        return new com.sk89q.worldedit.Vector(x, y, z);
    }

    /**
     * 将 XYZ 字符串坐标数据转换到  WorldEdit 的 Vector 对象
     *
     * @param pointData 字符串坐标数据 ("0,0,0")
     * @return Vector 对象 序列化失败则返回 null
     */
    public static com.sk89q.worldedit.Vector fromXYZ(String pointData) {

        if(!pointData.contains(",")) return null;
        String[] datas = pointData.replaceAll(" ", "").split(",");

        com.sk89q.worldedit.Vector vector = null;

        try {

            vector = new com.sk89q.worldedit.Vector(

                    Double.parseDouble(datas[0]),
                    Double.parseDouble(datas[1]),
                    Double.parseDouble(datas[2])
            );
        }
        catch (Exception e) { }

        return vector;
    }

    /**
     * 将一个矢量对象绕 Y 轴向指定角度旋转
     *
     * @param vector 矢量
     * @param angle 角度
     * @return 旋转角度后的矢量
     */
    public static Vector rotateAroundAxisY(Vector vector, double angle) {

        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = vector.getX() * cos + vector.getZ() * sin;
        double z = vector.getX() * -sin + vector.getZ() * cos;
        return vector.setX(x).setZ(z);
    }

    /**
     * 将指定字符串源转换到缓冲图片对象
     *
     * @param font 字体
     * @param string 字符串
     * @return  缓冲图片对象
     */
    public static BufferedImage stringToBufferedImage(Font font, String... string) {

        BufferedImage image = getStringImage(font, string);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.setFont(font);

        FontMetrics fontMetrics = graphics.getFontMetrics();

        for(int i = 0; i < string.length; i++) {

            graphics.drawString(string[i], 0, i * fontMetrics.getHeight() + 15);
        }
        graphics.dispose();

        return image;
    }

    /**
     * 获取字符串所占图片的宽度、高度的缓存图片对象
     *
     * @param font 字体
     * @param strs 字符串源
     * @return 缓存图片对象
     */
    public static BufferedImage getStringImage(Font font, String... strs) {

        BufferedImage image = new BufferedImage(1, 1, 6);
        Graphics graphics = image.getGraphics();
        graphics.setFont(font);

        FontRenderContext fontRenderContext = graphics.getFontMetrics().getFontRenderContext();
        Rectangle2D rectangle2D = font.getStringBounds(getStringArrayMaxLengthString(strs), fontRenderContext);
        graphics.dispose();

        int width = (int)Math.ceil(rectangle2D.getWidth());
        int height = 0;

        for(int i = 0; i < strs.length; i++) {

            height += (int)Math.ceil(rectangle2D.getHeight());
        }
        return new BufferedImage(width, height, 6);
    }

    /**
     * 获取字符串数据最大长度的字符串
     *
     * @param strs 字符串数组
     * @return 最大长度的字符串
     */
    public static String getStringArrayMaxLengthString(String[] strs) {

        List<Integer> list = new ArrayList<>();
        Map<Integer, String> map = new HashMap<>();

        for(int i = 0; i < strs.length; i++) {

            list.add(strs[i].length());
            map.put(strs[i].length(), strs[i]);
        }
        Collections.sort(list);

        return map.get(list.get(list.size() - 1));
    }

    /**
     * 获取指定范围的整数型随机数
     *
     * @param min 最低范围
     * @param max 最大范围
     * @return 范围内的随机数
     */
    public static int getRandomNumber(int min, int max) {

        return Math.abs(random.nextInt()) % (max - min + 1) + min;
    }
}
