package com.minecraft.moonlake.kitpvp.manager;

import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;

import java.util.*;

/**
 * Created by MoonLake on 2016/5/21.
 */
public final class EntityManager extends KitPvPManager {

    public final static Random RANDOM;

    static {

        RANDOM = new Random();
    }

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

    /**
     * 清除指定玩家的所有药水效果
     *
     * @param KitPvPPlayer 玩家
     */
    public static void clearPotionEffect(KitPvPPlayer kitPvPPlayer) {

        if(kitPvPPlayer != null) {

            clearPotionEffect(kitPvPPlayer.getBukkitPlayer());
        }
    }

    /**
     * 清除指定实体的所有药水效果
     *
     * @param entity 实体
     */
    public static void clearPotionEffect(LivingEntity entity) {

        if(entity != null && !entity.getActivePotionEffects().isEmpty()) {

            for(PotionEffect potionEffect : entity.getActivePotionEffects()) {

                if(potionEffect != null && entity.hasPotionEffect(potionEffect.getType())) {

                    entity.removePotionEffect(potionEffect.getType());
                }
            }
        }
    }

    /**
     * 在指定位置生成死亡烟花
     *
     * @param location 位置
     */
    public static void deathFireworks(Location location, int count) {

        if(count < 0) {

            count = 1;
        }
        for(int i = 0; i < count; i++) {

            Firework firework = location.getWorld().spawn(location, Firework.class);
            FireworkMeta fireworkMeta = firework.getFireworkMeta();
            fireworkMeta.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.BALL).withColor(getRandomColor()).build());
            firework.setFireworkMeta(fireworkMeta);
            firework.detonate();
        }
    }

    /**
     * 获取 Bukkit 的随机颜色对象
     *
     * @return 随机颜色
     */
    public static Color getRandomColor() {

        return Color.fromBGR(RANDOM.nextInt(255), RANDOM.nextInt(255), RANDOM.nextInt(255));
    }

    /**
     * 给予指定实体真实的伤害
     *
     * @param source 源实体
     * @param damager 攻击者实体
     * @param damage 真实伤害
     */
    public static void realDamage(LivingEntity source, KitPvPPlayer damager, double damage) {

        realDamage(source, damager.getBukkitPlayer(), damage);
    }

    /**
     * 给予指定实体真实的伤害
     *
     * @param source 源实体
     * @param damager 攻击者实体
     * @param damage 真实伤害
     */
    public static void realDamage(LivingEntity source, LivingEntity damager, double damage) {

        if(source != null && !source.isDead() && damager != null && !damager.isDead() && damage > 0d) {

            EntityDamageByEntityEvent edbee = new EntityDamageByEntityEvent(damager, source, EntityDamageEvent.DamageCause.CUSTOM, damage);

            source.damage(0d, damager);
            source.setLastDamageCause(edbee);

            Bukkit.getServer().getPluginManager().callEvent(edbee);

            if(source.getHealth() <= damage) {

                source.setHealth(0d);
                return;
            }
            source.setHealth(source.getHealth() - damage);
        }
    }
}
