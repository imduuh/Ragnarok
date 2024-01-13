package me.tantsz.ragnarok;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import me.tantsz.ragnarok.commands.Commands;
import me.tantsz.ragnarok.dbmanager.MySQL;
import me.tantsz.ragnarok.dbmanager.SQLite;
import me.tantsz.ragnarok.listeners.Listeners;
import me.tantsz.ragnarok.utils.Teleports;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	public static Main main;
	public Teleports teleportUtil;
	public static Economy eco;
	private SQLite sqlite;
	private MySQL mysql;
	
	public MySQL getMysql() {
		return mysql;
		}
	
	public SQLite getsql() {
		return sqlite;
		}
	
	public static Main getMain() {
		return main;
		}
	
	public Teleports getTeleportUtil() {
		return this.teleportUtil;
		}
	
	public static String getConfigString(String msg) {
		return main.getConfig().getString(msg).replace("&", "§");
		}
	
	@Override
	public void onEnable() {
		setupEconomy();
		main = this;
		teleportUtil = new Teleports();
		getCommand("ragnarok").setExecutor(new Commands());
		if (!(new File(getDataFolder(), "config.yml")).exists()) {
			saveDefaultConfig();
		}
		Bukkit.getPluginManager().registerEvents((Listener)new Listeners(), (Plugin)this);
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§cRagnarok: §aPlugin habilitado!");
		Bukkit.getConsoleSender().sendMessage("§cAuthor: §fTantsZ");
		Bukkit.getConsoleSender().sendMessage("§cVersao: §f1.0");
		Bukkit.getConsoleSender().sendMessage("");
		File database;
		if (!(database = new File(getDataFolder() + File.separator + "database.db")).exists()) {
			try {
				database.createNewFile();
				}
			catch (IOException e) {
				e.printStackTrace();
				} 
			}
		if (getConfig().getBoolean("MySQL.Ativado")) {
			mysql = new MySQL(getConfig().getString("MySQL.Usuario"), getConfig().getString("MySQL.Senha"), getConfig().getString("MySQL.Database"), getConfig().getString("MySQL.Host"));
			} else {
				sqlite = new SQLite();
			} 
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
		if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
			return false; 
		}
		RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
		if (rsp != null) {
			Main.eco = (Economy)rsp.getProvider();
			return true;
		} 
		return false;
	}
}