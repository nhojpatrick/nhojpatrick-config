package com.github.nhojpatrick.config.files.tests;

import com.github.nhojpatrick.config.files.FindUtil;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class FindUtilTest {

    @Test
    public void getDefault() {
        assertThat(FindUtil.getDefault(), is(notNullValue()));
    }

}
