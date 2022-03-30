package com.github.nhojpatrick.config.core.files.internal;

import com.github.nhojpatrick.config.core.files.FindUtil;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

public class FindUtilImpl
        implements FindUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindUtilImpl.class);

    @SuppressFBWarnings(value = {"FII_USE_METHOD_REFERENCE", "SLF4J_UNKNOWN_ARRAY"},
            justification = "ccepted will look at changing")
    public Optional<InputStream> findFirstFileAsStream(final String... filesAsVarArgs) {

        LOGGER.debug("findFirstFileAsStream('{}')", new Object[]{filesAsVarArgs});

        requireNonNull(filesAsVarArgs, "List of files required.");

        final List<String> files = asList(filesAsVarArgs);

        final Optional<Boolean> anyNull = files.stream()
                .map(Objects::nonNull)
                .filter(p -> p)
                .findAny();
        anyNull
                .orElseThrow(() -> new NullPointerException("List of files required."));

        LOGGER.debug("findFirstFileStream('{}') files '{}'", filesAsVarArgs, files);

        final Optional<InputStream> is = files.stream()
                .filter(Objects::nonNull)
                .map(FindUtilImpl.class.getClassLoader()::getResourceAsStream)
                .filter(Objects::nonNull)
                .findFirst();
//                .orElseThrow(() -> new RuntimeException(String.format("No File found from '%s'", files)));

        LOGGER.debug("findFirstFileAsStream('{}') files '{}' found '{}'", filesAsVarArgs, files, is);
        return is;
    }

}
