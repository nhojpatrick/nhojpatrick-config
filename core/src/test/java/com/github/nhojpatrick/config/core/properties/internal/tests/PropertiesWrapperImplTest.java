package com.github.nhojpatrick.config.core.properties.internal.tests;

import com.github.nhojpatrick.config.core.properties.PropertiesWrapper;
import com.github.nhojpatrick.config.core.properties.internal.PropertiesWrapperImpl;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.ex.ConversionException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.net.URISyntaxException;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    class lookupBoolean {

        @Test
        public void basic()
                throws URISyntaxException,
                ConfigurationException {

            final PropertiesWrapper cut = new PropertiesWrapperImpl(
                    "/com/github/nhojpatrick/config/core/properties/internal/tests/lookupBoolean.properties"
            );

            assertAll(
                    () -> assertThat(cut.lookupBoolean("lookupBoolean.known.true"), is(equalTo(true))),
                    () -> assertThat(cut.lookupBoolean("lookupBoolean.known.true_caps"), is(equalTo(true))),
                    () -> assertThat(cut.lookupBoolean("lookupBoolean.known.false"), is(equalTo(false))),
                    () -> assertThat(cut.lookupBoolean("lookupBoolean.known.false_caps"), is(equalTo(false))),
                    () -> {
                        final Executable testMethod = () -> cut.lookupBoolean("lookupBoolean.unknown");
                        assertThrows(NoSuchElementException.class, testMethod);
                    },
                    () -> {
                        final Executable testMethod = () -> cut.lookupBoolean("lookupBoolean.known.string");
                        assertThrows(ConversionException.class, testMethod);
                    }
            );
        }

        @Test
        public void defaults()
                throws URISyntaxException,
                ConfigurationException {

            final PropertiesWrapper cut = new PropertiesWrapperImpl(
                    "/com/github/nhojpatrick/config/core/properties/internal/tests/lookupBoolean.properties"
            );

            assertAll(
                    () -> assertThat(cut.lookupBoolean("lookupBoolean.known.true", false), is(equalTo(true))),
                    () -> assertThat(cut.lookupBoolean("lookupBoolean.known.true_caps", false), is(equalTo(true))),
                    () -> assertThat(cut.lookupBoolean("lookupBoolean.known.false", true), is(equalTo(false))),
                    () -> assertThat(cut.lookupBoolean("lookupBoolean.known.false_caps", true), is(equalTo(false))),
                    () -> assertThat(cut.lookupBoolean("lookupBoolean.unknown", true), is(equalTo(true))),
                    () -> {
                        final Executable testMethod = () -> cut.lookupBoolean("lookupBoolean.known.string", true);
                        assertThrows(ConversionException.class, testMethod);
                    }
            );
        }

    }

    @Nested
    class lookupInt {

        @Test
        public void basic()
                throws URISyntaxException,
                ConfigurationException {

            final PropertiesWrapper cut = new PropertiesWrapperImpl(
                    "/com/github/nhojpatrick/config/core/properties/internal/tests/lookupInt.properties"
            );

            assertAll(
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInt.unknown");
                        assertThrows(NoSuchElementException.class, testMethod);
                    },
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInt.base");
                        assertThrows(ConversionException.class, testMethod);
                    },
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInt.zero");
                        assertThrows(ConversionException.class, testMethod);
                    },
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInt.boolean_true");
                        assertThrows(ConversionException.class, testMethod);
                    },
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInt.boolean_false");
                        assertThrows(ConversionException.class, testMethod);
                    },
                    () -> assertThat("env.zero", cut.lookupInt("lookupInt.integer"), is(equalTo(12345))),
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInt.string");
                        assertThrows(ConversionException.class, testMethod);
                    }
            );
        }

        @Test
        public void defaults()
                throws URISyntaxException,
                ConfigurationException {

            final PropertiesWrapper cut = new PropertiesWrapperImpl(
                    "/com/github/nhojpatrick/config/core/properties/internal/tests/lookupInt.properties"
            );

            assertAll(
                    () -> assertThat("env.zero", cut.lookupInt("lookupInt.unknown", 23456), is(equalTo(23456))),
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInt.base", 23456);
                        assertThrows(ConversionException.class, testMethod);
                    },
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInt.zero", 23456);
                        assertThrows(ConversionException.class, testMethod);
                    },
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInt.boolean_true", 23456);
                        assertThrows(ConversionException.class, testMethod);
                    },
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInt.boolean_false", 23456);
                        assertThrows(ConversionException.class, testMethod);
                    },
                    () -> assertThat("env.zero", cut.lookupInt("lookupInt.integer", 23456), is(equalTo(12345))),
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInt.string", 23456);
                        assertThrows(ConversionException.class, testMethod);
                    }
            );
        }

    }

    @Nested
    class lookupInteger {

        @Test
        public void basic()
                throws URISyntaxException,
                ConfigurationException {

            final PropertiesWrapper cut = new PropertiesWrapperImpl(
                    "/com/github/nhojpatrick/config/core/properties/internal/tests/lookupInteger.properties"
            );

            assertAll(
                    () -> assertThat("env.zero", cut.lookupInteger("lookupInteger.unknown", 34567890), is(equalTo(34567890))),
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInteger.base", 34567890);
                        assertThrows(ConversionException.class, testMethod);
                    },
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInteger.zero", 34567890);
                        assertThrows(ConversionException.class, testMethod);
                    },
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInteger.boolean_true", 34567890);
                        assertThrows(ConversionException.class, testMethod);
                    },
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInteger.boolean_false", 34567890);
                        assertThrows(ConversionException.class, testMethod);
                    },
                    () -> assertThat("env.zero", cut.lookupInteger("lookupInteger.integer", 34567890), is(equalTo(12345))),
                    () -> {
                        final Executable testMethod = () -> cut.lookupInt("lookupInteger.string", 34567890);
                        assertThrows(ConversionException.class, testMethod);
                    }
            );
        }

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
