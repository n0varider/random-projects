package me.nova.plugin;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Core extends JavaPlugin {

    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        getCommand("color").setExecutor(new ColorCMD(this));
        getServer().getPluginManager().registerEvents(new Chat(this), this);
        System.out.println("The Cool Squad Is Here Boys");

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for(Player p : getServer().getOnlinePlayers()) {
                p.setPlayerListHeaderFooter(
                        ChatColor.translateAlternateColorCodes('&', "&bSMP\n"),
                        ChatColor.translateAlternateColorCodes('&', "\n&6" + getServer().getOnlinePlayers().size() + "&b players online"
                ));
            }
        }, 0, 100);
    }

    public void onDisable() {
      // nothing 
    }
}
