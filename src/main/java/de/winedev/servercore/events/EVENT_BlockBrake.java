package de.winedev.servercore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class EVENT_BlockBrake implements Listener {

  @EventHandler
  public void hasPermToBrake(BlockBreakEvent e) {
    Player p = e.getPlayer();
    if (p.hasPermission("servercore.*")
        || p.hasPermission("*")
        || p.hasPermission("servercore.build")) {
      e.setCancelled(false);
    } else {
      p.sendMessage("Du hast keine Berechtigung etwas abzubauen");
      e.setCancelled(true);
    }
  }
}
