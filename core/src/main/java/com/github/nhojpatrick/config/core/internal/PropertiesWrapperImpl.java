package com.github.nhojpatrick.config.core;

import org.apache.commons.configuration2.CombinedConfiguration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class PropertiesWrapper {

    private CombinedConfiguration config;

    public PropertiesWrapper(final String... fileVarArgs)
            throws ConfigurationException,
            URISyntaxException {

        this.config = new CombinedConfiguration();

        final Parameters params = new Parameters();

        final List<String> files = asList(fileVarArgs);

        for (final String fileAsStr : files) {

            final URL fileAsUrl = PropertiesWrapper.class.getResource(fileAsStr);
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
            final FileBasedConfigurationBuilder<FileBasedConfiguration> fbcb = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                    .configure(pbp);

            this.config.addConfiguration(fbcb.getConfiguration());
        }
    }

}
