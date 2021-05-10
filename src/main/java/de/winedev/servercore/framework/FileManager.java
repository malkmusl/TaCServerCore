package de.winedev.servercore.framework;

import de.winedev.servercore.Files;
import de.winedev.servercore.configdefaults.FILE_motd;
import de.winedev.servercore.configdefaults.FILE_users;
import de.winedev.servercore.configdefaults.File_groups;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

public class FileManager implements Files {

  public static void load(WGSFile wgsFile) {
    try {
      wgsFile.getConfig().load(wgsFile.getFile());
    } catch (IOException | InvalidConfigurationException ioException) {
      ioException.printStackTrace();
    }
  }

  public static void loadAll() {
    try {
      users.getConfig().load(users.getFile());
      groups.getConfig().load(groups.getFile());
      motd.getConfig().load(motd.getFile());
    } catch (IOException | InvalidConfigurationException ioException) {
      ioException.printStackTrace();
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

  public static void init() {
    File_groups file_groups = new File_groups();
    file_groups.addDefaults();

    FILE_motd file_motd = new FILE_motd();
    file_motd.addDefaults();

    FILE_users file_users = new FILE_users();
    file_users.addDefaults();

    save(users);
    save(groups);
    save(motd);
  }
}
