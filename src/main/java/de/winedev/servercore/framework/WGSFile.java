package de.winedev.servercore.framework;

import de.winedev.servercore.ServerCore;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class WGSFile {

  // WGSF is a combination of File and YamlConfiguration
  private final File f;
  private final YamlConfiguration c;

  public WGSFile(String path, String name) {
    f = new File(ServerCore.pl.getDataFolder().getPath() + "/" + path, name);
    c = YamlConfiguration.loadConfiguration(f);
  }

  public File getFile() {
    return this.f;
  }

  public YamlConfiguration getConfig() {
    return this.c;
  }
}
