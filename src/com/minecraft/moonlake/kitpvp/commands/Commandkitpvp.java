package com.minecraft.moonlake.kitpvp.commands;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.occupa.OccupaType;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.language.l18n;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import com.minecraft.moonlake.kitpvp.manager.DataManager;
import com.minecraft.moonlake.kitpvp.manager.OccupaManager;
import com.minecraft.moonlake.kitpvp.manager.WorldEditManager;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by MoonLake on 2016/7/10.
 */
public class Commandkitpvp implements CommandExecutor {

    private final KitPvP main;

    public Commandkitpvp(KitPvP main) {

        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("kitpvp")) {

            if(!(sender instanceof Player)) {

                sender.sendMessage(l18n.$("command.console.notRun"));
                return true;
            }
            KitPvPPlayer kitPvPPlayer = AccountManager.get(sender.getName());

            if(kitPvPPlayer == null) {

                kitPvPPlayer = AccountManager.create(sender.getName());

                if(kitPvPPlayer == null) {

                    sender.sendMessage(l18n.$("command.player.exception"));
                    return true;
                }
            }
            if(args.length == 0) {

                kitPvPPlayer.l18n("command.help");
                return true;
            }
            else if(args.length == 1) {

                if(args[0].equalsIgnoreCase("help")) {

                    kitpvpHelp(kitPvPPlayer);
                }
                else if(args[0].equalsIgnoreCase("opengui")) {

                    openGUI(kitPvPPlayer);
                }
                else if(args[0].equalsIgnoreCase("setlobby")) {

                    setLobby(kitPvPPlayer);
                }
                else if(args[0].equalsIgnoreCase("setlobbypoint")) {

                    setLobbyPoint(kitPvPPlayer);
                }
                return true;
            }
            else if(args.length == 2) {

                if(args[0].equalsIgnoreCase("occupa")) {

                    occupa(kitPvPPlayer, args[1], null);
                }
                return true;
            }
            else if(args.length == 3) {

                if(args[0].equalsIgnoreCase("occupa")) {

                    occupa(kitPvPPlayer, args[1], args[2]);
                }
                return true;
            }
        }
        return false;
    }

    private void kitpvpHelp(KitPvPPlayer kitPvPPlayer) {

        String[] helps = {

                "/kitpvp help - " + l18n.$("command.kitPvP.help"),
                "/kitpvp opengui - " + l18n.$("command.kitPvP.help.openGUI"),
                "/kitpvp setlobby - " + l18n.$("command.kitPvP.help.setLobby"),
                "/kitpvp setlobbypoint - " + l18n.$("command.kitPvP.help.setLobbyPoint"),
                "/kitpvp occupa <O> [T] - " + l18n.$("command.kitPvP.help.occupa"),
        };
        kitPvPPlayer.send(helps);
    }

    private void setLobby(KitPvPPlayer kitPvPPlayer) {

        if(!kitPvPPlayer.hasPermission("moonlake.kitpvp.setlobby")) {

            kitPvPPlayer.l18n("command.permission.notHave");
            return;
        }
        Selection selection = WorldEditManager.getSelection(kitPvPPlayer);

        if(selection == null) {

            kitPvPPlayer.l18n("worldEdit.selection.none");
            return;
        }
        DataManager.setKitPvPLobbyRegion(selection);

        kitPvPPlayer.l18n("command.kitPvP.setLobby");
    }

    private void setLobbyPoint(KitPvPPlayer kitPvPPlayer) {

        if(!kitPvPPlayer.hasPermission("moonlake.kitpvp.setlobbypoint")) {

            kitPvPPlayer.l18n("command.permission.notHave");
            return;
        }
        if(!DataManager.isSetLobby()) {

            kitPvPPlayer.l18n("command.kitPvP.setLobbyPoint.notSetLobby");
            return;
        }
        Location location = kitPvPPlayer.getLocation();

        if(!DataManager.contains(location)) {

            kitPvPPlayer.l18n("command.kitPvP.setLobbyPoint.notLocation");
            return;
        }
        DataManager.setKitPvPLobbyPoint(location);

        kitPvPPlayer.l18n("command.kitPvP.setLobbyPoint");
    }

    private void occupa(KitPvPPlayer kitPvPPlayer, String type, String target) {

        if(!kitPvPPlayer.hasPermission("moonlake.kitpvp.occupa")) {

            kitPvPPlayer.l18n("command.permission.notHave");
            return;
        }
        OccupaType occupaType = OccupaType.fromType(type);

        if(occupaType == null) {

            kitPvPPlayer.l18n("command.kitPvP.occupa.notType", type);
            return;
        }
        if(target != null) {

            KitPvPPlayer targetPlayer = AccountManager.get(target);

            if(targetPlayer == null) {

                kitPvPPlayer.l18n("command.target.notOnline", target);
                return;
            }
            kitPvPPlayer = targetPlayer;
        }
        OccupaManager.initOccupaPlayer(kitPvPPlayer, occupaType.newInstance());

        kitPvPPlayer.l18n("command.kitPvP.occupa", kitPvPPlayer.getName(), occupaType.getType());
    }

    private void openGUI(KitPvPPlayer kitPvPPlayer) {

        if(!kitPvPPlayer.hasPermission("moonlake.kitpvp.opengui")) {

            kitPvPPlayer.l18n("command.permission.notHave");
            return;
        }
        kitPvPPlayer.getOccupaGUI().openGUI();
        kitPvPPlayer.playSound(Sound.ENTITY_ARROW_HIT_PLAYER, 10f, 1f);
    }
}
