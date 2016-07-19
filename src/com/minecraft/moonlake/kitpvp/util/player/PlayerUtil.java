package com.minecraft.moonlake.kitpvp.util.player;

import com.minecraft.moonlake.api.player.AbstractPlayer;
import com.minecraft.moonlake.kitpvp.api.occupa.Occupa;
import com.minecraft.moonlake.kitpvp.api.occupa.OccupaGUI;
import com.minecraft.moonlake.kitpvp.api.occupa.OccupaType;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.combo.SkillCombo;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.api.player.bossbar.KitPvPBossbar;
import com.minecraft.moonlake.kitpvp.api.player.scoreboard.KitPvPScoreboard;
import com.minecraft.moonlake.kitpvp.bossbar.KitPvPBossbarUtil;
import com.minecraft.moonlake.kitpvp.language.l18n;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import com.minecraft.moonlake.kitpvp.rank.KitPvPRank;
import com.minecraft.moonlake.kitpvp.scoreboard.KitPvPScoreboardUtil;
import com.minecraft.moonlake.kitpvp.util.skill.SkillComboUtil;
import com.minecraft.moonlake.util.Util;

/**
 * Created by MoonLake on 2016/7/9.
 */
public class PlayerUtil extends AbstractPlayer implements KitPvPPlayer {

    private Occupa occupa;
    private OccupaGUI occupaGUI;
    private OccupaType occupaType;
    private SkillCombo skillCombo;
    private KitPvPBossbar bossbar;
    private KitPvPScoreboard scoreboard;

    private int kill;
    private int death;
    private KitPvPRank rank;

    public PlayerUtil(String name) {

        super(name);

        this.occupaGUI = new OccupaGUI(this);
        this.bossbar = new KitPvPBossbarUtil(this);
        this.skillCombo = new SkillComboUtil(this, 3);
        this.scoreboard = new KitPvPScoreboardUtil(this);
    }

    /**
     * 获取此玩家的职业
     *
     * @return 职业对象
     */
    @Override
    public Occupa getOccupa() {

        return occupa;
    }

    /**
     * 获取此玩家的职业 GUI 对象
     *
     * @return 职业 GUI 对象
     */
    @Override
    public OccupaGUI getOccupaGUI() {

        return occupaGUI;
    }

    /**
     * 获取此玩家的职业类型
     *
     * @return 职业类型
     */
    @Override
    public OccupaType getOccupaType() {

        return occupaType;
    }

    /**
     * 设置此玩家的职业
     *
     * @param occupa 职业对象
     */
    @Override
    public void setOccupa(Occupa occupa) {

        this.occupa = occupa;
        this.occupaType = occupa.getType();
    }

    /**
     * 获取此玩家的技能组合对象
     *
     * @return 技能组合对象
     */
    @Override
    public SkillCombo getSkillCombo() {

        return skillCombo;
    }

    /**
     * 获取此玩家的职业战争计分板对象
     *
     * @return 计分板对象
     */
    @Override
    public KitPvPScoreboard getKitPvPScoreboard() {

        return scoreboard;
    }

    /**
     * 获取此玩家的击杀数
     *
     * @return 击杀数
     */
    @Override
    public int getKill() {

        return kill;
    }

    /**
     * 设置此玩家的击杀数
     *
     * @param newKill 新的击杀数
     */
    @Override
    public void setKill(int newKill) {

        this.kill = newKill;
    }

    /**
     * 获取此玩家的死亡数
     *
     * @return 死亡数
     */
    @Override
    public int getDeath() {

        return death;
    }

    /**
     * 设置此玩家的死亡数
     *
     * @param newDeath 新的死亡数
     */
    @Override
    public void setDeath(int newDeath) {

        this.death = newDeath;
    }

    /**
     * 获取此玩家的 KD 比值
     *
     * @return KD 比值
     */
    @Override
    public double getKD() {

        if(kill > 0 && death > 0) {

            double kd = (double)kill / (double)death;

            return Util.rounding(kd, 2);
        }
        return 0.0d;
    }

    /**
     * 获取此玩家的 Rank 等级
     *
     * @return Rank
     */
    @Override
    public KitPvPRank getRank() {

        return rank;
    }

    /**
     * 设置此玩家的 Rank 等级
     *
     * @param kitPvPRank Rank
     */
    @Override
    public void setRank(KitPvPRank kitPvPRank) {

        this.rank = kitPvPRank;
    }

    /**
     * 获取此玩家的血条对象
     *
     * @return 血条对象
     */
    @Override
    public KitPvPBossbar getKitPvPBossbar() {

        return bossbar;
    }

    /**
     * 给此玩家发送语言文件指定 键 的值
     *
     * @param key 键
     */
    @Override
    public void l18n(String key) {

        send(l18n.$(key));
    }

    /**
     * 给此玩家发送语言文件指定 键 的值
     *
     * @param key  键
     * @param args 参数
     */
    @Override
    public void l18n(String key, Object... args) {

        send(l18n.$(key, args));
    }

    /**
     * 获取此玩家的击杀者玩家对象
     *
     * @return 击杀者玩家对象 没有则返回 null
     */
    @Override
    public KitPvPPlayer getKiller() {

        return getBukkitPlayer().getKiller() != null ? AccountManager.get(getBukkitPlayer().getKiller().getName()) : null;
    }
}
