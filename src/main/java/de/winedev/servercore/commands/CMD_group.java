package de.winedev.servercore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_group implements CommandExecutor {

  // /group <target> <create/delete/option/add/remove> <value> [option]

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player p = (Player) sender;
      if (p.hasPermission("servercore.group") || p.hasPermission("servercore.*") || p.isOp()) {
        // player side command structure
      } else {
        // console side command structure
      }
    }
    return true;
  }
}
