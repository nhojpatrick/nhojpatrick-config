package com.github.nhojpatrick.config.core.files;

import com.github.nhojpatrick.config.core.files.internal.FindUtilImpl;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

public interface FindUtil {

    static FindUtil getDefault() {
        return new FindUtilImpl();
    }

    File findFirstFileAsFile(String... filesAsVarArgs);

    Optional<File> findFirstFileAsOptionalFile(String... filesAsVarArgs);

    Optional<InputStream> findFirstFileAsOptionalStream(String... filesAsVarArgs);

}
