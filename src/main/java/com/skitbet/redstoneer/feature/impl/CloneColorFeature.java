package com.skitbet.redstoneer.feature.impl;

import com.skitbet.redstoneer.feature.type.AbstractFeature;
import com.skitbet.redstoneer.feature.type.FeatureData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@FeatureData(name = "CloneColor", description = "Lets you clone stained glass as colored concrete and vise versa.", command = "clonecolor")
public class CloneColorFeature extends AbstractFeature {
    @Override
    public void onCommand(Player player, String[] args) {
        Block block = player.getTargetBlock(null, 5);
        if (isStainedGlass(block.getType().name())) {
            player.getInventory().setItemInMainHand(getConcreteItem(block));
            player.sendMessage(ChatColor.GREEN + "Created concrete with same color.");
            return;
        }
        else if (isConcrete(block.getType().name())) {
            player.getInventory().setItemInMainHand(getStainedGlassItem(block));
            player.sendMessage(ChatColor.GREEN + "Created stained glass with same color.");
            return;
        }
        player.sendMessage(ChatColor.RED + "Cannot clone the color for the block: " + block.getType().name());
    }

    private boolean isStainedGlass(String name) {
        return name.endsWith("_STAINED_GLASS");
    }

    private boolean isConcrete(String name) {
        return name.endsWith("_CONCRETE");
    }

    public ItemStack getConcreteItem(Block stainedGlass) {
        String oldMaterial = stainedGlass.getType().name();
        String concreteMaterial = "_CONCRETE";

        int colorEndIndex = oldMaterial.lastIndexOf("_STAINED");
        String color = oldMaterial.substring(0, colorEndIndex);

        String newMaterialName = color + concreteMaterial;
        return new ItemStack(Material.valueOf(newMaterialName), 1);
    }
    public ItemStack getStainedGlassItem(Block concrete) {
        String oldMaterial = concrete.getType().name();
        String stainedMaterial = "_STAINED_GLASS";

        int colorEndIndex = oldMaterial.lastIndexOf('_');
        String color = oldMaterial.substring(0, colorEndIndex);

        String newMaterialName = color + stainedMaterial;
        return new ItemStack(Material.valueOf(newMaterialName), 1);
    }
}
