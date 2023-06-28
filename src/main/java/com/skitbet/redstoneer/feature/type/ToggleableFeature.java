package com.skitbet.redstoneer.feature.type;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ToggleableFeature extends AbstractFeature{
    @Override
    public void onCommand(Player player, String[] args) {
        toggled = !toggled;
        if (toggled) {
            player.sendMessage(ChatColor.GREEN + "Enabled " + getName());
        }else{
            player.sendMessage(ChatColor.RED + "Disabled " + getName());
        }
    }
}
