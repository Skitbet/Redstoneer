package com.skitbet.redstoneer.feature.impl;

import com.skitbet.redstoneer.RedstoneerPlugin;
import com.skitbet.redstoneer.feature.type.AbstractFeature;
import com.skitbet.redstoneer.feature.type.FeatureData;
import com.skitbet.redstoneer.util.BlockUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.RedstoneWire;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.ItemTagType;

import java.util.List;

@FeatureData(name = "SignalStrengthBlock", description = "Gives you a block that admits a signal strength.", command = "ss")
public class SignalStrengthBlockFeature extends AbstractFeature {


    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "You must provide a number between 1-7");
            return;
        }

        if (args[0].matches("\\d+")) {
            int length = Integer.parseInt(args[0]);
            if (length > 7 || length <= 0) {
                player.getInventory().setItemInMainHand(getBlockItemStack(length));
                player.sendMessage(ChatColor.GREEN + "You have been given a signal block of " + length);
                return;
            }
        }
        player.sendMessage(ChatColor.RED + "You must provide a number between 1-7.");

    }

    @Override
    protected void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();

        NamespacedKey key = new NamespacedKey(RedstoneerPlugin.getInstance(), "ssblock");
        ItemStack itemStack = event.getItemInHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        int length = itemMeta.getCustomTagContainer().getCustomTag(key, ItemTagType.INTEGER);

        List<Block> blocksTouching = BlockUtil.getBlocksTouchingBlock(block);
        blocksTouching.forEach(b -> {
            if (b.getType() == Material.REDSTONE_WIRE) {
                RedstoneWire redstoneWire = (RedstoneWire) block.getBlockData();
                redstoneWire.setPower(length);
                b.setBlockData(redstoneWire);
            }
        });

    }

    public ItemStack getBlockItemStack(int length) {
        ItemStack itemStack = new ItemStack(Material.BARREL, 1);
        ItemMeta meta = itemStack.getItemMeta();
        NamespacedKey key = new NamespacedKey(RedstoneerPlugin.getInstance(), "ssblock");

        meta.getCustomTagContainer().setCustomTag(key, ItemTagType.INTEGER, length);

        itemStack.setItemMeta(meta);
        return itemStack;
    }

}
