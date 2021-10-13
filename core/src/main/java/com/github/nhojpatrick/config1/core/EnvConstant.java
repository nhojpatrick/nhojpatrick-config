package com.github.nhojpatrick.config1.core;

public class EnvConstant {

    public static final String ENV;

    static {
        ENV = initEnvSetup();
    }

    public static final String initEnvSetup() {
        return System.getProperty("env", "local_dev");
    }

    public EnvConstant() {
        throw new AssertionError("Static constants class - cannot be instantiated.");
    }

}
