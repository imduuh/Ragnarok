package me.tantsz.ragnarok;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import me.tantsz.ragnarok.commands.Commands;
import me.tantsz.ragnarok.listeners.Listeners;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {

	public static Economy eco;
	
	@Override
	public void onEnable() {
		setupEconomy();
		getCommand("ragnarok").setExecutor(new Commands());
		Bukkit.getPluginManager().registerEvents((Listener)new Listeners(), (Plugin)this);
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§cRagnarok: §aPlugin habilitado!");
		Bukkit.getConsoleSender().sendMessage("§cAuthor: §fTantsZ");
		Bukkit.getConsoleSender().sendMessage("§cVersao: §f1.0");
		Bukkit.getConsoleSender().sendMessage("");
		}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§cRagnarok: §4Plugin desabilitado!");
		Bukkit.getConsoleSender().sendMessage("§cAuthor: §fTantsZ");
		Bukkit.getConsoleSender().sendMessage("§cVersao: §f1.0");
		Bukkit.getConsoleSender().sendMessage("");
	}
	
	private boolean setupEconomy() {
		if (Bukkit.getPluginManager().getPlugin("Vault") == null)
			return false; 
		RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
		if (rsp != null) {
			Main.eco = (Economy)rsp.getProvider();
			return true;
			} 
		return false;
		}
	}
