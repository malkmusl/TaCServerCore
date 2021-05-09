package de.winedev.servercore.events;

import de.winedev.servercore.Files;
import de.winedev.servercore.ServerCore;
import de.winedev.servercore.framework.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EVENT_PlayerChat implements Listener, Files{

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        FileManager.load(users);
        FileManager.load(groups);
        Player p = e.getPlayer();
        String PlayerPath = "users."+p.getUniqueId()+".";
        String PlayerNamePath = PlayerPath+"name";
        String PlayerName = users.getConfig().get(PlayerNamePath).toString();
        String GroupPath = PlayerPath+"group";
        String AutoNickPath = PlayerPath+"autonick";

        if(p.hasPermission("servercore.chat") || p.hasPermission("servercore.*") || p.hasPermission("*") || p.isOp()){
            if(users.getConfig().contains(PlayerPath)){
                String usergroup = users.getConfig().get(GroupPath).toString();
                if(groups.getConfig().contains(usergroup)){

                    if(users.getConfig().getBoolean(AutoNickPath)){
                        String prefix = groups.getConfig().get("default.prefix").toString().replace("&","§")+" ";
                        String suffix = groups.getConfig().get("default.suffix").toString().replace("&","§")+" ";
                        String chatColor = groups.getConfig().get("default.chatcolor").toString().replace("&","§")+" ";
                        e.setFormat(prefix+PlayerName+suffix+"»"+chatColor+e.getMessage());
                    }else{
                        String prefix = groups.getConfig().get(usergroup+".prefix").toString().replace("&","§")+" ";
                        String suffix = groups.getConfig().get(usergroup+".suffix").toString().replace("&","§")+" ";
                        String chatColor = groups.getConfig().get(usergroup+".chatcolor").toString().replace("&","§")+" ";
                        e.setFormat(prefix+PlayerName+suffix+"»"+chatColor+e.getMessage());
                    }
                }else{
                    p.sendMessage("§4[ERROR] die Gruppe existiert nicht wende dich bitte an einen Admin oder Owner");
                    String prefix = groups.getConfig().get("default.prefix").toString().replace("&","§")+" ";
                    String suffix = groups.getConfig().get("default.suffix").toString().replace("&","§")+" ";
                    String chatColor = groups.getConfig().get("default.chatcolor").toString().replace("&","§")+" ";
                    e.setFormat(prefix+PlayerName+suffix+"»"+chatColor+e.getMessage());
                }
            }else{
                p.kickPlayer("Your PlayerData is currupted");
            }
        }else{
            p.sendMessage("§cDu hast keine Berechtigung den Chat zu nutzen!");
            e.setCancelled(true);
        }
    }
}
