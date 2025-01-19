package org.truenvy.dogs.config;

import com.github.javafaker.Faker;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

/**
 * Singleton configuration provider for accessing application-level settings.
 *
 * This class is implemented as an enum singleton, ensuring thread-safe instantiation and allowing
 * centralized access to shared configurations throughout the application. It initializes and provides:
 *
 * 1. An instance of {@link ProjectConfig}, which reads configuration properties defined in a
 *    `config.properties` file using the Owner library.
 *
 * 2. An instance of {@link Faker}, used for generating mock data.
 *
 * Usage:
 * - The {@link ProjectConfig} instance can be accessed using {@code getProjectConfig()} for interactions
 *   with application settings, such as enabling or disabling logging functionality.
 * - The {@link Faker} instance can be accessed using {@code getFaker()} for generating test data in
 *   scenarios such as API testing.
 */
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
