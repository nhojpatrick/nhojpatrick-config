package com.github.nhojpatrick.config.env;

import com.github.nhojpatrick.config.env.internal.EnvironmentConstantImpl;

public interface EnvironmentConstant {

    static EnvironmentConstant getDefault() {
        return new EnvironmentConstantImpl();
    }

    String getEnvName();

}
