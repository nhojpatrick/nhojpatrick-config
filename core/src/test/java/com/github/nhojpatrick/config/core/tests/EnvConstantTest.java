package com.github.nhojpatrick.config.core.tests;

import org.junit.jupiter.api.Test;

import static com.github.nhojpatrick.config.core.EnvConstant.ENV;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class EnvConstantTest {

    @Test
    public void basic() {
        assertThat(ENV, is(equalTo("local_dev")));
    }

}
