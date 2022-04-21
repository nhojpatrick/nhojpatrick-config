package com.github.nhojpatrick.config.files.tests;

import com.github.nhojpatrick.config.files.FileUtil;
import com.github.nhojpatrick.config.files.FindUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindUtilTest {

    @Test
    public void staticUtilityClass() {
        final Executable testMethod = () -> new FileUtil();
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll(
                () -> assertThat(thrown.getMessage(), is(equalTo("Static utility class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    public void getDefault() {
        assertThat(FindUtil.getDefault(), is(notNullValue()));
    }

}
