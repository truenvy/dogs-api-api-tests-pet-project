package org.truenvy.dogs.config;

import com.github.javafaker.Faker;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

@Getter
public enum ConfigProvider {
    INSTANCE;

    private final ProjectConfig projectConfig;
    private final Faker faker;

    ConfigProvider() {
        projectConfig = ConfigFactory.create(ProjectConfig.class, System.getProperties());
        faker = new Faker();
    }

}
