package me.tantsz.ragnarok.commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.tantsz.ragnarok.Main;
import me.tantsz.ragnarok.listeners.Listeners;

public class Commands implements CommandExecutor {
	
	public static boolean acontecendo = false;
	public static List<Player> participantes = new ArrayList<>();
	
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ragnarok" )) {
			if (!(s instanceof Player)) {
				s.sendMessage("§cEste comando só pode ser executado IN-GAME.");
				return true;
			}
			Player p = (Player)s;
			if (args.length != 1) {
				sendMessageHelp(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("iniciar")) {
				startEvent(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("cancelar")) {
				stopEvent(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("finalizar")) {
				finishEvent(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("setarena")) {
				setArena(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("setsaida")) {
				setExit(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("info")) {
				sendMessageInfo(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("topkills")) {
				sendTopKills(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("entrar")) {
				joinEvent(p);
				return true;
			}
		}
		return false;
	}
	
	private void sendMessageHelp(Player p) {
		List<String> list = Main.getMain().getConfig().getStringList("Mensagens.Ajuda");
		for (String str: list) {
			p.sendMessage(str.replace("&","§"));
		};
		if (p.hasPermission("ragnarok.admin")) {
			List<String> listadmin = Main.getMain().getConfig().getStringList("Mensagens.AjudaAdmin");
			for (String str: listadmin) {
				p.sendMessage(str.replace("&","§"));
			}
		}
	}
	
	private void startEvent(Player p) {
		if (p.hasPermission("ragnarok.admin")) {
			if (!acontecendo) {
				if (Main.getConfigString("arena.World") != null && Main.getConfigString("saida.World") != null) {
					acontecendo = true;
					p.sendMessage(Main.getConfigString("Mensagens.Iniciou"));
					List<String> list = Main.getMain().getConfig().getStringList("Mensagens.Iniciado");
					for (String str: list) {
						Bukkit.broadcastMessage(str.replace("&","§"));
					}
				} else {
					p.sendMessage(Main.getConfigString("Mensagens.LocaisNaoDefinidos"));
				}
			} else {
				p.sendMessage(Main.getConfigString("Mensagens.JaAcontecendo"));
			}
		} else {
			p.sendMessage(Main.getConfigString("Mensagens.SemPermissao"));
		}
	}
	
	private void stopEvent(Player p) {
		if (p.hasPermission("ragnarok.admin")) {
			if (acontecendo) {
				acontecendo = false;
				participantes.clear();
				Listeners.kills.clear();
				p.sendMessage(Main.getConfigString("Mensagens.Cancelou"));
				List<String> list = Main.getMain().getConfig().getStringList("Mensagens.Cancelado");
				for (String str: list) {
					Bukkit.broadcastMessage(str.replace("&","§"));
				}
			} else {
				p.sendMessage(Main.getConfigString("Mensagens.NaoAcontecendo"));
			}
		} else {
			p.sendMessage(Main.getConfigString("Mensagens.SemPermissao"));
		}
	}
	
	private void finishEvent(Player p) {
		if (p.hasPermission("ragnarok.admin")) {
			if (acontecendo) {
				acontecendo = false;
				participantes.clear();
				String[] playerKillsInfo = Listeners.getPlayerWithMaxKills();
				p.sendMessage(Main.getConfigString("Mensagens.Finalizou"));
				List<String> list = Main.getMain().getConfig().getStringList("Mensagens.Finalizado");
				for (String str: list) {
					Bukkit.broadcastMessage(str.replace("&","§").replace("{jogador}", playerKillsInfo[0]).replace("{kills}", playerKillsInfo[1]));
				}
				Listeners.kills.clear();
			} else {
				p.sendMessage(Main.getConfigString("Mensagens.NaoAcontecendo"));
			}
		} else {
			p.sendMessage(Main.getConfigString("Mensagens.SemPermissao"));
		}
	}
	
	private void setArena(Player p) {
		if (p.hasPermission("ragnarok.admin")) {
			Main.getMain().getTeleportUtil().setLocation("arena", p.getLocation());
			p.sendMessage(Main.getConfigString("Mensagens.EntradaDefinida"));
		} else {
			p.sendMessage(Main.getConfigString("Mensagens.SemPermissao"));
		}
	}
	
	private void setExit(Player p) {
		if (p.hasPermission("ragnarok.admin")) {
			Main.getMain().getTeleportUtil().setLocation("saida", p.getLocation());
			p.sendMessage(Main.getConfigString("Mensagens.SaidaDefinida"));
		} else {
			p.sendMessage(Main.getConfigString("Mensagens.SemPermissao"));
		}
	}
	
	private void sendMessageInfo(Player p) {
		List<String> list = Main.getMain().getConfig().getStringList("Mensagens.Info");
		for (String str: list) {
			p.sendMessage(str.replace("&","§"));
		}
	}
	
	private void sendTopKills(Player p) {
		if (Main.getMain().getConfig().getBoolean("MySQL.Ativado")) {
			Main.getMain().getMysql().getTopKills(p);
		} else {
			Main.getMain().getsql().getTopKills(p);
		}
	}
	
	private void joinEvent(Player p) {
		if (acontecendo) {
			if (!participantes.contains(p)) {
				p.sendMessage(Main.getConfigString("Mensagens.Entrou"));
				Main.getMain().getTeleportUtil().teleport("arena", p);
				participantes.add(p);
			} else {
				p.sendMessage(Main.getConfigString("Mensagens.jaParticipando"));
			}
		} else {
			p.sendMessage(Main.getConfigString("Mensagens.NaoAcontecendo"));
		}		
	}
	
}