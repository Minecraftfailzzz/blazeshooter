package me.tomg.guns;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Guns extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		PluginDescriptionFile pdfFile = this.getDescription();
		Bukkit.getServer().getLogger().info(pdfFile.getName() + " Version: " + pdfFile.getVersion() + " by: " + pdfFile.getAuthors() + ChatColor.AQUA + " Has been enabled!");
		Bukkit.getServer().getPluginManager().registerEvents(new BombListener(), this);
	}

	
			 ArrayList<Player> cooldown = new ArrayList<Player>();
		       
		        @Override
				public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		                if (!(sender instanceof Player)) {
		                        sender.sendMessage(ChatColor.RED + "Only players can get kits!");
		                        return true;
		                }
		               
		                final Player p = (Player) sender;
		                PlayerInventory pi = p.getInventory();
		               
		                if (cmd.getName().equalsIgnoreCase("rod")) {
		                        if (cooldown.contains(p)) {
		                                p.sendMessage(ChatColor.RED + "You cannot get another blaze rod yet!");
		                                return true;
		                        }
		                        if (p.hasPermission("guns.getrod")) {
	                                ItemStack wand = new ItemStack(Material.BLAZE_ROD, 1);
	                                ItemMeta swordmeta = wand.getItemMeta();
	                                swordmeta.setDisplayName(ChatColor.GOLD + "Magic Wand");
	                                List<String> lore = new ArrayList<String>();
	                                lore.add(ChatColor.RED + "Right click to");
	                                lore.add(ChatColor.RED + "shoot!");
	                                swordmeta.setLore(lore);
	                                wand.setItemMeta(swordmeta);
	                                pi.addItem(wand);
	                                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 10000, 0));
	                                p.sendMessage(ChatColor.GREEN + "A magical rod has been given to you!");
	                                cooldown.add(p);
	                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	                                        @Override
											public void run() {
	                                                cooldown.remove(p);
	                                        }
	                                }, 100);
	                                return true;
	                        }
	                        else {
	                                p.sendMessage(ChatColor.RED + "You cannot get a kit at this time.");
	                                return true;
	                        }
	                }
	               
	                if (cmd.getName().equalsIgnoreCase("ci")) {
	                        pi.clear();
	                        p.sendMessage(ChatColor.GREEN + "Inventory Cleared!");
	                }
	                return true;
}}
