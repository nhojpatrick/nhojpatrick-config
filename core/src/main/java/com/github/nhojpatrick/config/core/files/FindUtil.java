package com.github.nhojpatrick.config.core.files;

import com.github.nhojpatrick.config.core.files.internal.FindUtilImpl;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

public interface FindUtil {

    static FindUtil getDefault() {
        return new FindUtilImpl();
    }

    /**
     * Find {@link java.io.File} by searching list of {@code filePaths}.
     *
     * @param filePaths a list of potential files.
     * @return the {@link java.io.File} if found, otherwise throw {@link java.lang.RuntimeException}.
     * @throws RuntimeException if file not found.
     */
    File findFile(String... filePaths);

    /**
     * Find optional {@link java.io.File} by searching list of {@code filePaths}.
     *
     * @param filePaths a list of potential files.
     * @return the {@link java.io.File} if found, otherwise {@link java.util.Optional#empty()}.
     */
    Optional<File> findOptionalFile(String... filePaths);

    /**
     * Find {@link java.io.InputStream} by searching list of {@code filePaths}.
     *
     * @param filePaths a list of potential files.
     * @return the {@link java.io.InputStream} if found, otherwise throw {@link java.lang.RuntimeException}.
     * @throws RuntimeException if file not found.
     */
    InputStream findInputStream(String... filePaths);

    /**
     * Find optional {@link java.io.InputStream} by searching list of {@code filePaths}.
     *
     * @param filePaths a list of potential files.
     * @return the {@link java.io.InputStream} if found, otherwise {@link java.util.Optional#empty()}.
     */
    Optional<InputStream> findOptionalInputStream(String... filePaths);

}
