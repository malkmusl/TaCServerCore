package de.winedev.servercore.framework;

import de.winedev.servercore.Files;
import de.winedev.servercore.ServerCore;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.List;

public class perm implements Files {

  public static void attachPermission(Player player, String permissions) {
    if (permissions.length() != 0 && !permissions.equals("*")) {
      String PlayerPath = "users." + player.getUniqueId() + ".";
      String PlayerNamePath = PlayerPath + "name";
      String PlayerName = users.getConfig().get(PlayerNamePath).toString();

      // add Player Specific Permissions to Player -> PlayerSide
      List<String> permissionList =
          (List<String>) users.getConfig().getList(PlayerPath + "permissions");

      for (String permission : permissionList) {
        if (player.hasPermission(permission)) {
          return;
        }
        PermissionAttachment attachment = player.addAttachment(ServerCore.pl, 1);
        String[] perms = permission.split("\\.");
        StringBuilder p = new StringBuilder();
        for (String perm : perms) {
          p.append(perm);
          attachment.setPermission(p.toString(), true);
          p.append('.');
        }
      }
    }
  }
}
