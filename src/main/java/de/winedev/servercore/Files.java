package de.winedev.servercore;

import de.winedev.servercore.framework.WGSFile;

public interface Files {

    WGSFile groups = new WGSFile("permissions", "groups.wgsf");
    WGSFile users = new WGSFile("permissions", "users.wgsf");
    WGSFile motd = new WGSFile("config", "motd.wgsf");
    WGSFile test = new WGSFile("lang", "de_DE.lang");
}
