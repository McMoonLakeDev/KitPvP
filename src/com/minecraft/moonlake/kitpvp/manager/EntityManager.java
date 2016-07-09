package com.minecraft.moonlake.kitpvp.manager;

import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by MoonLake on 2016/5/21.
 */
public final class EntityManager extends KitPvPManager {

    /**
     * 获取指定位置的半径内的实体
     *
     * @param location 位置
     * @param radius 半径
     * @return 实体集合
     */
    public static List<LivingEntity> getEntityInRadius(Location location, double radius) {

        return getEntityInRadius(location, radius, new HashSet<Class<? extends LivingEntity>>());
    }

    /**
     * 获取指定位置的半径内的实体
     *
     * @param location 位置
     * @param radius 半径
     * @param owner 无视主人
     * @return 实体集合
     */
    public static List<LivingEntity> getEntityInRadius(Location location, double radius, Player owner) {

        List<LivingEntity> entityList = getEntityInRadius(location, radius);

        for(int i = 0; i < entityList.size(); i++) {

            Entity entity = entityList.get(i);

            if(entity instanceof Player && ((Player)entity).getName().equals(owner.getName())) {

                entityList.remove(entity);

                i--;
            }
        }
        return entityList;
    }

    /**
     * 获取指定位置的半径内的实体
     *
     * @param location 位置
     * @param radius 半径
     * @param owner 无视主人
     * @param ignoreEntity 无视的实体
     * @return 实体集合
     */
    public static List<LivingEntity> getEntityInRadius(Location location, double radius, Player owner, Set<Class<? extends LivingEntity>> ignoreEntity) {

        List<LivingEntity> entityList = getEntityInRadius(location, radius, ignoreEntity);

        for(int i = 0; i < entityList.size(); i++) {

            Entity entity = entityList.get(i);

            if(entity instanceof Player && ((Player)entity).getName().equals(owner.getName())) {

                entityList.remove(entity);

                i--;
            }
        }
        return entityList;
    }

    /**
     * 获取指定位置的半径内的实体
     *
     * @param location 位置
     * @param radius 半径
     * @param owner 无视主人
     * @return 实体集合
     */
    public static List<LivingEntity> getEntityInRadius(Location location, double radius, KitPvPPlayer owner) {

        List<LivingEntity> entityList = getEntityInRadius(location, radius);

        for(int i = 0; i < entityList.size(); i++) {

            Entity entity = entityList.get(i);

            if(entity instanceof Player && ((Player)entity).getName().equals(owner.getName())) {

                entityList.remove(entity);

                i--;
            }
        }
        return entityList;
    }

    /**
     * 获取指定位置的半径内的实体
     *
     * @param location 位置
     * @param radius 半径
     * @param owner 无视主人
     * @param ignoreEntity 无视的实体
     * @return 实体集合
     */
    public static List<LivingEntity> getEntityInRadius(Location location, double radius, KitPvPPlayer owner, Set<Class<? extends LivingEntity>> ignoreEntity) {

        List<LivingEntity> entityList = getEntityInRadius(location, radius, ignoreEntity);

        for(int i = 0; i < entityList.size(); i++) {

            Entity entity = entityList.get(i);

            if(entity instanceof Player && ((Player)entity).getName().equals(owner.getName())) {

                entityList.remove(entity);

                i--;
            }
        }
        return entityList;
    }

    /**
     * 获取指定位置的半径内的实体
     *
     * @param location 位置
     * @param radius 半径
     * @param ignoreEntity 无视的实体
     * @return 实体集合
     */
    public static List<LivingEntity> getEntityInRadius(Location location, double radius, Set<Class<? extends LivingEntity>> ignoreEntity) {

        List<LivingEntity> entityList = new ArrayList<>();

        for(Entity entity : location.getWorld().getNearbyEntities(location, radius, radius, radius)) {

            if(entity != null && !entity.isDead() && entity instanceof LivingEntity && !ignoreEntity.contains(entity.getClass())) {

                entityList.add(((LivingEntity)entity));
            }
        }
        return entityList;
    }
}
