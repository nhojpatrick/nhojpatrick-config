package com.github.nhojpatrick.config.files.internal;

import com.github.nhojpatrick.config.files.FindUtil;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

public class FindUtilImpl
        implements FindUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindUtilImpl.class);

    @Override
    public File findFile(final String... filesAsVarArgs) {
        LOGGER.debug("findFile('{}')", new Object[]{filesAsVarArgs});
        return findOptionalFile(filesAsVarArgs)
                .orElseThrow(
                        () -> new RuntimeException(String.format("No File found from '%s'", asList(filesAsVarArgs))));
    }

    @SuppressFBWarnings(value = {"FII_USE_METHOD_REFERENCE", "SLF4J_UNKNOWN_ARRAY"},
            justification = "accepted will look at changing")
    @Override
    public Optional<File> findOptionalFile(final String... filesAsVarArgs) {

        // CPD-OFF
        LOGGER.debug("findOptionalFile('{}')", filesAsVarArgs);

        requireNonNull(filesAsVarArgs, "List of files required.");

        final List<String> files = asList(filesAsVarArgs)
                .stream()
                .map(p -> StringUtils.trimToNull(p))
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(files)) {
            throw new NullPointerException("List of files required.");
        }

        final Optional<Boolean> invalids = files.stream()
                .map(p -> Objects.nonNull(p))
                .filter(p -> !p)
                .findAny();

        invalids.ifPresent(p -> {
            throw new NullPointerException("List of files required.");
        });

        LOGGER.debug("findOptionalFile('{}') files '{}'", filesAsVarArgs, files);

        final Optional<File> file = files.stream()
                .filter(Objects::nonNull)
                .map(FindUtilImpl.class.getClassLoader()::getResource)
                .filter(Objects::nonNull)
                .map(p -> {
                    try {
                        return p.toURI();
                    } catch (URISyntaxException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(Paths::get)
                .filter(Objects::nonNull)
                .map(Path::toFile)
                .filter(Objects::nonNull)
                .filter(File::isFile)
                .findFirst();

        LOGGER.debug("findOptionalFile('{}') files '{}' found '{}'", filesAsVarArgs, files, file);
        return file;
        // CPD-ON
    }

    @Override
    public InputStream findInputStream(final String... filesAsVarArgs) {
        LOGGER.debug("findInputStream('{}')", new Object[]{filesAsVarArgs});
        return findOptionalInputStream(filesAsVarArgs)
                .orElseThrow(
                        () -> new RuntimeException(String.format("No File found from '%s'", asList(filesAsVarArgs))));
    }

    @SuppressFBWarnings(value = {"FII_USE_METHOD_REFERENCE", "SLF4J_UNKNOWN_ARRAY"},
            justification = "accepted will look at changing")
    @Override
    public Optional<InputStream> findOptionalInputStream(final String... filesAsVarArgs) {

        // CPD-OFF
        LOGGER.debug("findOptionalInputStream('{}')", new Object[]{filesAsVarArgs});

        requireNonNull(filesAsVarArgs, "List of files required.");

        final List<String> files = asList(filesAsVarArgs)
                .stream()
                .map(p -> StringUtils.trimToNull(p))
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(files)) {
            throw new NullPointerException("List of files required.");
        }

        final Optional<Boolean> invalids = files.stream()
                .map(p -> Objects.nonNull(p))
                .filter(p -> !p)
                .findAny();

        invalids.ifPresent(p -> {
            throw new NullPointerException("List of files required.");
        });

        LOGGER.debug("findOptionalInputStream('{}') files '{}'", filesAsVarArgs, files);

        final Optional<InputStream> is = files.stream()
                .filter(Objects::nonNull)
                .map(FindUtilImpl.class.getClassLoader()::getResourceAsStream)
                .filter(Objects::nonNull)
                .findFirst();

        LOGGER.debug("findOptionalInputStream('{}') files '{}' found '{}'", filesAsVarArgs, files, is);
        return is;
        // CPD-ON
    }

}
