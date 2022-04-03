package com.github.nhojpatrick.config.core.properties;

import com.github.nhojpatrick.config.core.properties.internal.PropertiesWrapperImpl;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.net.URISyntaxException;

public interface PropertiesWrapper {

    static PropertiesWrapper getDefault(final String... filesAsVarArgs)
            throws ConfigurationException,
            URISyntaxException {
        return new PropertiesWrapperImpl(filesAsVarArgs);
    }

}
