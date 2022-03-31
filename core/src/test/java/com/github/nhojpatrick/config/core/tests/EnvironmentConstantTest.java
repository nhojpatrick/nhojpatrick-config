package com.github.nhojpatrick.config.core.tests;

import com.github.nhojpatrick.config.core.EnvironmentConstant;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.ReadsSystemProperty;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class EnvironmentConstantTest {

    @Test
    @ReadsSystemProperty
    public void getDefault() {
        assertThat(EnvironmentConstant.getDefault(), is(notNullValue()));
    }

}
