package de.winedev.servercore.framework;

import de.winedev.servercore.Files;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

public class FileManager implements Files {

    public static void load(WGSFile wgsFile) {
        try {
            wgsFile.getConfig().load(wgsFile.getFile());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InvalidConfigurationException invalidConfigurationException) {
            invalidConfigurationException.printStackTrace();
        }
    }

    public static void loadAll() {
        try {
            users.getConfig().load(users.getFile());
            groups.getConfig().load(groups.getFile());
            motd.getConfig().load(motd.getFile());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InvalidConfigurationException invalidConfigurationException) {
            invalidConfigurationException.printStackTrace();
        }
    }

    public static void save(WGSFile wgsFile) {
        try {
            wgsFile.getConfig().save(wgsFile.getFile());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void saveAll() {
        try {
            users.getConfig().save(users.getFile());
            groups.getConfig().save(groups.getFile());
            motd.getConfig().save(motd.getFile());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
