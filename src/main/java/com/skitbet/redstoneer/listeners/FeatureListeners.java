package com.skitbet.redstoneer.listeners;

import com.skitbet.redstoneer.RedstoneerPlugin;
import com.skitbet.redstoneer.feature.FeatureManager;
import com.skitbet.redstoneer.feature.Features;
import com.skitbet.redstoneer.feature.impl.AutodustFeature;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class FeatureListeners implements Listener {

    private final RedstoneerPlugin plugin = RedstoneerPlugin.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.getPlayerFeatureManagerMap().put(event.getPlayer().getUniqueId(), new FeatureManager(event.getPlayer()));
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        plugin.getPlayerFeatureManagerMap().get(event.getPlayer().getUniqueId()).handleBlockPlaceEvent(event);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        plugin.getPlayerFeatureManagerMap().get(event.getPlayer().getUniqueId()).handleInteractionEvent(event);
    }

}
