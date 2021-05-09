package de.winedev.servercore.commands;

import de.winedev.servercore.Files;
import de.winedev.servercore.ServerCore;
import de.winedev.servercore.framework.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;


public class CMD_perm implements CommandExecutor, Files {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 4) {
            FileManager.load(users);
            String Starget = args[2];
            Player target = Bukkit.getPlayerExact(Starget);
            String PlayerPath = "users." + target.getUniqueId() + ".";
            String PlayerNamePath = PlayerPath + "name";
            String PlayerName = users.getConfig().get(PlayerNamePath).toString();


            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("servercore.perm") || p.hasPermission("servercore.*") || p.isOp()) {
                    if (args[0].equalsIgnoreCase("add")) {
                        if (args[1].equalsIgnoreCase("user")) {
                            if (Bukkit.getOnlinePlayers().contains(target)) {
                                // add Player Specific Permissions to Player
                                List userPerm = users.getConfig().getList(PlayerPath + "permissions");
                                if (!target.hasPermission(args[3].toString())) {
                                    if (!userPerm.contains(args[3])) {
                                        userPerm.add(args[3].toString());
                                        target.addAttachment(ServerCore.pl, args[3].toString(), true);
                                        users.getConfig().set(PlayerPath + "permissions", userPerm);
                                        FileManager.save(users);
                                        p.sendMessage("§3Du hast dem Spieler » §e" + PlayerName + "§3 die Berechtigung » §e" + args[3] + "§3 gegeben");
                                    } else {
                                        p.sendMessage("§cDer Spieler » §e" + PlayerName + " §chat die Berechtigung » §e" + args[3] + "§c bereits");
                                    }
                                } else {
                                    p.sendMessage("§cDer Spieler » §e" + PlayerName + " §chat die Berechtigung » §e" + args[3] + "§c bereits");
                                }

                            } else {
                                p.sendMessage("§cDer Spieler " + target + " ist offline");
                            }
                        } else {
                            cmdaddpermgroup(p);
                        }
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        if (args[1].equalsIgnoreCase("user")) {
                            if (Bukkit.getOnlinePlayers().contains(target)) {
                                // add Player Specific Permissions to Player
                                List userPerm = users.getConfig().getList(PlayerPath + "permissions");
                                if (target.hasPermission(args[3].toString())) {
                                    if (userPerm.contains(args[3])) {
                                        userPerm.remove(args[3].toString());
                                        target.addAttachment(ServerCore.pl, args[3].toString(), true);
                                        users.getConfig().set(PlayerPath + "permissions", userPerm);
                                        FileManager.save(users);
                                        p.sendMessage("§3Du hast dem Spieler » §e" + PlayerName + "§3 die Berechtigung » §e" + args[3] + "§3 entfernt");
                                    } else {
                                        p.sendMessage("§cDer Spieler » §e" + PlayerName + " §chat die Berechtigung » §e" + args[3] + "§c nicht");
                                    }
                                } else {
                                    p.sendMessage("§cDer Spieler » §e" + PlayerName + " §chat die Berechtigung » §e" + args[3] + "§c nicht");
                                }

                            } else {
                                p.sendMessage("§cDer Spieler " + target + " ist offline");
                            }
                        } else {
                            cmdremovepermgroup(p);
                        }
                    } else {
                        p.sendMessage("§cUsage: /perm <add/remove> <user/group> <target> <perm>");
                    }
                } else {
                    p.sendMessage("§cDu hast keine Berechtigung diesen Befehl aus zuführen");
                }
            } else {// COnsole COmmand


                if (args[0].equalsIgnoreCase("add")) {
                    if (args[1].equalsIgnoreCase("user")) {
                        if (Bukkit.getOnlinePlayers().contains(target)) {
                            // add Player Specific Permissions to Player
                            List userPerm = users.getConfig().getList(PlayerPath + "permissions");
                            if (!target.hasPermission(args[3].toString())) {
                                if (!userPerm.contains(args[3])) {
                                    userPerm.add(args[3].toString());
                                    target.addAttachment(ServerCore.pl, args[3].toString(), true);
                                    users.getConfig().set(PlayerPath + "permissions", userPerm);
                                    FileManager.save(users);
                                    Bukkit.getConsoleSender().sendMessage("§3Du hast dem Spieler » §e" + PlayerName + "§3 die Berechtigung » §e" + args[3] + "§3 gegeben");
                                } else {
                                    Bukkit.getConsoleSender().sendMessage("§cDer Spieler » §e" + PlayerName + " §chat die Berechtigung » §e" + args[3] + "§c bereits");
                                }
                            } else {
                                Bukkit.getConsoleSender().sendMessage("§cDer Spieler » §e" + PlayerName + " §chat die Berechtigung » §e" + args[3] + "§c bereits");
                            }

                        } else {
                            Bukkit.getConsoleSender().sendMessage("§cDer Spieler " + target + " ist offline");
                        }
                    } else {
                        cmdaddpermgroup(Bukkit.getConsoleSender());
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (args[1].equalsIgnoreCase("user")) {
                        if (Bukkit.getOnlinePlayers().contains(target)) {
                            // add Player Specific Permissions to Player
                            List userPerm = users.getConfig().getList(PlayerPath + "permissions");
                            if (target.hasPermission(args[3].toString())) {
                                if (userPerm.contains(args[3])) {
                                    userPerm.remove(args[3].toString());
                                    target.addAttachment(ServerCore.pl, args[3].toString(), true);
                                    users.getConfig().set(PlayerPath + "permissions", userPerm);
                                    FileManager.save(users);
                                    Bukkit.getConsoleSender().sendMessage("§3Du hast dem Spieler » §e" + PlayerName + "§3 die Berechtigung » §e" + args[3] + "§3 gegeben");
                                } else {
                                    Bukkit.getConsoleSender().sendMessage("§cDer Spieler » §e" + PlayerName + " §chat die Berechtigung » §e" + args[3] + "§c bereits");
                                }
                            } else {
                                Bukkit.getConsoleSender().sendMessage("§cDer Spieler » §e" + PlayerName + " §chat die Berechtigung » §e" + args[3] + "§c bereits");
                            }

                        } else {
                            Bukkit.getConsoleSender().sendMessage("§cDer Spieler " + target + " ist offline");
                        }
                    } else {
                        cmdremovepermgroup(Bukkit.getConsoleSender());
                    }
                } else {
                    Bukkit.getConsoleSender().sendMessage("§cUsage: /perm <add/remove> <user/group> <target> <perm>");
                }
            }
        } else if(args.length == 3){
            if (args[0].equalsIgnoreCase("list")) {
                sender.sendMessage("§c8Work in Progress");
            }else{
                sender.sendMessage("§cUsage: /perm <add/remove/list> <user/group> <target> <perm>");
            }
        } else {
            // args != 4 than give this message
            sender.sendMessage("§cUsage: /perm <add/remove/list> <user/group> <target> <perm>");
        }
        return true;
    }


    private void cmdaddpermgroup(CommandSender commandSender) {
    }

    private void cmdremovepermgroup(CommandSender commandSender) {
    }
}
