package de.winedev.servercore;

import de.winedev.servercore.configdefaults.FILE_motd;
import de.winedev.servercore.configdefaults.FILE_users;
import de.winedev.servercore.configdefaults.File_groups;
import de.winedev.servercore.framework.CommandHandler;
import de.winedev.servercore.framework.EventHandler;
import de.winedev.servercore.framework.FileManager;
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
        FileManager.init();

    }

    @Override
    public void onDisable() {
    }

}
