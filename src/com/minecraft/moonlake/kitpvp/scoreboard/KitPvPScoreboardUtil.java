package com.minecraft.moonlake.kitpvp.scoreboard;

import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.api.player.scoreboard.KitPvPScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Created by MoonLake on 2016/7/11.
 */
public class KitPvPScoreboardUtil implements KitPvPScoreboard {

    private final KitPvPPlayer kitPvPPlayer;
    private final Scoreboard scoreboard;
    private final Objective objective;

    public KitPvPScoreboardUtil(KitPvPPlayer kitPvPPlayer) {

        this.kitPvPPlayer = kitPvPPlayer;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.getObjective("kitpvp") == null ? scoreboard.registerNewObjective("kitpvp", "dummy") : scoreboard.getObjective("kitpvp");
    }

    /**
     * 注册此玩家的职业战争计分板
     */
    @Override
    public void register() {

        objective.setDisplayName("MoonLake 职业战争");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("    ").setScore(10);
        objective.getScore("> Rank: null").setScore(9);
        objective.getScore("   ").setScore(8);
        objective.getScore("> 杀敌数: " + kitPvPPlayer.getKill()).setScore(7);
        objective.getScore("> 死亡数: " + kitPvPPlayer.getDeath()).setScore(6);
        objective.getScore("  ").setScore(5);
        objective.getScore("> K/D比: " + kitPvPPlayer.getKD()).setScore(4);
        objective.getScore(" ").setScore(3);
        objective.getScore("官方Q群: 377607025").setScore(2);
        objective.getScore("").setScore(1);
        objective.getScore("   www.mcyszh.com").setScore(0);

        kitPvPPlayer.getBukkitPlayer().setScoreboard(scoreboard);
    }

    /**
     * 卸载此玩家的职业战争计分板
     */
    @Override
    public void unregister() {

        objective.unregister();
        scoreboard.clearSlot(DisplaySlot.SIDEBAR);
    }

    /**
     * 更新此玩家的杀敌数
     *
     * @param newKill 新的杀敌数
     */
    @Override
    public void updateKill(int newKill) {

        Score kill = objective.getScore("> 杀敌数: " + kitPvPPlayer.getKill());
        Score newKillScore = objective.getScore("> 杀敌数: " + newKill);
        newKillScore.setScore(7);

        if(kill != null && !kill.getEntry().equalsIgnoreCase(newKillScore.getEntry())) {

            scoreboard.resetScores(kill.getEntry());
        }
        double lastKD = kitPvPPlayer.getKD();

        kitPvPPlayer.setKill(newKill);

        updateKD(lastKD);
    }

    /**
     * 更新此玩家的死亡数
     *
     * @param newDeath 新的死亡数
     */
    @Override
    public void updateDeath(int newDeath) {

        Score death = objective.getScore("> 死亡数: " + kitPvPPlayer.getDeath());
        Score newDeathScore = objective.getScore("> 死亡数: " + newDeath);
        newDeathScore.setScore(6);

        if(death != null && !death.getEntry().equalsIgnoreCase(newDeathScore.getEntry())) {

            scoreboard.resetScores(death.getEntry());
        }
        double lastKD = kitPvPPlayer.getKD();

        kitPvPPlayer.setDeath(newDeath);

        updateKD(lastKD);
    }

    private void updateKD(double lastKD) {

        Score kd = objective.getScore("> K/D比: " + lastKD);

        Score newKd = objective.getScore("> K/D比: " + kitPvPPlayer.getKD());
        newKd.setScore(4);

        if(kd != null && !kd.getEntry().equalsIgnoreCase(newKd.getEntry())) {

            scoreboard.resetScores(kd.getEntry());
        }
    }
}
