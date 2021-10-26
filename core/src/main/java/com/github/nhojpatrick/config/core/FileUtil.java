package com.github.nhojpatrick.config.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public static String safeDirectoryName(final String directoryName) {

        LOGGER.debug("safeDirectoryName('{}')", directoryName);

        if (isEmpty(trimToEmpty(directoryName))) {
            throw new IllegalArgumentException(String.format("Invalid safeDirectoryName '%s'", directoryName));
        }

        final String safeDirectoryName = trimToEmpty(directoryName)
                .replaceAll("^[ -_]+", "") // remove leading ' -_'
                .replaceAll("[ -_]+$", ""); // remove trailing ' -_'

        if (isEmpty(trimToEmpty(safeDirectoryName))) {
            throw new IllegalArgumentException(String.format("Invalid safeDirectoryName '%s'", directoryName));
        }

        LOGGER.debug("safeDirectoryName('{}') return '{}'", directoryName, safeDirectoryName);
        return safeDirectoryName;
    }

}
