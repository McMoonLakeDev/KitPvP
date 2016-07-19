package com.minecraft.moonlake.kitpvp.manager;

/**
 * Created by MoonLake on 2016/5/27.
 */
public final class VectorManager extends KitPvPManager {

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
}
