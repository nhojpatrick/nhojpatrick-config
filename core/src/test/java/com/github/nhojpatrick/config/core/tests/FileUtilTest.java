package com.github.nhojpatrick.config.core.tests;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.nhojpatrick.config.core.FileUtil.safeDirectoryName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileUtilTest {

    @ParameterizedTest
    @MethodSource("safeDirectoryName_invalid_src")
    void safeDirectoryName_invalid(final String input,
                                   final String expectedMessage) {
        final Executable testMethod = () -> safeDirectoryName(input);
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo(expectedMessage))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    private static Stream<Arguments> safeDirectoryName_invalid_src() {
        return Stream.of(
                //
                Arguments.of(" ", "Invalid safeDirectoryName ' '"),
                //
                Arguments.of("  ", "Invalid safeDirectoryName '  '"),
                //
                Arguments.of("   ", "Invalid safeDirectoryName '   '"),
                //
                Arguments.of(null, "Invalid safeDirectoryName 'null'")
        );
    }

    @ParameterizedTest
    @MethodSource("safeDirectoryName_valid_src")
    void safeDirectoryName_valid(final String input,
                                 final String expected) {
        assertThat(safeDirectoryName(input), is(equalTo(expected)));
    }

    private static Stream<Arguments> safeDirectoryName_valid_src() {
        return Stream.of(
                //
                Arguments.of("qwerty ", "qwerty"),
                Arguments.of(" qwerty", "qwerty"),
                //
                Arguments.of(" qwerty ", "qwerty"),
                //
                Arguments.of("qwerty   ", "qwerty"),
                Arguments.of("   qwerty", "qwerty"),
                //
                Arguments.of("   qwerty   ", "qwerty"),
                //
                Arguments.of("qwerty", "qwerty")
        );
    }

}
