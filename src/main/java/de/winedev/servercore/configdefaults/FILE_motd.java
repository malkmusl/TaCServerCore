package de.winedev.servercore.configdefaults;

import de.winedev.servercore.framework.ConfigDefault;
import de.winedev.servercore.framework.WGSFile;

import java.util.ArrayList;

public class FILE_motd extends ConfigDefault {
  public FILE_motd(WGSFile wgsFile) {
    super(motd);
  }

  @Override
  public void addDefaults() {
    ArrayList<String> motd = new ArrayList<>();
    motd.add("test~true");
    motd.add("test1~false");
    motd.add("test2~false");
    motd.add("test3~true");
    motd.add("test4~true");

    add("motd", motd);
  }
}
