package de.winedev.servercore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class EVENT_PlayerMove implements Listener {

    @EventHandler
    public void hasPermToPlace(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("servercore.*")|| p.hasPermission("*") || p.hasPermission("servercore.move")){
            e.setCancelled(false);
        }else{
            p.sendMessage("Du hast keine Berechtigung dich zu bewegen");
            e.setCancelled(true);
        }
    }
}
