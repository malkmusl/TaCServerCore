package de.winedev.servercore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EVENT_PlayerInteractEntity implements Listener {

  @EventHandler
  public static void onInteract(PlayerInteractEntityEvent e) {
    Player p = e.getPlayer();
    if (!p.hasPermission("servercore.interactEntity")) {
      e.setCancelled(true);
    }
  }
}
