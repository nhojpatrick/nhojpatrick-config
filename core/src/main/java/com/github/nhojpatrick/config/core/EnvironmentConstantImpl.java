package com.github.nhojpatrick.config.core;

public class EnvironmentConstantImpl {

    public static final String ENV;

    static {
        ENV = initEnvSetup();
    }

    public static final String initEnvSetup() {
        return System.getProperty("env", "local_dev");
    }

    public EnvironmentConstantImpl() {
        throw new AssertionError("Static constants class - cannot be instantiated.");
    }

}
