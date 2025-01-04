package org.truenvy.dogs.base;

import com.github.javafaker.Faker;
import org.truenvy.dogs.config.ConfigProvider;
import org.truenvy.dogs.rest.api.v2.DogsApiClient;

public abstract class AbstractBaseTest {
    protected final DogsApiClient dogsApiClient = new DogsApiClient();
    protected final Faker faker = ConfigProvider.INSTANCE.getFaker();
}
