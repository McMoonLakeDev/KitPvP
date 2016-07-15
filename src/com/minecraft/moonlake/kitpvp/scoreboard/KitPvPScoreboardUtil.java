package com.minecraft.moonlake.kitpvp.scoreboard;

import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.api.player.scoreboard.KitPvPScoreboard;
import com.minecraft.moonlake.kitpvp.rank.KitPvPRank;
import com.minecraft.moonlake.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

/**
 * Created by MoonLake on 2016/7/11.
 */
public class KitPvPScoreboardUtil implements KitPvPScoreboard {

    private final KitPvPPlayer kitPvPPlayer;
    private final Scoreboard scoreboard;
    private final Objective objective;
    private final Team team;

    public KitPvPScoreboardUtil(KitPvPPlayer kitPvPPlayer) {

        this.kitPvPPlayer = kitPvPPlayer;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.getObjective("kitpvp") == null ? scoreboard.registerNewObjective("kitpvp", "dummy") : scoreboard.getObjective("kitpvp");
        this.team = scoreboard.getTeam("kitpvp") == null ? scoreboard.registerNewTeam("kitpvp") : scoreboard.getTeam("kitpvp");
    }

    /**
     * 注册此玩家的职业战争计分板
     */
    @Override
    public void register() {

        objective.setDisplayName(Util.color("&6MoonLake 职业战争"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("    ").setScore(10);
        objective.getScore(Util.color("&b> &eRank: &b" + kitPvPPlayer.getRank().getRankName())).setScore(9);
        objective.getScore("   ").setScore(8);
        objective.getScore(Util.color("&b> &e杀敌数: &a" + kitPvPPlayer.getKill())).setScore(7);
        objective.getScore(Util.color("&b> &e死亡数: &c" + kitPvPPlayer.getDeath())).setScore(6);
        objective.getScore("  ").setScore(5);
        objective.getScore(Util.color("&b> &eK/D比: &d" + kitPvPPlayer.getKD())).setScore(4);
        objective.getScore(" ").setScore(3);
        objective.getScore(Util.color("&e官方Q群: &a377607025")).setScore(2);
        objective.getScore("").setScore(1);
        objective.getScore(Util.color("   &ewww.mcyszh.com")).setScore(0);

        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM);
        team.addEntry(kitPvPPlayer.getName());

        kitPvPPlayer.getBukkitPlayer().setScoreboard(scoreboard);
    }

    /**
     * 卸载此玩家的职业战争计分板
     */
    @Override
    public void unregister() {

        team.unregister();
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

        Score kill = objective.getScore(Util.color("&b> &e杀敌数: &a" + kitPvPPlayer.getKill()));
        Score newKillScore = objective.getScore(Util.color("&b> &e杀敌数: &a" + newKill));
        newKillScore.setScore(7);

        if(kill != null && !kill.getEntry().equalsIgnoreCase(newKillScore.getEntry())) {

            scoreboard.resetScores(kill.getEntry());
        }
        double lastKD = kitPvPPlayer.getKD();

        kitPvPPlayer.setKill(newKill);

        updateKD(lastKD);

        KitPvPRank updateRank = KitPvPRank.getPlayerRank(newKill);

        if(kitPvPPlayer.getRank() != updateRank) {

            updateRank(updateRank);

            kitPvPPlayer.setRank(updateRank);
        }
    }

    /**
     * 更新此玩家的死亡数
     *
     * @param newDeath 新的死亡数
     */
    @Override
    public void updateDeath(int newDeath) {

        Score death = objective.getScore(Util.color("&b> &e死亡数: &c" + kitPvPPlayer.getDeath()));
        Score newDeathScore = objective.getScore(Util.color("&b> &e死亡数: &c" + newDeath));
        newDeathScore.setScore(6);

        if(death != null && !death.getEntry().equalsIgnoreCase(newDeathScore.getEntry())) {

            scoreboard.resetScores(death.getEntry());
        }
        double lastKD = kitPvPPlayer.getKD();

        kitPvPPlayer.setDeath(newDeath);

        updateKD(lastKD);
    }

    private void updateKD(double lastKD) {

        Score kd = objective.getScore(Util.color("&b> &eK/D比: &d" + lastKD));

        Score newKd = objective.getScore(Util.color("&b> &eK/D比: &d" + kitPvPPlayer.getKD()));
        newKd.setScore(4);

        if(kd != null && !kd.getEntry().equalsIgnoreCase(newKd.getEntry())) {

            scoreboard.resetScores(kd.getEntry());
        }
    }

    private void updateRank(KitPvPRank newRank) {

        Score rank = objective.getScore(Util.color("&b> &eRank: &b" + kitPvPPlayer.getRank().getRankName()));

        Score newRankScore = objective.getScore(Util.color("&b> &eRank: &b" + newRank.getRankName()));
        newRankScore.setScore(9);

        if(rank != null && !rank.getEntry().equalsIgnoreCase(newRankScore.getEntry())) {

            scoreboard.resetScores(rank.getEntry());
        }
    }
}
