package io.github.lst96.DisableCommands.commands;

import java.util.List;

import net.mindfulhacker.disablecommands.DisableCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ARLRCommand implements CommandExecutor {
private DisableCommands plugin;
	
	public ARLRCommand(DisableCommands instance){
		this.plugin = instance;
}


public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	String msg = plugin.getConfig().getString("Messages.Permission Deny Message");
	String fmsg = plugin.getConfig().getString("Messages.Already Forbidden Message");
	String nmsg = plugin.getConfig().getString("Messages.Not Forbidden Message");
  if (args.length == 0) 
    return false;
  if (args.length == 1) { 
    if (args[0].equalsIgnoreCase("list")) {
      if ((sender.isOp()) || (sender.hasPermission("disablecommands.list"))) {
        sender.sendMessage(ChatColor.GOLD + "Forbidden Commands");
        for (String command : plugin.getConfig().getStringList("forbidden-commands")) {
          sender.sendMessage(ChatColor.GOLD + " - " + command);
        }
        return true;
      }
      sender.sendMessage(ChatColor.RED + msg);
      return true;
    }

  if (args[0].equalsIgnoreCase("reload")) {
	  if ((sender.isOp()) || (sender.hasPermission("disablecommands.reload"))) {
		 plugin.reloadConfig(); 
		 sender.sendMessage(ChatColor.DARK_RED + "[Disable Commands] " + ChatColor.RED + "Configuration Reloaded!");
	     return true;
  }
      sender.sendMessage(ChatColor.RED + msg);
      return true;
}
    return false;
  }
  if (args.length == 2) {
    if (args[0].equalsIgnoreCase("add")) {
      if ((sender.isOp()) || (sender.hasPermission("disablecommands.add"))) {
        List<String> forbiddenCommands = plugin.getConfig().getStringList("forbidden-commands");
        if (forbiddenCommands.contains(args[1].toLowerCase())) {
          sender.sendMessage(ChatColor.RED + fmsg);
          return true;
        }
        forbiddenCommands.add(args[1].toLowerCase());
        plugin.getConfig().set("forbidden-commands", forbiddenCommands);
        plugin.saveConfig();
        sender.sendMessage(ChatColor.GREEN + "Added " + ChatColor.AQUA + args[1].toLowerCase() + ChatColor.GREEN + " to the forbidden commands list!");
        return true;
      }
      sender.sendMessage(ChatColor.RED + msg);
      return true;
    }
    if (args[0].equalsIgnoreCase("remove")) {
      if ((sender.isOp()) || (sender.hasPermission("disablecommands.remove"))) {
        List<String> forbiddenCommands = plugin.getConfig().getStringList("forbidden-commands");
        if (!forbiddenCommands.contains(args[1].toLowerCase())) {
          sender.sendMessage(ChatColor.RED + nmsg);
          return true;
        }
        forbiddenCommands.remove(args[1].toLowerCase());
        plugin.getConfig().set("forbidden-commands", forbiddenCommands);
        plugin.saveConfig();
        sender.sendMessage(ChatColor.GREEN + "Removed " + ChatColor.AQUA + args[1].toLowerCase() + ChatColor.GREEN + " from the forbidden commands list!");
        return true;
      }
      sender.sendMessage(ChatColor.RED + msg);
      return true;
    }
return false;
}
return false;
  }
}