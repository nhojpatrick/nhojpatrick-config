package com.github.nhojpatrick.config.core.properties.tests;

import com.github.nhojpatrick.config.core.properties.PropertiesWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropertiesWrapperTest {

    @Test
    public void getDefault_empty() {

        final Executable testMethod = () -> PropertiesWrapper.getDefault();
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("List of files required."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    public void getDefault_null_single() {

        final Executable testMethod = () -> PropertiesWrapper.getDefault(
                null
        );
        final RuntimeException thrown = assertThrows(RuntimeException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("List of files required."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    public void getDefault_null_multiple() {

        final Executable testMethod = () -> PropertiesWrapper.getDefault(
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
    public void getDefault_null_leading() {

        final Executable testMethod = () -> PropertiesWrapper.getDefault(
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
    public void getDefault_null_trailing() {

        final Executable testMethod = () -> PropertiesWrapper.getDefault(
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
    public void getDefault_null_middle() {

        final Executable testMethod = () -> PropertiesWrapper.getDefault(
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
    public void getDefault_valid_single() {

        final PropertiesWrapper cut = PropertiesWrapper.getDefault(
                "is-not-empty"
        );
        assertThat(cut, is(notNullValue()));
    }

    @Test
    public void getDefault_valid_multiple() {

        final PropertiesWrapper cut = PropertiesWrapper.getDefault(
                "is-not-empty-0",
                "is-not-empty-1"
        );
        assertThat(cut, is(notNullValue()));
    }

}
