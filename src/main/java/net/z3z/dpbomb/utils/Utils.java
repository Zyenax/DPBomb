package net.z3z.dpbomb.utils;

import java.util.List;
import java.util.Random;

import net.z3z.dpbomb.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftFirework;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Utils implements Listener{
	
	private static Main plugin;
	public Utils(Main listener) {
		Utils.plugin = listener;		
	}
	
	public static String color(String string){
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	public static void playFirework(Location paramLocation,
			FireworkEffect paramFireworkEffect) {
		Entity localEntity = paramLocation.getWorld().spawnEntity(
				paramLocation, EntityType.FIREWORK);
		Firework localFirework = (Firework) localEntity;
		FireworkMeta localFireworkMeta = localFirework.getFireworkMeta();
		localFireworkMeta.addEffect(paramFireworkEffect);
		localFireworkMeta.setPower(1);
		localFirework.setFireworkMeta(localFireworkMeta);

		((CraftFirework) localFirework).getHandle().expectedLifespan = 1;
	}
	
	public static int randomNum(int Low, int High){
		Random r = new Random();
		int R = r.nextInt(High-Low) + Low;
		return R;
	}
	
	public static ItemStack createItem(Material material, int amount ,int dataValue, String name,
			List<String> list) {
		ItemStack selector = new ItemStack(material, amount, (short) dataValue);
		ItemMeta selectorMeta = selector.getItemMeta();
		selectorMeta.setDisplayName(name);
		if (list != null)
			selectorMeta.setLore(list);
		selector.setItemMeta(selectorMeta);
		return selector;
	}

	public static ItemStack createSkull(String pname, String name,
			List<String> lore) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta3 = (SkullMeta) skull.getItemMeta();
		meta3.setOwner(pname);
		meta3.setDisplayName(name);
		meta3.setLore(lore);
		skull.setItemMeta(meta3);
		return skull;
	}
	
	public static void updateinventory(final Player p){
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				p.updateInventory();
			}
		}, 5L);
	}

}
