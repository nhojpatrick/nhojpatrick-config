package com.github.nhojpatrick.config.core;

public class EnvConstant {

    public static final String ENV;

    static {
        ENV = System.getProperty("env", "local_dev");
    }

}
