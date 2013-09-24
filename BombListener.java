package me.tomg.guns;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BombListener implements Listener{

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		if (!(e.getAction() == Action.RIGHT_CLICK_AIR)) return;
		if (!(e.getItem().getType() == Material.BLAZE_ROD)) return;		
		e.getPlayer().launchProjectile(Snowball.class);
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e){
			Projectile p = e.getEntity();
			if (!(p instanceof Snowball)) return;
			Snowball s = (Snowball) p;
			s.getWorld().createExplosion(s.getLocation(), 0F);
			for (Entity en : s.getNearbyEntities(10, 10, 10)){
				if (en instanceof Player){
					Player pl = (Player) en;
					if (!(pl == e.getEntity().getShooter())) pl.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
				}
			}}
}
