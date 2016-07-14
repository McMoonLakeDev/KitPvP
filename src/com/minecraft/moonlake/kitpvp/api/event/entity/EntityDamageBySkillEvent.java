package com.minecraft.moonlake.kitpvp.api.event.entity;

import com.minecraft.moonlake.kitpvp.api.event.KitPvPEvent;
import com.minecraft.moonlake.kitpvp.api.occupa.skill.Skill;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Created by MoonLake on 2016/7/13.
 */
public class EntityDamageBySkillEvent extends KitPvPEvent implements Cancellable {

    private final static HandlerList handlers = new HandlerList();
    private final Entity entity;
    private final Skill skill;
    private final KitPvPPlayer owner;
    private boolean cancel = false;

    public EntityDamageBySkillEvent(Entity entity, Skill skill, KitPvPPlayer owner) {

        this.entity = entity;
        this.skill = skill;
        this.owner = owner;
    }

    public Entity getEntity() {

        return entity;
    }

    public boolean isPlayer() {

        return getEntity() instanceof Player;
    }

    public Skill getSkill() {

        return skill;
    }

    public KitPvPPlayer getOwner() {

        return owner;
    }

    @Override
    public boolean isCancelled() {

        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {

        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {

        return handlers;
    }

    public static HandlerList getHandlerList() {

        return handlers;
    }
}
