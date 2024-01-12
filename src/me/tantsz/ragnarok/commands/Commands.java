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
					p.sendMessage("�4�lRAGNAR�K �8� �7Comandos do evento:");
					p.sendMessage("�c/ragnarok entrar �f- �7Entra no evento.");
					p.sendMessage("�c/ragnarok recompensas �f- �7Mostra as recompensas do evento.");
					p.sendMessage("�c/ragnarok iniciar �f- �7Inicia o evento.");
					p.sendMessage("�c/ragnarok cancelar �f- �7Cancela o evento.");
				} else {
					p.sendMessage("�4�lRAGNAR�K �8� �7Comandos do evento:");
					p.sendMessage("�c/ragnarok entrar �f- �7Entra no evento.");
					p.sendMessage("�c/ragnarok topkills �f- �7Mostro o TOP com os jogadores que mais mataram no evento.");
					p.sendMessage("�c/ragnarok recompensas �f- �7Mostra as recompensas do evento.");
				}
			}
			if (args[0].equalsIgnoreCase("iniciar")) {
				if (!(p.hasPermission("ragnarok.admin"))) {
					p.sendMessage("�3�lString �8� �cVoc� n�o tem permiss�o para executar esse comando.");
					return true;
				}
				if (Commands.acontecendo == true) {
					p.sendMessage("�c�lRAGNAR�K �8� �cO evento j� est� acontecendo!");
					return true;
				}
				Commands.acontecendo = true;
				p.sendMessage("�c�lRAGNAR�K �8� �7Voc� iniciou o evento �cRagnar�k�7!");
				Bukkit.broadcastMessage("");
				Bukkit.broadcastMessage("�c�lRAGNAR�K �8� �7Evento �cRAGNAR�K �7iniciado!");
				Bukkit.broadcastMessage("�c�lRAGNAR�K �8� �7Para participar, use: �c/ragnarok entrar");
				Bukkit.broadcastMessage("�c�lRAGNAR�K �8� �7Para saber como o evento funciona e suas premia��es, use: �c/ragnarok recompensas");
				Bukkit.broadcastMessage("�c�lRAGNAR�K �8� �c * FREE KILL RESULTARA EM BAN! *");
				Bukkit.broadcastMessage("");
			}
			if (args[0].equalsIgnoreCase("cancelar")) {
				if (!(p.hasPermission("ragnarok.admin"))) {
					p.sendMessage("�3�lString �8� �cVoc� n�o tem permiss�o para executar esse comando.");
					return true;
				}
				if (Commands.acontecendo == false) {
					p.sendMessage("�c�lRAGNAR�K �8� �cO evento n�o est� acontecendo agora!");
					return true;
				}
				Commands.acontecendo = false;
				p.sendMessage("�c�lRAGNAR�K �8� �7Voc� finalizou o evento �cRagnar�k�7!");
				Bukkit.broadcastMessage("");
				Bukkit.broadcastMessage("�c�lRAGNAR�K �8� �7Evento �cRAGNAR�K �7finalizado!");
				Bukkit.broadcastMessage("");
			}
			if (args[0].equalsIgnoreCase("recompensas")) {
				p.sendMessage("�c�lRAGNAR�K �8� �7Premia��es do evento �cRagnar�k�7:");
				p.sendMessage("�fDurante o evento, cada kill ser� recompensada com um valor com relevancia em seu rank. Veja a seguir:");
				p.sendMessage(" * �8Membros: �7300 Reais;");
				p.sendMessage(" * �6VIPs: �7500 Reais;");
				p.sendMessage(" * �eMITO: �71000 Reais.");
				p.sendMessage("�fO evento tem dura��o indeterminada!");
				p.sendMessage("�fFazer FREE KILL poder� resultar em BAN!");
			}
			if (args[0].equalsIgnoreCase("entrar")) {
				if (!(Commands.acontecendo == true)) {
					p.sendMessage("�c�lRAGNAR�K �8� �cO evento n�o est� acontecendo agora!");
					return true;
					}
				p.sendMessage("�c�lRAGNAR�K �8� �7Voc� entrou no evento �cRagnar�k�7.");
				p.teleport(new Location(Bukkit.getWorld("ragnarok"), 405.490D, 38.0D, 1246.437D));
			}
		}
		return false;
	}
}