package de.winedev.servercore.configdefaults;

/*

default:
  prefix: '&6[Owner]'
  suffix: ''
  chatcolor: '&e'
  permissions:
  - example.perm0
  - example.perm1
  - example.perm2
  - example.perm3
  - example.perm4

 */

import de.winedev.servercore.framework.ConfigDefault;

import java.util.ArrayList;

public class File_groups extends ConfigDefault {

  public File_groups() {
    super(groups);
  }

  @Override
  public void addDefaults() {
    add("default.prefix", "&a[User]");
    add("default.suffix", "");
    add("default.chatcolor", "&7");
    ArrayList<String> exampleperms = new ArrayList<>();
    exampleperms.add("servercore.move");
    exampleperms.add("servercore.build");
    exampleperms.add("servercore.chat");
    add("default.permissions", exampleperms);
  }
}
