package de.winedev.servercore.events;

import de.winedev.servercore.Files;
import de.winedev.servercore.ServerCore;
import de.winedev.servercore.framework.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.haoshoku.nick.api.NickAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EVENT_PlayerJoin implements Listener, Files {

  @EventHandler
  public void addToFile(PlayerJoinEvent e) {
    Player p = e.getPlayer();
    String PlayerPath = "users." + p.getUniqueId() + ".";
    String GroupPath = PlayerPath + "group";
    String AutoNickPath = PlayerPath + "autonick";
    String PlayerNamePath = PlayerPath + "name";

    FileManager.load(users);
    FileManager.load(groups);
    // add Group Specific Permissions to Player
    if (users.getConfig().contains(PlayerPath)) {
      p.sendMessage("§6Willkommen zurück!");
      String usergroup = Objects.requireNonNull(users.getConfig().get(GroupPath)).toString();
      if (groups.getConfig().contains(usergroup)) {
        //noinspection rawtypes
        List groupsList = groups.getConfig().getList(usergroup + ".permissions");
        assert groupsList != null;
        for (Object o : groupsList) {
          String perm = o.toString();
          p.addAttachment(ServerCore.pl, perm, true);
        }
      } else {
        p.sendMessage("§4[ERROR] die Gruppe existiert nicht");
      }
      // add Player Specific Permissions to Player
      //noinspection rawtypes
      List userPerm = users.getConfig().getList(PlayerPath + "permissions");
      assert userPerm != null;
      for (Object o : userPerm) {
        String perm = o.toString();
        p.addAttachment(ServerCore.pl, perm, true);
      }
    } else {
      p.sendMessage("Willkommen auf dem Server");
      users.getConfig().set(PlayerPath + "name", p.getName());
      users.getConfig().set(PlayerPath + "group", "default");
      ArrayList<String> defaultperms = new ArrayList<>();
      defaultperms.add("servercore.move");
      defaultperms.add("servercore.chat");
      users.getConfig().set(PlayerPath + "permissions", defaultperms);
      try {
        users.getConfig().save(users.getFile());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
    String usergroup = Objects.requireNonNull(users.getConfig().get(GroupPath)).toString();
    if (users.getConfig().getBoolean(AutoNickPath)) {
      String prefix =
          Objects.requireNonNull(groups.getConfig().get("default.prefix"))
                  .toString()
                  .replace("&", "§")
              + " ";
      String suffix =
          " "
              + Objects.requireNonNull(groups.getConfig().get("default.suffix"))
                  .toString()
                  .replace("&", "§");

      String PlayerName = Objects.requireNonNull(users.getConfig().get(PlayerNamePath)).toString();
      e.setJoinMessage("§7[+] " + users.getConfig().get(PlayerNamePath));

      //
      //
      //
      NickAPI.nick(p, prefix + PlayerName + suffix + "§r");
      NickAPI.setUniqueId(p, PlayerName);
      NickAPI.setGameProfileName(p, PlayerName);
      NickAPI.refreshPlayer(p);
      //
      //
      //
    } else {
      String prefix =
          Objects.requireNonNull(groups.getConfig().get(usergroup + ".prefix"))
                  .toString()
                  .replace("&", "§")
              + " ";
      String suffix =
          " "
              + Objects.requireNonNull(groups.getConfig().get(usergroup + ".suffix"))
                  .toString()
                  .replace("&", "§");

      e.setJoinMessage("§7[+] " + p.getName());

      //
      //
      //
      NickAPI.nick(p, prefix + NickAPI.getOriginalName(p) + suffix + "§r");
      NickAPI.setUniqueId(p, NickAPI.getOriginalName(p));
      NickAPI.setGameProfileName(p, NickAPI.getOriginalName(p));
      NickAPI.refreshPlayer(p);
      //
      //
      //
    }
  }
}
