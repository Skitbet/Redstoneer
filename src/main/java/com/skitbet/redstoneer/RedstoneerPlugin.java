package com.skitbet.redstoneer;

import com.skitbet.redstoneer.feature.FeatureManager;
import com.skitbet.redstoneer.listeners.FeatureListeners;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RedstoneerPlugin extends JavaPlugin {

    private static RedstoneerPlugin instance;
    public Map<UUID, FeatureManager> playerFeatureManagerMap = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new FeatureListeners(), this);

        for (Player onlinePlayer : getServer().getOnlinePlayers()) {
            playerFeatureManagerMap.put(onlinePlayer.getUniqueId(), new FeatureManager(onlinePlayer));
        }
    }

    public static RedstoneerPlugin getInstance() {
        return instance;
    }

    public Map<UUID, FeatureManager> getPlayerFeatureManagerMap() {
        return playerFeatureManagerMap;
    }
}