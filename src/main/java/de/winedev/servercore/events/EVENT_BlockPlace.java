package de.winedev.servercore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class EVENT_BlockPlace implements Listener {

    @EventHandler
    public void hasPermToPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("servercore.*")|| p.hasPermission("*") || p.hasPermission("servercore.build")){
            e.setCancelled(false);
        }else{
            p.sendMessage("Du hast keine Berechtigung hier etwas zu platzierern");
            e.setCancelled(true);
        }
    }
}
