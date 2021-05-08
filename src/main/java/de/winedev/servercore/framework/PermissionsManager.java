package de.winedev.servercore.framework;

import de.winedev.servercore.ServerCore;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PermissionsManager {

    static Plugin sc = ServerCore.pl;

    public static void addPermissions(Player p, String permission){
        p.addAttachment(sc,permission, true);
    }
}
