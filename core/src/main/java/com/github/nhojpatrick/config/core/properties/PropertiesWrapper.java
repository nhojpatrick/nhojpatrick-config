package com.github.nhojpatrick.config.core.properties;

import com.github.nhojpatrick.config.core.properties.internal.PropertiesWrapperImpl;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.net.URISyntaxException;
import java.util.List;

public interface PropertiesWrapper {

    static PropertiesWrapper getDefault(final String... filesAsVarArgs)
            throws ConfigurationException,
            URISyntaxException {
        return new PropertiesWrapperImpl(filesAsVarArgs);
    }

    /**
     * Lookup {@link boolean} property using supplier key.
     *
     * <p>Lookup logic and error handling controlled by Apache commons-configuration.</p>
     *
     * @param key the property key to use.
     * @return the value of the key.
     */
    boolean lookupBoolean(String key);

    /**
     * Lookup {@link boolean} property using supplier key, using defaultValue if needed.
     *
     * <p>Lookup logic, defaultValue and error handling controlled by Apache commons-configuration.</p>
     *
     * @param key          the property key to use.
     * @param defaultValue the default property value to use.
     * @return the value of the key.
     */
    Boolean lookupBoolean(String key, Boolean defaultValue);

    /**
     * Lookup {@link int} property using supplier key, using defaultValue if needed.
     *
     * <p>Lookup logic and error handling controlled by Apache commons-configuration.</p>
     *
     * @param key the property key to use.
     * @return the value of the key.
     */
    int lookupInt(String key);

    /**
     * Lookup {@link int} property using supplier key, using defaultValue if needed.
     *
     * <p>Lookup logic, defaultValue and error handling controlled by Apache commons-configuration.</p>
     *
     * @param key          the property key to use.
     * @param defaultValue the default property value to use.
     * @return the value of the key.
     */
    int lookupInt(String key, int defaultValue);

    /**
     * Lookup {@link java.lang.Integer} property using supplier key, using defaultValue if needed.
     *
     * <p>Lookup logic, defaultValue and error handling controlled by Apache commons-configuration.</p>
     *
     * @param key          the property key to use.
     * @param defaultValue the default property value to use.
     * @return the value of the key.
     */
    Integer lookupInteger(String key, Integer defaultValue);

    /**
     * Lookup {@link java.lang.String} property using supplier key.
     *
     * <p>Lookup logic and error handling controlled by Apache commons-configuration.</p>
     *
     * @param key the property key to use.
     * @return the value of the key.
     */
    String lookupString(String key);

    /**
     * Lookup {@link java.lang.String} property using supplier key, using defaultValue if needed.
     *
     * <p>Lookup logic, defaultValue and error handling controlled by Apache commons-configuration.</p>
     *
     * @param key          the property key to use.
     * @param defaultValue the default property value to use.
     * @return the value of the key.
     */
    String lookupString(String key, String defaultValue);

    /**
     * Lookup {@link java.lang.String} property using supplier key.
     *
     * <p>Lookup logic and error handling controlled by Apache commons-configuration.</p>
     *
     * @param key the property key to use.
     * @return the value of the key.
     */
    List<String> lookupStrings(final String key);

}
