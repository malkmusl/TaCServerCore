package de.winedev.servercore.events;

import de.winedev.servercore.Files;
import de.winedev.servercore.framework.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

public class EVENT_PlayerChat implements Listener, Files {

  @EventHandler
  public void onChat(AsyncPlayerChatEvent e) {
    FileManager.load(users);
    FileManager.load(groups);
    Player p = e.getPlayer();
    String PlayerPath = "users." + p.getUniqueId() + ".";
    String PlayerNamePath = PlayerPath + "name";
    String PlayerName = Objects.requireNonNull(users.getConfig().get(PlayerNamePath)).toString();
    String GroupPath = PlayerPath + "group";
    String AutoNickPath = PlayerPath + "autonick";

    if (p.hasPermission("servercore.chat")
        || p.hasPermission("servercore.*")
        || p.hasPermission("*")
        || p.isOp()) {
      if (users.getConfig().contains(PlayerPath)) {
        String usergroup = Objects.requireNonNull(users.getConfig().get(GroupPath)).toString();
        if (groups.getConfig().contains(usergroup)) {

          if (users.getConfig().getBoolean(AutoNickPath)) {
            String prefix =
                Objects.requireNonNull(groups.getConfig().get("default.prefix"))
                        .toString()
                        .replace("&", "§")
                    + " ";
            String suffix =
                Objects.requireNonNull(groups.getConfig().get("default.suffix"))
                        .toString()
                        .replace("&", "§")
                    + " ";
            String chatColor =
                Objects.requireNonNull(groups.getConfig().get("default.chatcolor"))
                        .toString()
                        .replace("&", "§")
                    + " ";
            //
            //
            //
            // if (!NickAPI.isNicked(p)) {
            // NickAPI.nick(p, prefix + PlayerName + suffix + "§r");
            // NickAPI.setUniqueId(p, PlayerName);
            // NickAPI.setGameProfileName(p, PlayerName);
            // NickAPI.refreshPlayer(p);
            // }
            //
            //
            //
            e.setFormat(prefix + PlayerName + suffix + "»" + chatColor + e.getMessage());
            p.setPlayerListName(prefix + PlayerName + suffix + "§r");
            p.setDisplayName(prefix + PlayerName + suffix + "§r");
          } else {
            String prefix =
                Objects.requireNonNull(groups.getConfig().get(usergroup + ".prefix"))
                        .toString()
                        .replace("&", "§")
                    + " ";
            String suffix =
                Objects.requireNonNull(groups.getConfig().get(usergroup + ".suffix"))
                        .toString()
                        .replace("&", "§")
                    + " ";
            String chatColor =
                Objects.requireNonNull(groups.getConfig().get(usergroup + ".chatcolor"))
                        .toString()
                        .replace("&", "§")
                    + " ";
            e.setFormat(prefix + p.getName() + suffix + "»" + chatColor + e.getMessage());
            //
            //
            //
            // if (!NickAPI.isNicked(p)) {
            // NickAPI.nick(p, prefix + PlayerName + suffix + "§r");
            // NickAPI.setUniqueId(p, PlayerName);
            // NickAPI.setGameProfileName(p, PlayerName);
            // NickAPI.refreshPlayer(p);
            // }
            //
            //
            //
          }
        } else {
          p.sendMessage(
              "§4[ERROR] die Gruppe existiert nicht wende dich bitte an einen Admin oder Owner");
          String prefix =
              Objects.requireNonNull(groups.getConfig().get("default.prefix"))
                      .toString()
                      .replace("&", "§")
                  + " ";
          String suffix =
              Objects.requireNonNull(groups.getConfig().get("default.suffix"))
                      .toString()
                      .replace("&", "§")
                  + " ";
          String chatColor =
              Objects.requireNonNull(groups.getConfig().get("default.chatcolor"))
                      .toString()
                      .replace("&", "§")
                  + " ";
          e.setFormat(prefix + PlayerName + suffix + "»" + chatColor + e.getMessage());
          //
          //
          //
          // if (!NickAPI.isNicked(p)) {
          // NickAPI.nick(p, prefix + PlayerName + suffix + "§r");
          // NickAPI.setUniqueId(p, PlayerName);
          // NickAPI.setGameProfileName(p, PlayerName);
          // NickAPI.refreshPlayer(p);
          // }
          //
          //
          //
        }
      } else {
        p.kickPlayer("Your PlayerData is currupted");
      }
    } else {
      p.sendMessage("§cDu hast keine Berechtigung den Chat zu nutzen!");
      e.setCancelled(true);
    }
  }
}
