package net.z3z.dpbomb.handlers;

import java.util.Arrays;
import java.util.HashMap;

import net.z3z.dpbomb.Main;
import net.z3z.dpbomb.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class BombHandler implements Listener {

	private static Main plugin;

	public BombHandler(Main listener) {
		BombHandler.plugin = listener;
	}
	
	static HashMap<Player, BukkitTask> Bomb = new HashMap<Player, BukkitTask>();
	static HashMap<String, Location> Bombloc = new HashMap<String, Location>();
	static HashMap<String, String> InUse = new HashMap<String, String>();
	
	static Item items;
	
	public static void throwBomb(Player player) {
		final Player p = player;
		World world = p.getWorld();
		if(!InUse.containsKey("inuse")){
			InUse.put("inuse", "inuse");
			final Item bomb = world.dropItem(
					p.getEyeLocation().subtract(0, 0.7, 0), new ItemStack(
							Material.OBSIDIAN));
			bomb.setVelocity(p.getLocation().getDirection());
			Bukkit.broadcastMessage(Utils.color("&b[&eDPBomb&b] &a&lDROP PARTY ACTIVATED TYPE &c/dp warp"));

			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
			scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					Bukkit.broadcastMessage(Utils.color("&c&lTHE DROP PARTY IS OVER"));
					Bomb.get(p).cancel();
					bomb.remove();
					InUse.clear();
					Bombloc.clear();
				}
			}, 20 * 30L);
			Bomb.put(p, scheduler.runTaskTimer(plugin, new Runnable() {
				public void run() {
					Bombloc.put("bomb", bomb.getLocation());
					FireworkEffect yellowEffect = FireworkEffect
							.builder()
							.trail(true)
							.flicker(true)
							.withColor(
									new Color[] { Color.YELLOW, Color.AQUA,
											Color.PURPLE })
							.with(FireworkEffect.Type.BURST).build();
					Utils.playFirework(bomb.getLocation(), yellowEffect);
					Integer num = Utils.randomNum(1, 10);
					if(num.equals(1)){
						items = bomb.getWorld().dropItem(bomb.getLocation(), Utils.createItem(Material.DIAMOND_PICKAXE, 1, 0, Utils.color("&e&l&k::&b&lOP PICK&e&l&k::"), Arrays.asList(Utils.color("&cThe best pickaxe you can get!"), Utils.color("&aBrag to your friends >:)"))));
						
					}else if(num.equals(2)){
						items = bomb.getWorld().dropItem(bomb.getLocation(), Utils.createItem(Material.EXP_BOTTLE, 64, 0, Utils.color("&e&l&k::&b&lMASSIVE XP&e&l&k::"), Arrays.asList(Utils.color("&cA huge amount of xp for ya!"), Utils.color("&aFree to use at your will!"))));
						
					}else if(num.equals(3)){
						items = bomb.getWorld().dropItem(bomb.getLocation(), Utils.createItem(Material.OBSIDIAN, 32, 0, Utils.color("&e&l&k::&b&lHARDENED OBSIDIAN&e&l&k::"), Arrays.asList(Utils.color("&cAs hard as you want it to be!"), Utils.color("&aGood for bases!"))));
						
					}else if(num.equals(4)){
						items = bomb.getWorld().dropItem(bomb.getLocation(), Utils.createItem(Material.DIAMOND_BOOTS, 1, 0, Utils.color("&e&l&k::&b&lFROSTY'S BOOTS&e&l&k::"), Arrays.asList(Utils.color("&cFreeze the water you walk on!"), Utils.color("&aDont freeze yourself :)!"))));
						
					}else if(num.equals(5)){
						items = bomb.getWorld().dropItem(bomb.getLocation(), Utils.createItem(Material.TNT, 15, 0, Utils.color("&e&l&k::&b&lDYNAMITE&e&l&k::"), Arrays.asList(Utils.color("&cBlow some foes up!"), Utils.color("&aDont nuke yourself :)!"))));
						
					}else if(num.equals(6)){
						items = bomb.getWorld().dropItem(bomb.getLocation(), Utils.createItem(Material.DIAMOND_SWORD, 1, 0, Utils.color("&e&l&k::&b&lOP SHANK&e&l&k::"), Arrays.asList(Utils.color("&cThe best shank you can get!"), Utils.color("&aStab some people!"))));
						
					}else if(num.equals(7)){
						items = bomb.getWorld().dropItem(bomb.getLocation(), Utils.createItem(Material.DIAMOND, 10, 0, Utils.color("&e&l&k::&b&lSHINY DIAMONDS&e&l&k::"), Arrays.asList(Utils.color("&cHeres what a diamond looks like!"), Utils.color("&aIncase you didnt know this is a diamond :)!"))));
						
					}else if(num.equals(8)){
						items = bomb.getWorld().dropItem(bomb.getLocation(), Utils.createItem(Material.ENCHANTMENT_TABLE, 1, 0, Utils.color("&e&l&k::&b&lSORCERER STUMP&e&l&k::"), Arrays.asList(Utils.color("&cThese seem pretty useful!"), Utils.color("&aEnchant some stuff!"))));
						
					}else if(num.equals(9)){
						items = bomb.getWorld().dropItem(bomb.getLocation(), Utils.createItem(Material.SLIME_BLOCK, 10, 0, Utils.color("&e&l&k::&b&lBOUNCE BLOCKS&e&l&k::"), Arrays.asList(Utils.color("&cThese seem pretty useless!"), Utils.color("&aBounce bounce bounce :)!"))));
						
					}else if(num.equals(10)){
						items = bomb.getWorld().dropItem(bomb.getLocation(), Utils.createItem(Material.ENDER_PEARL, 1, 0, Utils.color("&e&l&k::&b&lENDERMAN SOUL&e&l&k::"), Arrays.asList(Utils.color("&cJump around like an enderman!"), Utils.color("&aDont forget to screech at people!"))));
					}
					Vector direction = new Vector();
					direction.setX(0.0D + Math.random() - Math.random());
					direction.setY(0.2D + Math.random());
					direction.setZ(0.0D + Math.random() - Math.random());
					items.setVelocity(direction);
				}
			}, 0, 5L));
		}else{
			p.sendMessage(Utils.color("&cDrop party already going!"));
		}
	}

	public static void tpToBomb(Player player) {
		if(Bombloc.containsKey("bomb")){
			player.teleport(Bombloc.get("bomb"));
		}else{
			player.sendMessage(Utils.color("&c&lNo drop parties currently active!"));
		}
	}

}
