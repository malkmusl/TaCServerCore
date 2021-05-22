package de.winedev.servercore.events;

import de.winedev.servercore.Files;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class EVENT_ServerListPing implements Listener, Files {

  private static HashMap getMotdMap() {
    List motdlist = motd.getConfig().getList("motd");
    Random r = new Random();
    String motd = motdlist.get(r.nextInt(motdlist.size())).toString();
    String[] split = motd.split("~");
    HashMap enabler = new HashMap();
    enabler.put(split[1], split[0]);
    return enabler;
  }
}
