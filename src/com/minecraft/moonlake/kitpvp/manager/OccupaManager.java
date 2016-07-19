package com.minecraft.moonlake.kitpvp.manager;

import com.minecraft.moonlake.api.itemlib.ItemBuilder;
import com.minecraft.moonlake.kitpvp.api.occupa.Occupa;
import com.minecraft.moonlake.kitpvp.api.occupa.OccupaType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.manager.RandomManager;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MoonLake on 2016/7/9.
 */
public final class OccupaManager extends KitPvPManager {

    private final static int MONEY;

    static {

        MONEY = ConfigManager.get("Money").asInt();
    }

    /**
     * 初始化指定玩家的职业数据
     *
     * @param kitPvPPlayer 玩家
     */
    public static void initOccupaPlayer(KitPvPPlayer kitPvPPlayer) {

        initOccupaPlayer(kitPvPPlayer, kitPvPPlayer.getOccupa());
    }

    /**
     * 初始化指定玩家的职业数据
     *
     * @param kitPvPPlayer 玩家
     * @param occupa 职业对象
     */
    public static void initOccupaPlayer(KitPvPPlayer kitPvPPlayer, Occupa occupa) {

        if(kitPvPPlayer != null && occupa != null) {

            kitPvPPlayer.resetMaxHealth();
            kitPvPPlayer.clearPotionEffect();
            kitPvPPlayer.getInventory().clear();

            kitPvPPlayer.setOccupa(occupa);
            kitPvPPlayer.setMaxHealth(occupa.getMaxHealth());
            kitPvPPlayer.setHealth(kitPvPPlayer.getMaxHealth());
            kitPvPPlayer.getBukkitPlayer().setFoodLevel(20);
            kitPvPPlayer.getInventory().setItem(0, onAddOccupaSkillInfo(kitPvPPlayer, occupa.getWeapon()));
            kitPvPPlayer.getInventory().addItem(ItemManager.baseHealthPotion(4));
            kitPvPPlayer.getInventory().addItem(ItemManager.baseFoodItemStack(16));
            kitPvPPlayer.getInventory().setArmorContents(occupa.getArmors());
            kitPvPPlayer.getSkillCombo().clearSkill();
            kitPvPPlayer.getSkillCombo().resetCoolDown();
            kitPvPPlayer.getSkillCombo().setSkill(occupa.getSkillList());

            if(occupa.hasShield()) {

                kitPvPPlayer.setItemInOffHand(ItemManager.baseShield());
            }
            if(occupa.getType() == OccupaType.RANGER) {

                kitPvPPlayer.getInventory().addItem(new ItemBuilder(Material.ARROW).build());
            }
            if(DataManager.hasSpawnPoint()) {

                kitPvPPlayer.teleport(DataManager.getRandomSpawnPoint());
                kitPvPPlayer.l18n("player.kitPvP.join.battlefield", occupa.getType().getName());
            }
        }
    }

    /**
     * 给指定玩家的武器物品栈添加技能信息
     *
     * @param kitPvPPlayer 玩家
     * @param itemStack 武器物品栈
     * @return 添加信息后的武器物品栈
     */
    public static ItemStack onAddOccupaSkillInfo(KitPvPPlayer kitPvPPlayer, ItemStack itemStack) {

        List<String> occupaInfo = kitPvPPlayer.getOccupa().getType().getInfo().getLore();
        List<String> finalSkillInfo = new ArrayList<>();

        finalSkillInfo.add("");

        for(int i = 5; i < occupaInfo.size(); i++) {

            finalSkillInfo.add(occupaInfo.get(i));
        }
        finalSkillInfo.add("");

        return getMain().getMoonLake().getItemlib().setLore(itemStack, finalSkillInfo);
    }

    /**
     * 指定玩家成功斩杀目标玩家
     *
     * @param source 源玩家
     * @param target 目标玩家
     */
    public static void onKillPlayer(KitPvPPlayer source, KitPvPPlayer target) {

        int money = source.hasPermission("moonlake.kitpvp.dmoney") ? MONEY * 2 : MONEY;

        source.l18n("player.kill.player", target.getName(), money);

        EconomyManager.addMoney(source.getName(), money);
    }

    /**
     * 将指定玩家补给物品
     *
     * @param kitPvPPlayer 玩家
     */
    public static void supplyKitPvP(KitPvPPlayer kitPvPPlayer) {

        kitPvPPlayer.getInventory().addItem(ItemManager.baseHealthPotion(1));
        kitPvPPlayer.getInventory().addItem(ItemManager.baseFoodItemStack(3));
        kitPvPPlayer.getBukkitPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,  5 * 20, 0, true, true));
        kitPvPPlayer.playSound(Sound.ENTITY_ITEM_PICKUP, 10f, 1f);

        kitPvPPlayer.l18n("player.kill.player.supply");
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

        return Color.fromBGR(RandomManager.getRandom().nextInt(255), RandomManager.getRandom().nextInt(255), RandomManager.getRandom().nextInt(255));
    }
}
