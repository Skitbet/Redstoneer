package com.skitbet.redstoneer.feature.type;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public abstract class AbstractFeature {

    private final FeatureData data;
    private final String id;

    public boolean toggled;

    public AbstractFeature() {
        this.data = getClass().getAnnotation(FeatureData.class);

        if (data == null) {
            throw new IllegalStateException("Feature " + getClass() + " does not have a FeatureData annotation.");
        }

        this.id = data.name().toLowerCase().replace(" ", "");
    }

    public abstract void onCommand(Player player, String[] args);
    protected void onBlockPlace(BlockPlaceEvent event) {}
    protected void onInteractEvent(PlayerInteractEvent event) {}

    public void handleBlockPlace(BlockPlaceEvent event) {
        if (toggled) {
            onBlockPlace(event);
        }
    }

    public void handleInteraction(PlayerInteractEvent event) {
        if (toggled) {
            onInteractEvent(event);
        }
    }

    public String getName() {
        return data.name();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return data.description();
    }

    public String getCommandName() {
        return data.command();
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

}
