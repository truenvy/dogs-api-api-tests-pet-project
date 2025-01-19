package org.truenvy.dogs.config;

import org.aeonbits.owner.Config;

/**
 * Represents the configuration properties for the project, sourced from a `config.properties` file.
 *
 * This interface is an extension of the {@link Config} interface from the Owner library. It facilitates
 * access to application-level configuration settings, specifically enabling or disabling features
 * based on the associated property values.
 *
 * Methods defined in this interface correspond to keys in the `config.properties` file. For instance,
 * the `logging()` method is tied to a configuration key that determines whether logging should be enabled.
 *
 * Usage of this configuration is typically managed through a provider, like {@code ConfigProvider},
 * which instantiates the configuration and makes it accessible across the application.
 *
 * Key application examples:
 * - Logging behavior is controlled using the `logging()` method, allowing dynamic adjustment of
 *   logging filters depending on the configuration.
 * - This configuration is referenced during the construction of various API client components, ensuring
 *   that the behavior is consistent with the specified project settings.
 */
@Config.Sources("classpath:config.properties")
public interface ProjectConfig extends Config {

    Boolean logging();

}
