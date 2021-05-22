package de.winedev.servercore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class EVENT_PlayerInteractWithEntity implements Listener {

  @EventHandler
  public static void interactWithEntity(PlayerInteractAtEntityEvent e) {
    Player p = e.getPlayer();
    if (!p.hasPermission("servercore.interactWithEntity")) {
      e.setCancelled(true);
      p.sendMessage("Du darfst nicht mit Entitys interagieren");
    }
  }
}
