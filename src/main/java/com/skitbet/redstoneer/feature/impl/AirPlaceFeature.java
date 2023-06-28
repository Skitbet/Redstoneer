package com.skitbet.redstoneer.feature.impl;

import com.skitbet.redstoneer.feature.type.AbstractFeature;
import com.skitbet.redstoneer.feature.type.FeatureData;
import com.skitbet.redstoneer.feature.type.ToggleableFeature;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@FeatureData(name = "AirPlace", description = "Allows you to place blocks in the air.", command = "airplace")
public class AirPlaceFeature extends ToggleableFeature {
    @Override
    protected void onInteractEvent(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            Player player = event.getPlayer();
            Block block = player.getTargetBlock(null, 5);
            if (event.getItem() != null || event.getItem().getType() != Material.AIR) {
                block.setType(event.getItem().getType());
            }
        }
    }
}
