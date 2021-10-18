package com.github.nhojpatrick.config1.core.tests;

import com.github.nhojpatrick.config1.core.EnvConstant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junitpioneer.jupiter.ClearSystemProperty;
import org.junitpioneer.jupiter.ReadsSystemProperty;
import org.junitpioneer.jupiter.SetSystemProperty;

import static com.github.nhojpatrick.config1.core.EnvConstant.ENV;
import static com.github.nhojpatrick.config1.core.EnvConstant.initEnvSetup;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EnvConstantTest {

    @Test
    public void constructor() {
        final Executable testMethod = () -> new EnvConstant();
        final AssertionError thrown = assertThrows(AssertionError.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo("Static constants class - cannot be instantiated."))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    @Test
    @ReadsSystemProperty
    public void constant() {
        assertThat(ENV, is(equalTo("local_dev")));
    }

    @Test
    @ReadsSystemProperty
    @ClearSystemProperty(key = "env")
    public void clearEnv() {
        assertThat(initEnvSetup(), is(equalTo("local_dev")));
    }

    @Test
    @ReadsSystemProperty
    @SetSystemProperty(key = "env", value = "prod")
    public void customEnv() {
        assertThat(initEnvSetup(), is(equalTo("prod")));
    }

}