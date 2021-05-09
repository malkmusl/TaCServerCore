package de.winedev.servercore.commands;

import de.winedev.servercore.Files;
import de.winedev.servercore.ServerCore;
import de.winedev.servercore.framework.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.List;

public class CMD_perm implements CommandExecutor, Files {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if (p.hasPermission("servercore.perm") ||p.hasPermission("servercore.*") || p.isOp()){
                //perm <add/remove> <user/group> <target> <perm>
                if(args.length == 4) {
                    if (args[0].equalsIgnoreCase("add")) {
                        if (args[1].equalsIgnoreCase("user")) {
                            FileManager.load(users);
                            String Starget = args[2];
                            Player target = Bukkit.getPlayer(Starget);
                            if(Bukkit.getOnlinePlayers().contains(target)){
                                String PlayerPath = "users."+target.getUniqueId()+".";
                                String PlayerNamePath = PlayerPath+"name";
                                String PlayerName = users.getConfig().get(PlayerNamePath).toString();
                                // add Player Specific Permissions to Player
                                List userPerm = users.getConfig().getList(PlayerPath + "permissions");
                                if(!target.hasPermission(args[3].toString())){
                                    if(!userPerm.contains(args[3])) {
                                        userPerm.add(args[3].toString());
                                        target.addAttachment(ServerCore.pl, args[3].toString(), true);
                                        users.getConfig().set(PlayerPath + "permissions", userPerm);
                                        FileManager.save(users);
                                        p.sendMessage("§3Du hast dem Spieler » §e"+ PlayerName +"§3 die Berechtigung » §e"+args[3]+"§3 gegeben");
                                    }else {
                                        p.sendMessage("§cDer Spieler » §e"+ PlayerName +" §chat die Berechtigung » §e"+args[3]+"§c bereits");
                                    }
                                }

                            }else{
                                p.sendMessage("Der Spieler "+target+" ist offline");
                            }
                        }
                        return true;
                    }
                    else if (args[0].equalsIgnoreCase("remove")) {
                        if (args[1].equalsIgnoreCase("user")) {
                            FileManager.load(users);
                            String Starget = args[2];
                            Player target = Bukkit.getPlayer(Starget);
                            if(Bukkit.getOnlinePlayers().contains(target)){
                                String PlayerPath = "users."+target.getUniqueId()+".";
                                String PlayerNamePath = PlayerPath+"name";
                                String PlayerName = users.getConfig().get(PlayerNamePath).toString();
                                // add Player Specific Permissions to Player
                                List userPerm = users.getConfig().getList(PlayerPath + "permissions");
                                if(target.hasPermission(args[3].toString())){
                                    if(userPerm.contains(args[3])) {
                                        userPerm.remove(args[3].toString());
                                        target.addAttachment(ServerCore.pl, args[3].toString(), false);
                                        users.getConfig().set(PlayerPath + "permissions", userPerm);
                                        FileManager.save(users);
                                        p.sendMessage("§3Du hast dem Spieler » §e"+ PlayerName +"§3 die Berechtigung » §e"+args[3]+"§3 entzogen");
                                    }else {
                                        p.sendMessage("§cDer Spieler » §e"+ PlayerName +" §chat die Berechtigung » §e"+args[3]+"§c nicht");
                                    }
                                }

                            }else{
                                p.sendMessage("Der Spieler "+target+" ist offline");
                            }
                        }
                        return true;
                    }
                    else{
                        p.sendMessage("/perm <add/remove> <user/group> <target> <perm>");
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
