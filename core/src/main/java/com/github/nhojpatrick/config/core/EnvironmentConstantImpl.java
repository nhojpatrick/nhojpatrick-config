package com.github.nhojpatrick.config.core;

public class EnvironmentConstantImpl
        implements EnvironmentConstant {

    public static final String ENV;

    static {
        ENV = initEnvSetup();
    }

    public static final String initEnvSetup() {
        return System.getProperty("env", "local_dev");
    }

    @Override
    public String getEnvName() {
        return initEnvSetup();
    }

}
