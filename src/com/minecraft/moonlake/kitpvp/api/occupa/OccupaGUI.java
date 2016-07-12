package com.minecraft.moonlake.kitpvp.api.occupa;

import com.minecraft.moonlake.api.itemlib.ItemBuilder;
import com.minecraft.moonlake.kitpvp.KitPvPPlugin;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.language.l18n;
import com.minecraft.moonlake.kitpvp.manager.GUIManager;
import com.minecraft.moonlake.kitpvp.manager.OccupaManager;
import com.minecraft.moonlake.kitpvp.questbuy.QuestBuy;
import com.minecraft.moonlake.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

/**
 * Created by MoonLake on 2016/7/11.
 */
public class OccupaGUI {

    private final KitPvPPlayer kitPvPPlayer;
    private final Inventory gui;

    public OccupaGUI(KitPvPPlayer kitPvPPlayer) {

        this.kitPvPPlayer = kitPvPPlayer;
        this.gui = Bukkit.getServer().createInventory(kitPvPPlayer, 6 * 9, GUIManager.formatOccupaGUITitle(kitPvPPlayer));
    }

    /**
     * 加载此玩家的职业战争职业数据
     */
    public void loadKitPvPOccupaData() {

        gui.clear();

        File folder = new File(KitPvPPlugin.getInstances().getDataFolder(), "/data/" + kitPvPPlayer.getName());

        if(!folder.exists()) {

            folder.mkdir();
        }
        for(OccupaType occupaType : OccupaType.values()) {

            File occupa = new File(KitPvPPlugin.getInstances().getDataFolder(), "/data/" + kitPvPPlayer.getName() + "/" + occupaType.getType().toLowerCase() + ".dat");

            if(kitPvPPlayer.hasPermission("moonlake.kitpvp.occupadata") || occupaType.getBuyType() == null) {

                if(occupa.getParentFile() != null) {

                    if(!occupa.getParentFile().exists()) {

                        occupa.getParentFile().mkdirs();
                    }
                }
                if(!occupa.exists()) {

                    try {

                        occupa.createNewFile();
                    }
                    catch (IOException e) {

                        KitPvPPlugin.getInstances().log("创建职业战争玩家职业数据文件时异常: " + e.getMessage());

                        if(KitPvPPlugin.getInstances().isDebug()) {

                            e.printStackTrace();
                        }
                    }
                }
            }
            boolean have = occupa.exists();

            ItemStack occupaIcon = null;

            if(occupaType.getBuyType() == null || have) {

                occupaIcon = new ItemBuilder(Material.MONSTER_EGG, 0, "&6" + occupaType.getName())
                        .setAmount(have ? 1 : 0)
                        .addLores(occupaType.getInfo().getLore())
                        .addLore("")
                        .addLore(isUnLockString(have))
                        .build();
            }
            else {

                String buyType = l18n.$("player.questBuy.guiFormat", occupaType.getBuyType().getValue(), occupaType.getBuyType().getType().getName());

                occupaIcon = new ItemBuilder(Material.MONSTER_EGG, 0, "&6" + occupaType.getName())
                        .setAmount(have ? 1 : 0)
                        .addLores(occupaType.getInfo().getLore())
                        .addLore("")
                        .addLore(buyType)
                        .addLore("")
                        .addLore(isUnLockString(have))
                        .addEnchant(Enchantment.DURABILITY, 1)
                        .addFlag(ItemFlag.HIDE_ENCHANTS)
                        .build();
            }
            gui.addItem(occupaIcon);
        }
    }

    /**
     * 将此职业战争职业 GUI 打开给玩家
     */
    public void openGUI() {

        kitPvPPlayer.openInventory(gui);
    }

    /**
     * 处理职业战争 GUI 的点击事件
     *
     * @param event 事件对象
     */
    public void onOccupaGUIClick(InventoryClickEvent event) {

        ItemStack clickItem = event.getCurrentItem();
        String occupaName = clickItem.getItemMeta().hasDisplayName() ? clickItem.getItemMeta().getDisplayName() : clickItem.getType().name();

        if(occupaName != null) {

            OccupaType occupaType = OccupaType.fromName(Util.fColor(occupaName));

            if(occupaType != null) {

                File occupa = new File(KitPvPPlugin.getInstances().getDataFolder(), "/data/" + kitPvPPlayer.getName() + "/" + occupaType.getType().toLowerCase() + ".dat");

                if(!occupa.exists()) {

                    if(occupaType.getBuyType() != null) {

                        if(!occupaType.getBuyType().checkPlayer(kitPvPPlayer)) {

                            occupaType.getBuyType().notHave(kitPvPPlayer);
                            return;
                        }
                    }
                    QuestBuy questBuy = new QuestBuy(kitPvPPlayer, clickItem, "【职业战争购买职业】: " + kitPvPPlayer.getName()) {

                        @Override
                        public void confirm() {

                            if(occupa.getParentFile() != null) {

                                if(!occupa.getParentFile().exists()) {

                                    occupa.getParentFile().mkdirs();
                                }
                            }
                            if(!occupa.exists()) {

                                try {

                                    occupa.createNewFile();
                                }
                                catch (IOException e) {

                                    KitPvPPlugin.getInstances().log("创建职业战争玩家职业数据文件时异常: " + e.getMessage());

                                    if(KitPvPPlugin.getInstances().isDebug()) {

                                        e.printStackTrace();
                                    }
                                }
                            }
                            event.setCurrentItem(new ItemBuilder(Material.MONSTER_EGG, 0, "&6" + occupaType.getName())
                                    .setAmount(1)
                                    .addLores(occupaType.getInfo().getLore())
                                    .addLore("")
                                    .addLore(isUnLockString(true))
                                    .build());
                            occupaType.getBuyType().takeBuyValue(kitPvPPlayer);
                            kitPvPPlayer.l18n("player.questBuy.confirm", occupaType.getBuyType().getValue(), occupaType.getBuyType().getType().getName(), occupaType.getName());
                        }

                        @Override
                        public void cancel() {

                            kitPvPPlayer.l18n("player.questBuy.cancel", occupaType.getName());
                        }
                    };
                    questBuy.openQuestBuy();
                    return;
                }
                kitPvPPlayer.closeInventory();
                kitPvPPlayer.l18n("player.occupaGUI.onChange", occupaName);
                OccupaManager.initOccupaPlayer(kitPvPPlayer, occupaType.newInstance());
            }
        }
    }

    private String isUnLockString(boolean state) {

        return state ? "&b> &a该职业已经被解锁" : "&b> &c该职业还未被解锁";
    }
}
