package com.github.nhojpatrick.config.core;

import com.github.nhojpatrick.config.core.internal.EnvironmentConstantImpl;

public interface EnvironmentConstant {

    static EnvironmentConstant getDefault() {
        return new EnvironmentConstantImpl();
    }

    String getEnvName();

}
