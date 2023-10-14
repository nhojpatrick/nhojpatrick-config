package com.github.nhojpatrick.config.properties.internal;

import com.github.nhojpatrick.config.properties.PropertiesWrapper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.configuration2.CombinedConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.tree.MergeCombiner;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

public class PropertiesWrapperImpl
        implements PropertiesWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesWrapperImpl.class);

    private final Configuration config;

    @SuppressFBWarnings(value = {"CT_CONSTRUCTOR_THROW"},
            justification = "accepted")
    public PropertiesWrapperImpl(final String... filesAsVarArgs)
            throws ConfigurationException,
            URISyntaxException {

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

        final CombinedConfiguration config = new CombinedConfiguration(new MergeCombiner());

        final Parameters params = new Parameters();

        for (final String fileAsStr : files) {

            final URL fileAsUrl = loadAsURL(fileAsStr);
            if (fileAsUrl == null) {
                continue;
            }

            final File file = Paths.get(fileAsUrl.toURI())
                    .toFile();
            if (!file.isFile()) {
                continue;
            }

            final String fileAsAbsolutePath = file.getAbsolutePath();

            final PropertiesBuilderParameters pbp = params.properties()
                    .setFileName(fileAsAbsolutePath);
            final FileBasedConfigurationBuilder<FileBasedConfiguration> fbcb
                    = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                    .configure(pbp);

            config.addConfiguration(fbcb.getConfiguration());
        }

        this.config = config;
    }

    /**
     * Pre JPMS vs Post JPMS logic needs to be handled here.
     *
     * @param fileAsStr the source file.
     * @return the URL.
     */
    private URL loadAsURL(String fileAsStr) {
        LOGGER.debug("loadAsURL('{}')", fileAsStr);
        URL url = PropertiesWrapperImpl.class.getResource(fileAsStr);
        if (url == null
                && fileAsStr != null
                && fileAsStr.startsWith("/")) {
            final String fileAsStrJPMS = fileAsStr.substring(1, fileAsStr.length());
            LOGGER.debug("loadAsURL('{}') trying '{}'", fileAsStr, fileAsStrJPMS);
            url = ClassLoader.getSystemResource(fileAsStrJPMS);
        }
        LOGGER.debug("loadAsURL('{}') url='{}'", fileAsStr, url);
        return url;
    }

    @Override
    public boolean lookupBoolean(final String key) {
        LOGGER.debug("lookupBoolean('{}')", key);
        final boolean value = this.config.getBoolean(key);
        LOGGER.debug("lookupBoolean('{}') value='{}'", key, value);
        return value;
    }

    @Override
    public Boolean lookupBoolean(final String key, final Boolean defaultValue) {
        LOGGER.debug("lookupBoolean('{}', '{}')", key, defaultValue);
        final boolean value = this.config.getBoolean(key, defaultValue);
        LOGGER.debug("lookupBoolean('{}', '{}') value='{}'", key, defaultValue, value);
        return value;
    }

    @Override
    public int lookupInt(final String key) {
        LOGGER.debug("lookupInt('{}')", key);
        final int value = this.config.getInt(key);
        LOGGER.debug("lookupInt('{}') value='{}'", key, value);
        return value;
    }

    @Override
    public int lookupInt(final String key, final int defaultValue) {
        LOGGER.debug("lookupInt('{}', '{}')", key, defaultValue);
        final int value = this.config.getInt(key, defaultValue);
        LOGGER.debug("lookupInt('{}', '{}') value='{}'", key, defaultValue, value);
        return value;
    }

    @Override
    public Integer lookupInteger(final String key, final Integer defaultValue) {
        LOGGER.debug("lookupInteger('{}', '{}')", key, defaultValue);
        final Integer value = this.config.getInteger(key, defaultValue);
        LOGGER.debug("lookupInteger('{}', '{}') value='{}'", key, defaultValue, value);
        return value;
    }

    @Override
    public <T> Map<String, T> lookupMap(final String key, T clazz) {
        LOGGER.debug("lookupMap('{}', '{}')", key, clazz);
        final Map<String, T> map = new LinkedHashMap<>();
        final Configuration subset = config.subset(key);
        if (!subset.isEmpty()) {
            final Iterator it = subset.getKeys();
            while (it.hasNext()) {
                final String k = (String) it.next();
                final T v = (T) subset.getProperty(k);
                map.put(k, v);
            }
        }
        LOGGER.debug("lookupMap('{}', '{}') map='{}'", key, clazz, map);
        return map;
    }

    @Override
    public String lookupString(final String key) {
        LOGGER.debug("lookupString('{}')", key);
        final String value = this.config.getString(key);
        LOGGER.debug("lookupString('{}') value='{}'", key, value);
        return value;
    }

    @Override
    public String lookupString(final String key, final String defaultValue) {
        LOGGER.debug("lookupString('{}', '{}')", key, defaultValue);
        final String value = this.config.getString(key, defaultValue);
        LOGGER.debug("lookupString('{}', '{}') value='{}'", key, defaultValue, value);
        return value;
    }

    @Override
    public List<String> lookupStrings(final String key) {
        LOGGER.debug("lookupStrings('{}')", key);
        final List<String> value = this.config.getList(String.class, key);
        LOGGER.debug("lookupStrings('{}') value='{}'", key, value);
        return value;
    }

}
