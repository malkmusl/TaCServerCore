package de.winedev.servercore.events;

import de.winedev.servercore.Files;
import de.winedev.servercore.ServerCore;
import de.winedev.servercore.framework.FileManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

;

public class EVENT_PlayerJoin implements Listener, Files {

    @EventHandler
    public void addToFile(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String PlayerPath = "users."+p.getUniqueId()+".";
        String GroupPath = PlayerPath+"group";
        String AutoNickPath = PlayerPath+"autonick";
        String PlayerNamePath = PlayerPath+"name";

        FileManager.load(users);
        FileManager.load(groups);
        // add Group Specific Permissions to Player
        if(users.getConfig().contains(PlayerPath)){
            p.sendMessage("§6Willkommen zurück!");
            String usergroup = users.getConfig().get(GroupPath).toString();
            p.sendMessage("");
            p.sendMessage("§7[DEBUG]");
            p.sendMessage("§7Group: "+usergroup);
            if(groups.getConfig().contains(usergroup)){
                List groupsList = groups.getConfig().getList(usergroup+".permissions");
                for(int i = 0; i < groupsList.size(); i++) {
                    String perm = (groupsList.get(i)).toString();
                    p.addAttachment(ServerCore.pl, perm, true);
                }
                p.sendMessage("§7GroupPerms: "+groupsList.toString());
            }else{
                p.sendMessage("§4[ERROR] die Gruppe existiert nicht");
            }
            // add Player Specific Permissions to Player
            List userPerm = users.getConfig().getList(PlayerPath + "permissions");
            for(int i = 0; i < userPerm.size(); i++) {
                String perm = (userPerm.get(i)).toString();
                p.addAttachment(ServerCore.pl, perm, true);
            }
            p.sendMessage("§7UserPerms: "+userPerm.toString());
            p.sendMessage("");
        }else{
            p.sendMessage("Willkommen auf dem Server");
            users.getConfig().set(PlayerPath + "name", p.getName());
            users.getConfig().set(PlayerPath + "group", "default");
            ArrayList defaultperms = new ArrayList();
            defaultperms.add("servercore.move");
            defaultperms.add("servercore.chat");
            users.getConfig().set(PlayerPath + "permissions", defaultperms);
            try {
                users.getConfig().save(users.getFile());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        String usergroup = users.getConfig().get(GroupPath).toString();
        if(users.getConfig().getBoolean(AutoNickPath)){
            String prefix = groups.getConfig().get("default.prefix").toString().replace("&","§")+" ";
            String suffix = " "+groups.getConfig().get("default.suffix").toString().replace("&","§");

            e.setJoinMessage("§7[+] "+users.getConfig().get(PlayerNamePath));

            p.setPlayerListName(prefix+users.getConfig().get(PlayerNamePath)+suffix+"§r");
            p.setDisplayName(prefix+users.getConfig().get(PlayerNamePath)+suffix+"§r");

        }else{
            String prefix = groups.getConfig().get(usergroup+".prefix").toString().replace("&","§")+" ";
            String suffix = " "+groups.getConfig().get(usergroup+".suffix").toString().replace("&","§");

            e.setJoinMessage("§7[+] "+p.getName());

            p.setPlayerListName(prefix+p.getName()+suffix+"§r");
            p.setDisplayName(prefix+p.getName()+suffix+"§r");
        }
    }

}
