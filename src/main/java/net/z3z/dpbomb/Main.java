package net.z3z.dpbomb;

import net.z3z.dpbomb.handlers.BombHandler;
import net.z3z.dpbomb.handlers.CommandHandler;
import net.z3z.dpbomb.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_10_R1.CraftServer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public void onEnable(){
		registerListeners();
		registerCommands();
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		console.sendMessage(Utils.color("&b[&eDPBomb&b] &eDPBomb has been enabled!"));
	}
	
	public void onDisable(){
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		console.sendMessage(Utils.color("&b[&eDPBomb&b] &eDPBomb has been disabled!"));
	}
	
	public void registerListeners(){
		PluginManager manager = Bukkit.getServer().getPluginManager();
		manager.registerEvents(new BombHandler(this), this);
		manager.registerEvents(new Utils(this), this);
	}
	
	public void registerCommands(){
		((CraftServer) this.getServer()).getCommandMap().register("dp", new CommandHandler("dp", "Start a drop party!"));
		((CraftServer) this.getServer()).getCommandMap().register("dropparty", new CommandHandler("dropparty", "Start a drop party!"));
	}

}
