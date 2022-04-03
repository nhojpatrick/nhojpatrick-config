package com.github.nhojpatrick.config.core.properties.internal;

import com.github.nhojpatrick.config.core.properties.PropertiesWrapper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

public class PropertiesWrapperImpl
        implements PropertiesWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesWrapperImpl.class);

    public PropertiesWrapperImpl() {

        final String[] filesAsVarArgs = null;

        LOGGER.debug("PropertiesWrapperImpl('{}')", filesAsVarArgs);

        requireNonNull(filesAsVarArgs, "List of files required.");

        final List<String> files = asList(filesAsVarArgs);

        if (CollectionUtils.isEmpty(files)) {
            throw new NullPointerException("List of files required.");
        }
    }

}
