package com.ui.pojo;

import java.util.Map;

public class Config {

    Map<String , Environment> environments;

    public Map<String, Environment> getEnviroments() {
        return environments;
    }

    public void setEnviroments(Map<String, Environment> enviroments) {
        this.environments = enviroments;
    }
}
