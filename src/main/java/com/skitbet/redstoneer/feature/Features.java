package com.skitbet.redstoneer.feature;

import com.skitbet.redstoneer.feature.impl.AirPlaceFeature;
import com.skitbet.redstoneer.feature.impl.AutodustFeature;
import com.skitbet.redstoneer.feature.impl.CloneColorFeature;
import com.skitbet.redstoneer.feature.type.AbstractFeature;

public enum Features {
    AUTODUST("AutoDust", new AutodustFeature()),
    CLONECOLOR("CloneColor", new CloneColorFeature()),
    AIRPLACE("AirPlace", new AirPlaceFeature());

    private final String name;
    private final AbstractFeature feature;


    Features(String name, AbstractFeature feature) {
        this.name = name;
        this.feature = feature;
    }

    public String getName() {
        return name;
    }

    public AbstractFeature getFeature() {
        return feature;
    }
}
