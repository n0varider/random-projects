package me.nova.plugin;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;


public class Chat implements Listener {

    Core plugin;
    public Chat(Core i) {
        plugin = i;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        e.getPlayer().getServer().broadcastMessage(
                e.getPlayer().getDisplayName() + ChatColor.WHITE + " » " + e.getMessage()
        );
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        String clr = plugin.getConfig().get(e.getPlayer().getName()).toString();
        if(clr != null) {
            e.getPlayer().setDisplayName(ChatColor.of(clr) + e.getPlayer().getName() + ChatColor.RESET);
            e.getPlayer().setPlayerListName(ChatColor.of(clr) + e.getPlayer().getName() + ChatColor.RESET);
        }else{
            e.getPlayer().setPlayerListName(e.getPlayer().getName() + ChatColor.RESET);
        }
    }
}
