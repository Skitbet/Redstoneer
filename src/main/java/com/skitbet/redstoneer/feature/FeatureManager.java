package com.skitbet.redstoneer.feature;

import com.skitbet.redstoneer.RedstoneerPlugin;
import com.skitbet.redstoneer.feature.type.AbstractFeature;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;

public class FeatureManager implements CommandExecutor {

    private Map<String, AbstractFeature> featuresMap = new HashMap<>();

    private final Player player;
    private final RedstoneerPlugin plugin = RedstoneerPlugin.getInstance();

    public FeatureManager(Player player) {
        this.player = player;

        for (Features feature : Features.values()) {
            register(feature);
        }

        registerCommands();
    }

    public void register(Features feature) {
        featuresMap.put(feature.getName(), feature.getFeature());
    }

    public void registerCommands() {
        for (AbstractFeature abstractFeature : featuresMap.values()) {
            plugin.getCommand(abstractFeature.getCommandName()).setExecutor(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandName, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Redstoneer commands can only be ran by players.");
            return true;
        }

        for (AbstractFeature abstractFeature : featuresMap.values()) {
            if (commandName.equals(abstractFeature.getCommandName())) {
                abstractFeature.onCommand(player, args);
            }
        }

        return true;
    }

    public AbstractFeature getFeatureByName(String name) {
        System.out.println(featuresMap.toString());
        return featuresMap.getOrDefault(name, null);
    }

    public void handleBlockPlaceEvent(BlockPlaceEvent event) {
        featuresMap.values().forEach(abstractFeature -> abstractFeature.handleBlockPlace(event));
    }

    public void handleInteractionEvent(PlayerInteractEvent event) {
        featuresMap.values().forEach(abstractFeature -> abstractFeature.handleInteraction(event));
    }
}
