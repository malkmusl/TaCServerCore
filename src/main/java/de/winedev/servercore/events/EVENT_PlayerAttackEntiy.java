package de.winedev.servercore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EVENT_PlayerAttackEntiy implements Listener {

  @EventHandler
  public static void getDamageByEntiy(EntityDamageByEntityEvent e) {
    if (e.getDamager() instanceof Player) {
      Player d = (Player) e.getDamager();
      if (!d.hasPermission("servercore.damageEntity")) {
        e.setCancelled(true);
        if (e.getEntity() instanceof Player) {
          d.sendMessage("Du darfst keine Spieler schlagen");
        } else {
          d.sendMessage("Du darfst keine Mobs schlagen");
        }
      }
    }
  }
}
