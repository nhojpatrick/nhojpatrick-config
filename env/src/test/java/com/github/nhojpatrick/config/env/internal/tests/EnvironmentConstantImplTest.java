package com.github.nhojpatrick.config.env.internal.tests;

import com.github.nhojpatrick.config.env.internal.EnvironmentConstantImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.ClearSystemProperty;
import org.junitpioneer.jupiter.ReadsSystemProperty;
import org.junitpioneer.jupiter.SetSystemProperty;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class EnvironmentConstantImplTest {

    @Test
    @ReadsSystemProperty
    public void constructor() {
        new EnvironmentConstantImpl();
    }

    @Nested
    @DisplayName("EnvironmentConstantImpl.getEnvName")
    class getEnvName {

        @Test
        @ReadsSystemProperty
        @ClearSystemProperty(key = "env")
        public void valueDefault() {
            final EnvironmentConstantImpl cut = new EnvironmentConstantImpl();
            assertThat(cut.getEnvName(), is(equalTo("local_dev")));
        }

        @Test
        @ReadsSystemProperty
        @SetSystemProperty(key = "env", value = "prod")
        public void valueCustom() {
            final EnvironmentConstantImpl cut = new EnvironmentConstantImpl();
            assertThat(cut.getEnvName(), is(equalTo("prod")));
        }

    }

}
