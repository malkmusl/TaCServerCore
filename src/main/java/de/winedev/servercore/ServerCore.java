package de.winedev.servercore;

import de.winedev.servercore.configdefaults.FILE_motd;
import de.winedev.servercore.framework.CommandHandler;
import de.winedev.servercore.framework.EventHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class ServerCore extends JavaPlugin implements Files{

    public static Plugin pl;
    public static ServerCore serverCore;

    public ServerCore() {
        serverCore = this;
    }

    @Override
    public void onEnable() {
        pl = this;
        EventHandler.init();
        CommandHandler.init();
        FILE_motd file_motd = new FILE_motd(motd);
        file_motd.addDefaults();
        try {
            groups.getConfig().save(groups.getFile());
            users.getConfig().save(users.getFile());
            motd.getConfig().save(motd.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
    }

}
