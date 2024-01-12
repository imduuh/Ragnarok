package me.tantsz.ragnarok.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	
	public static boolean acontecendo = false;
	
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ragnarok" )) {
			if (args.length != 1) {
				return true;
			}
			Player p = (Player)s;
			if (args[0].equalsIgnoreCase("ajuda")) {
				if (p.hasPermission("ragnarok.ajuda")) {
					p.sendMessage("§4§lRAGNARÖK §8» §7Comandos do evento:");
					p.sendMessage("§c/ragnarok entrar §f- §7Entra no evento.");
					p.sendMessage("§c/ragnarok recompensas §f- §7Mostra as recompensas do evento.");
					p.sendMessage("§c/ragnarok iniciar §f- §7Inicia o evento.");
					p.sendMessage("§c/ragnarok cancelar §f- §7Cancela o evento.");
				} else {
					p.sendMessage("§4§lRAGNARÖK §8» §7Comandos do evento:");
					p.sendMessage("§c/ragnarok entrar §f- §7Entra no evento.");
					p.sendMessage("§c/ragnarok topkills §f- §7Mostro o TOP com os jogadores que mais mataram no evento.");
					p.sendMessage("§c/ragnarok recompensas §f- §7Mostra as recompensas do evento.");
				}
			}
			if (args[0].equalsIgnoreCase("iniciar")) {
				if (!(p.hasPermission("ragnarok.admin"))) {
					p.sendMessage("§3§lString §8» §cVocê não tem permissão para executar esse comando.");
					return true;
				}
				if (Commands.acontecendo == true) {
					p.sendMessage("§c§lRAGNARÖK §8» §cO evento já está acontecendo!");
					return true;
				}
				Commands.acontecendo = true;
				p.sendMessage("§c§lRAGNARÖK §8» §7Você iniciou o evento §cRagnarök§7!");
				Bukkit.broadcastMessage("");
				Bukkit.broadcastMessage("§c§lRAGNARÖK §8» §7Evento §cRAGNARÖK §7iniciado!");
				Bukkit.broadcastMessage("§c§lRAGNARÖK §8» §7Para participar, use: §c/ragnarok entrar");
				Bukkit.broadcastMessage("§c§lRAGNARÖK §8» §7Para saber como o evento funciona e suas premiações, use: §c/ragnarok recompensas");
				Bukkit.broadcastMessage("§c§lRAGNARÖK §8» §c * FREE KILL RESULTARA EM BAN! *");
				Bukkit.broadcastMessage("");
			}
			if (args[0].equalsIgnoreCase("cancelar")) {
				if (!(p.hasPermission("ragnarok.admin"))) {
					p.sendMessage("§3§lString §8» §cVocê não tem permissão para executar esse comando.");
					return true;
				}
				if (Commands.acontecendo == false) {
					p.sendMessage("§c§lRAGNARÖK §8» §cO evento não está acontecendo agora!");
					return true;
				}
				Commands.acontecendo = false;
				p.sendMessage("§c§lRAGNARÖK §8» §7Você finalizou o evento §cRagnarök§7!");
				Bukkit.broadcastMessage("");
				Bukkit.broadcastMessage("§c§lRAGNARÖK §8» §7Evento §cRAGNARÖK §7finalizado!");
				Bukkit.broadcastMessage("");
			}
			if (args[0].equalsIgnoreCase("recompensas")) {
				p.sendMessage("§c§lRAGNARÖK §8» §7Premiações do evento §cRagnarök§7:");
				p.sendMessage("§fDurante o evento, cada kill será recompensada com um valor com relevancia em seu rank. Veja a seguir:");
				p.sendMessage(" * §8Membros: §7300 Reais;");
				p.sendMessage(" * §6VIPs: §7500 Reais;");
				p.sendMessage(" * §eMITO: §71000 Reais.");
				p.sendMessage("§fO evento tem duração indeterminada!");
				p.sendMessage("§fFazer FREE KILL poderá resultar em BAN!");
			}
			if (args[0].equalsIgnoreCase("entrar")) {
				if (!(Commands.acontecendo == true)) {
					p.sendMessage("§c§lRAGNARÖK §8» §cO evento não está acontecendo agora!");
					return true;
					}
				p.sendMessage("§c§lRAGNARÖK §8» §7Você entrou no evento §cRagnarök§7.");
				p.teleport(new Location(Bukkit.getWorld("ragnarok"), 405.490D, 38.0D, 1246.437D));
			}
		}
		return false;
	}
}