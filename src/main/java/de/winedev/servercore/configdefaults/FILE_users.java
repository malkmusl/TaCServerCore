package de.winedev.servercore.configdefaults;

import de.winedev.servercore.framework.ConfigDefault;
import de.winedev.servercore.framework.WGSFile;

import java.util.ArrayList;

/*
users:
  cce8457b-2667-4996-b56c-bad794725d42:
    name: WineGaming
    group: default
    permissions:
    - servercore.move
    - servercore.chat

 */

public class FILE_users extends ConfigDefault {
    public FILE_users(WGSFile wgsFile) {
        super(users);
    }

    @Override
    public void addDefaults() {
        add("users.cce8457b-2667-4996-b56c-bad794725d42.name", "WineGaming");
        add("users.cce8457b-2667-4996-b56c-bad794725d42.group", "default");
        add("users.cce8457b-2667-4996-b56c-bad794725d42.autonick", false);
        ArrayList<String> exampleperms = new ArrayList<>();
        exampleperms.add("servercore.*");
        add("users.cce8457b-2667-4996-b56c-bad794725d42.permissions", exampleperms);
    }
}
