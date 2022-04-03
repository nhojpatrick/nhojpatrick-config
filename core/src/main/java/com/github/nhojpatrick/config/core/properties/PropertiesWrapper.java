package com.github.nhojpatrick.config.core.properties;

import com.github.nhojpatrick.config.core.properties.internal.PropertiesWrapperImpl;

public interface PropertiesWrapper {

    static PropertiesWrapper getDefault() {
        return new PropertiesWrapperImpl();
    }

}
