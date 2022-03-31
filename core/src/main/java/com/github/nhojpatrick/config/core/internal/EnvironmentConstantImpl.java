package com.github.nhojpatrick.config.core.internal;

import com.github.nhojpatrick.config.core.EnvironmentConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;

public class EnvironmentConstantImpl
        implements EnvironmentConstant {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnvironmentConstantImpl.class);

    private final String envName;

    public EnvironmentConstantImpl() {
        this.envName = System.getProperty("env", "local_dev");
        requireNonNull(this.envName, "Required System Property 'env' missing.");
        LOGGER.debug("EnvironmentConstantImpl for env='{}'", this.envName);
    }

    @Override
    public String getEnvName() {
        return this.envName;
    }

}
