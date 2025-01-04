package org.truenvy.dogs.rest.configuration.v2;

import org.truenvy.dogs.api.BreedsApi;
import org.truenvy.dogs.api.FactsApi;
import org.truenvy.dogs.api.GroupsApi;
import org.truenvy.dogs.rest.configuration.RestApiClientBuilder;

/**
 * Configuration class for accessing APIs related to dogs. This class is designed to provide
 * pre-configured instances of API interfaces for various domains like breeds, facts, and groups.
 *
 * All the provided APIs are initialized with a configured REST client using {@link RestApiClientBuilder},
 * which ensures consistent configuration, including logging, filtering, base URI, and base path.
 *
 * This class cannot be instantiated and provides static methods to obtain API instances.
 */
public class DogsConfiguration {

    private DogsConfiguration() {}

    public static BreedsApi breeds() {
        return new RestApiClientBuilder().build(BreedsApi::breeds);
    }

    public static FactsApi facts() {
        return new RestApiClientBuilder().build(FactsApi::facts);
    }

    public static GroupsApi groups() {
        return new RestApiClientBuilder().build(GroupsApi::groups);
    }
}
