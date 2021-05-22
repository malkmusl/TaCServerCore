package de.winedev.servercore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class EVENT_PlayerInteract implements Listener {

  @EventHandler
  public static void onInteract(PlayerInteractEvent e) {
    Player p = e.getPlayer();
    if (!p.hasPermission("servercore.interact")) {
      e.setCancelled(true);
    }
  }
}
