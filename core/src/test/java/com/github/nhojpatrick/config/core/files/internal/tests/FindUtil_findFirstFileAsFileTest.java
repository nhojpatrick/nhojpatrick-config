package com.github.nhojpatrick.config.core.files.internal.tests;

import com.github.nhojpatrick.config.core.files.internal.FindUtilImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindUtil_findFirstFileAsFileTest {

    private FindUtilImpl cut;

    @BeforeEach
    public void beforeEach() {
        this.cut = new FindUtilImpl();
    }

    @Test
    @DisplayName("Input empty")
    public void input_empty() {
        final Executable testMethod = () -> this.cut.findFirstFileAsFile();
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("List of files required."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    @DisplayName("Input null single")
    public void input_null_single() {
        final Executable testMethod = () -> this.cut.findFirstFileAsFile(
                null
        );
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("List of files required."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    @DisplayName("Input null multiple")
    public void input_null_multiples() {
        final Executable testMethod = () -> this.cut.findFirstFileAsFile(
                null,
                null
        );
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("List of files required."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    @DisplayName("Input null leading")
    public void input_null_leading() {
        final Executable testMethod = () -> this.cut.findFirstFileAsFile(
                null,
                "is-not-empty"
        );
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("List of files required."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    @DisplayName("Input null trailing")
    public void input_null_trailing() {
        final Executable testMethod = () -> this.cut.findFirstFileAsFile(
                "is-not-empty",
                null
        );
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("List of files required."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    @DisplayName("Input null middle")
    public void input_null_middle() {
        final Executable testMethod = () -> this.cut.findFirstFileAsFile(
                "is-not-empty",
                null,
                "is-not-empty"
        );
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("List of files required."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    @DisplayName("Input empty single")
    public void input_empty_single() {
        final Executable testMethod = () -> this.cut.findFirstFileAsFile(
                ""
        );
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("List of files required."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    @DisplayName("Input empty multiple")
    public void input_empty_multiples() {
        final Executable testMethod = () -> this.cut.findFirstFileAsFile(
                "",
                ""
        );
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("List of files required."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    @DisplayName("Input empty leading")
    public void input_empty_leading() {
        final Executable testMethod = () -> this.cut.findFirstFileAsFile(
                "",
                "is-not-empty"
        );
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("List of files required."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    @DisplayName("Input empty trailing")
    public void input_empty_trailing() {
        final Executable testMethod = () -> this.cut.findFirstFileAsFile(
                "is-not-empty",
                ""
        );
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("List of files required."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    @DisplayName("Input empty middle")
    public void input_empty_middle() {
        final Executable testMethod = () -> this.cut.findFirstFileAsFile(
                "is-not-empty",
                "",
                "is-not-empty"
        );
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("List of files required."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    @DisplayName("Input unknown single")
    public void input_unknown() {
        final Executable testMethod = () -> this.cut.findFirstFileAsFile(
                "com/github/nhojpatrick/config/core/files/tests/unknown.file"
        );
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("No File found from '[com/github/nhojpatrick/config/core/files/tests/unknown.file]'"))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    @DisplayName("Input unknown multiple")
    public void input_unknown_multiple() {
        final Executable testMethod = () -> this.cut.findFirstFileAsFile(
                "com/github/nhojpatrick/config/core/files/tests/unknownA.file",
                "com/github/nhojpatrick/config/core/files/tests/unknownB.file"
        );
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("No File found from '[com/github/nhojpatrick/config/core/files/tests/unknownA.file, com/github/nhojpatrick/config/core/files/tests/unknownB.file]'"))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    @DisplayName("Input valid single")
    public void input_valid() {
        final File actual = this.cut.findFirstFileAsFile(
                "com/github/nhojpatrick/config/core/files/tests/abc.file"
        );
        assertThat(actual, is(notNullValue()));
        final String actualAbsolutePath = actual.getAbsolutePath();
        assertThat(actualAbsolutePath, is(endsWith("com/github/nhojpatrick/config/core/files/tests/abc.file")));
    }

    @Test
    @DisplayName("Input valid abc def")
    public void input_valid_abc_def() {
        final File actual = this.cut.findFirstFileAsFile(
                "com/github/nhojpatrick/config/core/files/tests/abc.file",
                "com/github/nhojpatrick/config/core/files/tests/def.file"
        );
        assertThat(actual, is(notNullValue()));
        final String actualAbsolutePath = actual.getAbsolutePath();
        assertThat(actualAbsolutePath, is(endsWith("com/github/nhojpatrick/config/core/files/tests/abc.file")));
    }

    @Test
    @DisplayName("Input valid def abc")
    public void input_valid_def_abc() {
        final File actual = this.cut.findFirstFileAsFile(
                "com/github/nhojpatrick/config/core/files/tests/def.file",
                "com/github/nhojpatrick/config/core/files/tests/abc.file"
        );
        assertThat(actual, is(notNullValue()));
        final String actualAbsolutePath = actual.getAbsolutePath();
        assertThat(actualAbsolutePath, is(endsWith("com/github/nhojpatrick/config/core/files/tests/def.file")));
    }

    @Test
    @DisplayName("Input valid leading invalid")
    public void input_valid_unknown_abc() {
        final File actual = this.cut.findFirstFileAsFile(
                "com/github/nhojpatrick/config/core/files/tests/unknown.file",
                "com/github/nhojpatrick/config/core/files/tests/abc.file"
        );
        assertThat(actual, is(notNullValue()));
        final String actualAbsolutePath = actual.getAbsolutePath();
        assertThat(actualAbsolutePath, is(endsWith("com/github/nhojpatrick/config/core/files/tests/abc.file")));
    }

    @Test
    @DisplayName("Input valid trailing invalid")
    public void input_valid_abc_unknown() {
        final File actual = this.cut.findFirstFileAsFile(
                "com/github/nhojpatrick/config/core/files/tests/abc.file",
                "com/github/nhojpatrick/config/core/files/tests/unknown.file"
        );
        assertThat(actual, is(notNullValue()));
        final String actualAbsolutePath = actual.getAbsolutePath();
        assertThat(actualAbsolutePath, is(endsWith("com/github/nhojpatrick/config/core/files/tests/abc.file")));
    }

}
