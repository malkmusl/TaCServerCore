package de.winedev.servercore.events;

import de.winedev.servercore.Files;
import de.winedev.servercore.ServerCore;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

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
        loadUsers();
        loadGroups();

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
    }


    private void loadUsers() {
        try {
            users.getConfig().load(users.getFile());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InvalidConfigurationException invalidConfigurationException) {
            invalidConfigurationException.printStackTrace();
        }
    }

    public static void loadGroups(){
        try {
            groups.getConfig().load(groups.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
