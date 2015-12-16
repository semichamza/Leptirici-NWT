package com.nwt.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by glasshark on 30-Mar-15.
 */
public class ConfigProperties extends ArrayList<ConfigProperty> {
    public ConfigProperties() {
        super();
    }

    public ConfigProperties(Collection<? extends ConfigProperty> c) {
        super(c);
    }

    public List<ConfigProperty> getConfigProperties() {
        return this;
    }

    public void setConfigProperties(List<ConfigProperty> configProperties) {
        this.addAll(configProperties);
    }
}
