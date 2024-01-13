package me.tantsz.ragnarok.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import me.tantsz.ragnarok.Main;

public class Teleports {
	
	public void setLocation(String local, Location l) {
		Main.getMain().getConfig().set(String.valueOf(local) + ".World", l.getWorld().getName());
		Main.getMain().getConfig().set(String.valueOf(local) + ".X", Double.valueOf(l.getX()));
		Main.getMain().getConfig().set(String.valueOf(local) + ".Y", Double.valueOf(l.getY()));
		Main.getMain().getConfig().set(String.valueOf(local) + ".Z", Double.valueOf(l.getZ()));
		Main.getMain().getConfig().set(String.valueOf(local) + ".Yaw", Float.valueOf(l.getYaw()));
		Main.getMain().getConfig().set(String.valueOf(local) + ".Pitch", Float.valueOf(l.getPitch()));
		Main.getMain().saveConfig();
	}
	
	public void teleport(String local, Player p) {
		if (Main.getMain().getConfig().getString(String.valueOf(local) + ".World") == null) {
			p.sendMessage(Main.getConfigString("Mensagens.NaoSetado"));
			return;
		}
		World w = Bukkit.getWorld(Main.getMain().getConfig().getString(String.valueOf(local) + ".World"));
		double x = Main.getMain().getConfig().getDouble(String.valueOf(local) + ".X");
		double y = Main.getMain().getConfig().getDouble(String.valueOf(local) + ".Y");
		double z = Main.getMain().getConfig().getDouble(String.valueOf(local) + ".Z");
		float yaw = (float)Main.getMain().getConfig().getDouble(String.valueOf(local) + ".Yaw");
		float pitch = (float)Main.getMain().getConfig().getDouble(String.valueOf(local) + ".Pitch");
		p.teleport(new Location(w, x, y, z, yaw, pitch));
	}
}