package com.skitbet.redstoneer.feature.impl;

import com.skitbet.redstoneer.feature.type.AbstractFeature;
import com.skitbet.redstoneer.feature.type.FeatureData;
import com.skitbet.redstoneer.feature.type.ToggleableFeature;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;

@FeatureData(name = "AutoDust", description = "Places redstone dust on top of blocks you place.", command = "autodust")
public class AutodustFeature extends ToggleableFeature {

    @Override
    protected void onBlockPlace(BlockPlaceEvent event) {
        Location redstoneDustLocation = event.getBlock().getLocation().add(0, 1, 0);
        redstoneDustLocation.getBlock().setType(Material.REDSTONE_WIRE);
    }
}
