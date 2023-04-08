package me.nova.plugin;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ColorCMD implements CommandExecutor {

    Core plugin;
    public ColorCMD(Core instance) {
        plugin = instance;
    }
    // »»
    public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
        if(c.getName().equalsIgnoreCase("color")) {
            Player p = (Player) s;
            if(args.length > 0) {
                p.setDisplayName(ChatColor.of(args[0]) + p.getName() + ChatColor.RESET);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        "&l»&r &aYour name now looks like " + ChatColor.of(args[0]) + p.getName()
                        ));
                plugin.getConfig().set(p.getName(), args[0]);
                plugin.saveConfig();
                p.setPlayerListName(ChatColor.of(args[0]) + p.getName() + ChatColor.RESET);
                return true;
            }
            p.sendMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            """
                                    &l»&r &cPlease provide a hex color or a proper color.&r
                                    &l»&r &cValid Colors:
                                    &0black &1dark_blue &2dark_green &3dark_aqua &4dark_red
                                    &5dark_purple &6gold &7gray &8dark_gray
                                    &9blue &agreen &baqua &cred
                                    &dlight_purple &eyellow &fwhite"""
                    ));
        }
        return false;
    }
}
