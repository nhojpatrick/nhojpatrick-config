package com.github.nhojpatrick.config.core.properties.internal.tests;

import com.github.nhojpatrick.config.core.properties.PropertiesWrapper;
import com.github.nhojpatrick.config.core.properties.internal.PropertiesWrapperImpl;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PropertiesWrapperImplTest {

    @Test
    public void threePropertiesFiles()
            throws ConfigurationException,
            URISyntaxException {

        final PropertiesWrapper cut = new PropertiesWrapperImpl(
                "/com/github/nhojpatrick/config/core/tests/Override-2.properties",
                "/com/github/nhojpatrick/config/core/tests/Override-1.properties",
                "/com/github/nhojpatrick/config/core/tests/Override-0.properties"
        );

        assertAll(
                () -> assertThat("env.zero", cut.lookupString("env.zero"), is(equalTo("From Override-0.properties"))),
                () -> assertThat("env.one", cut.lookupString("env.one"), is(equalTo("From Override-1.properties"))),
                () -> assertThat("env.two", cut.lookupString("env.two"), is(equalTo("From Override-2.properties")))
        );
    }

    @Nested
    class lookupString {

        @Test
        public void basic()
                throws URISyntaxException,
                ConfigurationException {

            final PropertiesWrapper cut = new PropertiesWrapperImpl(
                    "/com/github/nhojpatrick/config/core/properties/internal/tests/lookupString.properties"
            );

            assertAll(
                    () -> assertThat(cut.lookupString("lookupString.known.boolean"), is(equalTo("true"))),
                    () -> assertThat(cut.lookupString("lookupString.known.int"), is(equalTo("23456"))),
                    () -> assertThat(cut.lookupString("lookupString.known.integer"), is(equalTo("12345"))),
                    () -> assertThat(cut.lookupString("lookupString.known.string"), is(equalTo("Known qwerty"))),
                    //
                    () -> assertThat(cut.lookupString("lookupString.unknown"), is(nullValue()))
            );
        }

        @Test
        public void defaults()
                throws URISyntaxException,
                ConfigurationException {

            final PropertiesWrapper cut = new PropertiesWrapperImpl(
                    "/com/github/nhojpatrick/config/core/properties/internal/tests/lookupString.properties"
            );

            assertAll(
                    () -> assertThat(cut.lookupString("lookupString.known.boolean", "Default qwerty"), is(equalTo("true"))),
                    () -> assertThat(cut.lookupString("lookupString.known.int", "Default qwerty"), is(equalTo("23456"))),
                    () -> assertThat(cut.lookupString("lookupString.known.integer", "Default qwerty"), is(equalTo("12345"))),
                    () -> assertThat(cut.lookupString("lookupString.known.string", "Default qwerty"), is(equalTo("Known qwerty"))),
                    //
                    () -> assertThat(cut.lookupString("lookupString.unknown", "Default qwerty"), is(equalTo("Default qwerty")))
            );
        }

    }

}
