package com.minecraft.moonlake.kitpvp.rank;

/**
 * Created by MoonLake on 2016/7/14.
 */
public enum KitPvPRank {

    LEVEL_0(0, "萌新"),

    LEVEL_1(150, "小试牛刀"),

    LEVEL_2(780, "战神附身"),

    LEVEL_3(1455, "神魔让道"),

    LEVEL_4(2600, "毁天灭地"),

    LEVEL_5(3400, "天下自尊"),

    LEVEL_6(6000, "嘴强王者"),

    LEVEL_MAX(10000, "YouUpSky"),
    ;

    private int needKill;
    private String rankName;

    KitPvPRank(int needKill, String rankName) {

        this.needKill = needKill;
        this.rankName = rankName;
    }

    public int getNeedKill() {

        return needKill;
    }

    public String getRankName() {

        return rankName;
    }

    public static KitPvPRank getPlayerRank(int kill) {

        KitPvPRank kitPvPRank = LEVEL_0;

        for(KitPvPRank rank : values()) {

            if(kill >= rank.getNeedKill()) {

                kitPvPRank = rank;
                continue;
            }
            break;
        }
        return kitPvPRank;
    }
}
