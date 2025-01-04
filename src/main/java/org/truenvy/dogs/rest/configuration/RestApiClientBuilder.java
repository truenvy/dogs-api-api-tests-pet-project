package org.truenvy.dogs.rest.configuration;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.truenvy.dogs.ApiClient;
import org.truenvy.dogs.config.ConfigProvider;
import org.truenvy.dogs.config.ProjectConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;
import java.util.function.Supplier;

public class RestApiClientBuilder {
    private final ProjectConfig projectConfig = ConfigProvider.INSTANCE.getProjectConfig();

    /**
     * Builds and returns an instance of type {@code T} by applying the provided client builder function.
     *
     * The method constructs a {@link Supplier} of {@link RequestSpecBuilder}, which provides a pre-configured
     * {@code RequestSpecBuilder} instance. This instance is configured with logging, filtering, base URI,
     * and base path as specified in the application's {@link ProjectConfig}.
     *
     * @param <T> The type of the instance to be returned.
     * @param clientBuilder A function that accepts a {@link Supplier} of {@link RequestSpecBuilder} and
     *        returns an instance of type {@code T}.
     * @return An instance of type {@code T}, built using the provided {@code clientBuilder} function.
     */
    public <T> T build(Function<Supplier<RequestSpecBuilder>, T> clientBuilder) {
        Supplier<RequestSpecBuilder> supplier = () -> new RequestSpecBuilder()
                .addRequestSpecification(
                        this.calculateLogging()
                                .setBaseUri(ApiClient.BASE_URI)
                                .build()
                );
        return clientBuilder.apply(supplier);
    }

    /**
     * Configures a RequestSpecBuilder instance with logging and filtering based on the application's
     * project configuration settings.
     *
     * If the logging feature is enabled in the configuration, this method adds request and response logging
     * filters along with an AllureRestAssured filter. Otherwise, it only adds the AllureRestAssured filter.
     *
     * @return A pre-configured instance of RequestSpecBuilder with appropriate filters applied.
     */
    private RequestSpecBuilder calculateLogging() {
        if (projectConfig.logging()) {
            return new RequestSpecBuilder()
                    .addFilters(
                            Arrays.asList(
                                    new RequestLoggingFilter(),
                                    new ResponseLoggingFilter(),
                                    new AllureRestAssured()
                            )
                    );
        }
        return new RequestSpecBuilder()
                .addFilters(
                        Collections.singletonList(
                                new AllureRestAssured()
                        )
                );
    }
}
