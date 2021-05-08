package de.winedev.servercore.configdefaults;

import de.winedev.servercore.Files;
import de.winedev.servercore.framework.WGSFile;

import java.io.IOException;
import java.util.ArrayList;

public abstract class ConfigDefault implements Files {

    WGSFile file;
    public ConfigDefault(WGSFile wgsFile){
        file = wgsFile;
    }


    public abstract void addDefaults();

    public void add(String path, String string) {
        file.getConfig().options().copyDefaults(true);
        file.getConfig().addDefault(path, string);
        try {
            file.getConfig().save(file.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(String path, Integer integer) {
        file.getConfig().options().copyDefaults(true);
        file.getConfig().addDefault(path, integer);
        try {
            file.getConfig().save(file.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(String path, ArrayList list) {
        file.getConfig().options().copyDefaults(true);
        file.getConfig().addDefault(path, list);
        try {
            file.getConfig().save(file.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(String path, Object obj){
        file.getConfig().options().copyDefaults(true);
        file.getConfig().addDefault(path, obj);
        try {
            file.getConfig().save(file.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
