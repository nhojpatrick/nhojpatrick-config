package com.github.nhojpatrick.config.core.files;

import com.github.nhojpatrick.config.core.files.internal.FindUtilImpl;

import java.io.InputStream;
import java.util.Optional;

public interface FindUtil {

    default FindUtil getInstance() {
        return new FindUtilImpl();
    }

    Optional<InputStream> findFirstFileAsStream(String... filesAsVarArgs);

}
