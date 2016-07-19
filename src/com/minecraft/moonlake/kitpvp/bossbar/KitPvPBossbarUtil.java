package com.minecraft.moonlake.kitpvp.bossbar;

import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.api.player.bossbar.KitPvPBossbar;
import com.minecraft.moonlake.kitpvp.manager.ConfigManager;
import com.minecraft.moonlake.kitpvp.manager.EconomyManager;
import com.minecraft.moonlake.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

/**
 * Created by MoonLake on 2016/7/17.
 */
public class KitPvPBossbarUtil implements KitPvPBossbar {

    private final KitPvPPlayer kitPvPPlayer;
    private final BossBar bossBar;
    private final String title;

    public KitPvPBossbarUtil(KitPvPPlayer kitPvPPlayer) {

        this.kitPvPPlayer = kitPvPPlayer;
        this.title = ConfigManager.get("PlayerBar.Title").asString();
        this.bossBar = Bukkit.getServer().createBossBar(toTitle(kitPvPPlayer), BarColor.PINK, BarStyle.SOLID, BarFlag.PLAY_BOSS_MUSIC);
        this.bossBar.addPlayer(kitPvPPlayer.getBukkitPlayer());
        this.bossBar.setProgress(1.0d);
    }

    /**
     * 更新此玩家的 Boss 血条数据
     */
    @Override
    public void update() {

        bossBar.setTitle(toTitle(kitPvPPlayer));
    }

    private String toTitle(KitPvPPlayer kitPvPPlayer) {

        String format = new String(title)
                .replace("%money", String.valueOf(EconomyManager.getMoney(kitPvPPlayer.getName())))
                .replace("%point", String.valueOf(EconomyManager.getPoint(kitPvPPlayer.getName())))
                .replace("%player", kitPvPPlayer.getName());

        return Util.color(format);
    }
}
