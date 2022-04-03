package com.github.nhojpatrick.config.core.properties.internal;

import com.github.nhojpatrick.config.core.properties.PropertiesWrapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

public class PropertiesWrapperImpl
        implements PropertiesWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesWrapperImpl.class);

    public PropertiesWrapperImpl(final String... filesAsVarArgs) {

        LOGGER.debug("PropertiesWrapperImpl('{}')", new Object[]{filesAsVarArgs});

        requireNonNull(filesAsVarArgs, "List of files required.");

        final List<String> files = asList(filesAsVarArgs)
                .stream()
                .map(p -> StringUtils.trimToNull(p))
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(files)) {
            throw new NullPointerException("List of files required.");
        }

        final Optional<Boolean> anyNull = files.stream()
                .map(p -> Objects.nonNull(p))
                .filter(p -> !p)
                .findAny();

        anyNull.ifPresent(p -> {
            throw new NullPointerException("List of files required.");
        });
    }

}
