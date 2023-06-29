package com.skitbet.redstoneer.util;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;

public class BlockUtil {

    public static ArrayList<Block> getBlocksTouchingBlock(Block center) {
        ArrayList<Block> arrayList = new ArrayList<>();
        arrayList.add(center.getRelative(BlockFace.NORTH));
        arrayList.add(center.getRelative(BlockFace.EAST));
        arrayList.add(center.getRelative(BlockFace.SOUTH));
        arrayList.add(center.getRelative(BlockFace.WEST));
        arrayList.add(center.getRelative(BlockFace.UP));
        arrayList.add(center.getRelative(BlockFace.DOWN));
        return arrayList;

    }

}
