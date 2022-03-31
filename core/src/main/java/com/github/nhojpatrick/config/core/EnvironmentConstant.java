package com.github.nhojpatrick.config.core;

public interface EnvironmentConstant {

    static EnvironmentConstant getDefault() {
        return new EnvironmentConstantImpl();
    }

    String getEnvName();

}
