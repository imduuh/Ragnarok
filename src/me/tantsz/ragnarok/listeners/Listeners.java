package me.tantsz.ragnarok.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import me.tantsz.ragnarok.Main;
import me.tantsz.ragnarok.commands.Commands;

public class Listeners implements Listener {

	@EventHandler
	void onDeath(PlayerDeathEvent e) {
		Player killer = e.getEntity().getKiller();
	    Player vitima = e.getEntity();
	    if (killer instanceof Player && vitima instanceof Player) {
		    if (killer.getWorld().getName().equals("ragnarok") || vitima.getWorld().getName().equals("ragnarok")) {
		    	if (e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player) {	
			    	if (Commands.acontecendo) {
			    		Main.eco.depositPlayer(killer, 300);
			    		killer.sendMessage("§c§lRAGNARÖK §8» §7Você ganhou §a300 Reais §7por matar §a" + vitima.getName() + "§7!");
			    		if (killer.hasPermission("ragnarok.mito")) {
			    			Main.eco.depositPlayer(killer, 700);
			    			killer.sendMessage("§c§lRAGNARÖK §8» §7Você ganhou um bônus de §a700 Reais §7por estar com a tag §e{MITO}§7!");
			    		} else if (killer.hasPermission("ragnarok.vip")) {
			    			Main.eco.depositPlayer(killer, 200);
			    			killer.sendMessage("§c§lRAGNARÖK §8» §7Você ganhou um bônus de §a200 Reais §7por ser §6VIP§7!");
			    			}
			    		}
		    		}
		    	}
	    	}
	    }
	}