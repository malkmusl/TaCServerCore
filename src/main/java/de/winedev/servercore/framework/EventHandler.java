package de.winedev.servercore.framework;

import de.winedev.servercore.ServerCore;
import de.winedev.servercore.events.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class EventHandler {

    static Plugin sc = ServerCore.pl;

    public static void init() {
        regEvents(new EVENT_BlockBrake());
        regEvents(new EVENT_BlockPlace());
        regEvents(new EVENT_PlayerMove());
        regEvents(new EVENT_PlayerJoin());
    }

    private static void regEvents(Listener event){
        sc.getServer().getPluginManager().registerEvents(event, sc);
        System.out.println("Das Event "+ event.getClass().getSimpleName()+" wurde regestriert");
    }
}
