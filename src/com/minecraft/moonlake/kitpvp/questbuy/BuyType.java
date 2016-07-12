package com.minecraft.moonlake.kitpvp.questbuy;

import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.EconomyManager;

/**
 * Created by MoonLake on 2016/7/12.
 */
public class BuyType {

    private Type type;
    private int value;

    public BuyType(Type type, int value) {

        this.type = type;
        this.value = value;
    }

    /**
     * 获取购买的类型
     *
     * @return 类型
     */
    public Type getType() {

        return type;
    }

    /**
     * 获取购买的值
     *
     * @return
     */
    public int getValue() {

        return value;
    }

    /**
     * 检测指定玩家是否足够购买需求
     *
     * @param kitPvPPlayer 玩家
     * @return true 则足够 else 不足够
     */
    public boolean checkPlayer(KitPvPPlayer kitPvPPlayer) {

        if(type == Type.MONEY) {

            return EconomyManager.getMoney(kitPvPPlayer.getName()) >= value;
        }
        else if(type == Type.POINT) {

            return EconomyManager.getPoint(kitPvPPlayer.getName()) >= value;
        }
        return false;
    }

    /**
     * 将足够购买需求的值拿走玩家的经济数据
     *
     * @param kitPvPPlayer 玩家
     */
    public void takeBuyValue(KitPvPPlayer kitPvPPlayer) {

        if(type == Type.MONEY) {

            EconomyManager.takeMoney(kitPvPPlayer.getName(), value);
        }
        else if(type == Type.POINT) {

            EconomyManager.takePoint(kitPvPPlayer.getName(), value);
        }
    }

    public void notHave(KitPvPPlayer kitPvPPlayer) {

        kitPvPPlayer.l18n("player.questBuy.notHave", type.getName());
    }

    public enum Type {

        /**
         * 类型: 金币
         */
        MONEY("Money", "金币"),
        /**
         * 类型: 点券
         */
        POINT("Point", "点券"),;

        private String type;
        private String name;

        Type(String type, String name) {

            this.type = type;
            this.name = name;
        }

        public String getType() {

            return type;
        }

        public String getName() {

            return name;
        }
    }
}
