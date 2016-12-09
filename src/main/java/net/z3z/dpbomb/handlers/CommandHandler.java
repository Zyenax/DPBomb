package net.z3z.dpbomb.handlers;

import net.z3z.dpbomb.utils.Utils;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class CommandHandler extends BukkitCommand{
	
	public CommandHandler(String string, String description) {
		super(string);
		this.description = description;
	}
	
	public boolean execute(CommandSender sender, String alias, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if (alias.equalsIgnoreCase("dp") || alias.equalsIgnoreCase("dropparty")) {
					if(args.length < 1){
						if(p.hasPermission("dropparty.use")){
							BombHandler.throwBomb(p);
						}else{
							p.sendMessage(Utils.color("&b[&eDPBomb&b] &cYou dont have the permission &adropparty.use&c!"));
						}
					}else if(args[0].equals("warp")){
						if(p.hasPermission("dropparty.warp")){
							BombHandler.tpToBomb(p);
						}else{
							p.sendMessage(Utils.color("&b[&eDPBomb&b] &cYou dont have the permission &adropparty.warp&c!"));
						}
					}else if(args.length >= 2){
						p.sendMessage(Utils.color("&b[&eDPBomb&b] &cUSAGE: &a/dp or /dp warp"));
					}
				}
		}
		return true;
	}
}
