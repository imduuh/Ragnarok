package me.tantsz.ragnarok.listeners;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import me.tantsz.ragnarok.Main;
import me.tantsz.ragnarok.commands.Commands;
import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;

public class Listeners implements Listener {
	
	public static Map<Player, Integer> kills = new HashMap<>();
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player killer = e.getEntity().getKiller();
	    Player vitima = e.getEntity();
	    if (killer instanceof Player && vitima instanceof Player) {
	    	if (Commands.acontecendo) {
	    		if (Commands.participantes.contains(killer) && Commands.participantes.contains(vitima)) {
			    	if (killer.getWorld().getName().equals("ragnarok") && vitima.getWorld().getName().equals("ragnarok")) {
			    		Commands.participantes.remove(vitima);
			    		if (Main.getMain().getConfig().getBoolean("MySQL.Ativado")) {
			    			Main.getMain().getMysql().addKill(killer.getName());
			    		} else {
			    			Main.getMain().getsql().addKill(killer.getName());
			    		}
			    		if (kills.containsKey(killer)) {
			    			int currentKills = kills.get(killer);
			    		    int newKills = currentKills + 1;
			    		    kills.put(killer, newKills);
			    		} else {
			    			kills.put(killer, 1);
			    		}
			    		Main.eco.depositPlayer(killer, Main.getMain().getConfig().getInt("Premiacoes.Valor"));
			    		killer.sendMessage(Main.getConfigString("Mensagens.GanhouDinheiro").replace("{vitima}", vitima.getName()));
			    		if (killer.hasPermission("ragnarok.mito")) {
			    			Main.eco.depositPlayer(killer, Main.getMain().getConfig().getInt("Bonus.Mito"));
			    			killer.sendMessage(Main.getConfigString("Mensagens.BonusMito"));
			    		}
			    		else if (killer.hasPermission("ragnarok.vip")) {
			    			Main.eco.depositPlayer(killer, Main.getMain().getConfig().getInt("Bonus.VIP"));
			    			killer.sendMessage(Main.getConfigString("Mensagens.BonusVIP"));
			    		}
			    		killer.sendMessage(Main.getConfigString("Mensagens.TotalKills").replace("{kills}", Integer.toString(kills.get(killer))));
			    	}
		    	} else {
		    		Commands.participantes.remove(vitima);
		    	}
	    	}
	    }
	}
	
	public static String[] getPlayerWithMaxKills() {
		Player playerWithMaxKills = null;
		int maxKills = Integer.MIN_VALUE;
		for (Map.Entry<Player, Integer> entry : kills.entrySet()) {
		    Player currentPlayer = entry.getKey();
		    int currentKills = entry.getValue();
		    if (currentKills > maxKills) {
		        maxKills = currentKills;
		        playerWithMaxKills = currentPlayer;
		    }
		}
		if (playerWithMaxKills != null) {
			String[] result = { playerWithMaxKills.getName(), Integer.toString(maxKills)};
			return result;
		}
		String[] resultnull = {"Ninguém", "0"};
		return resultnull;
	}
	
	@EventHandler
	public void onChatMessageEvent(ChatMessageEvent e) {
		Player p = e.getSender();
		if (Main.getMain().getConfig().contains("Premiacoes.UltimoVencedor") && e.getTags().contains("ragnarok") && p.getName().equalsIgnoreCase(Main.getConfigString("Premiacoes.UltimoVencedor"))) {
			e.setTagValue("ragnarok", Main.getConfigString("Premiacoes.Tag"));
			}
		}
}