package de.winedev.servercore.configdefaults;

import de.winedev.servercore.framework.ConfigDefault;
import de.winedev.servercore.framework.WGSFile;

public class FILE_users extends ConfigDefault {
    public FILE_users(WGSFile wgsFile) {
        super(users);
    }

    @Override
    public void addDefaults() {
        add("users", "<uuid>");
        add("test", "test");

    }
}
