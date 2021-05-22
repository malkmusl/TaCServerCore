package de.winedev.servercore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EVENT_PlayerDamageByEntiy implements Listener {

  @EventHandler
  public static void getDamageByEntiy(EntityDamageByEntityEvent e) {
    if (e.getEntity() instanceof Player) {
      Player p = (Player) e.getEntity();
      if (!p.hasPermission("servercore.getDamage")) {
        if (e.getDamager() instanceof Player) {
          Player d = (Player) e.getDamager();
          d.sendMessage("Du kannst  den Spieler " + p.getDisplayName() + " nicht verletzen");
        }
        e.setCancelled(true);
      }
    }
  }
}
