package io.github.lst96.DisableCommands.commands;

import java.util.List;

import net.mindfulhacker.disablecommands.DisableCommands;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandBlock implements Listener {
private DisableCommands plugin;
	
	public CommandBlock(DisableCommands instance){
		this.plugin = instance;
}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	  public void onPreprocess(PlayerCommandPreprocessEvent event)
	  {
	    String command = event.getMessage().substring(1);
	    if ((event.getPlayer().isOp()) || (event.getPlayer().hasPermission("disablecommands.bypass"))) {
	    }else{
	      List<String> forbiddenCommands = plugin.getConfig().getStringList("forbidden-commands");
	      for (String forbiddenCommand : forbiddenCommands)
	        if (command.startsWith(forbiddenCommand)) {
	          event.setCancelled(true);
	          String msg = plugin.getConfig().getString("Messages.Command Deny Message");
	          event.getPlayer().sendMessage(ChatColor.RED + msg);
	          break;
	        }
	    }
	  }
}
