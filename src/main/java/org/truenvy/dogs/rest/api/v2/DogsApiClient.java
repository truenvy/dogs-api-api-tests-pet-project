package org.truenvy.dogs.rest.api.v2;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.truenvy.dogs.rest.configuration.v2.DogsConfiguration;

import java.util.function.Function;

/**
 * DogsApiClient provides methods to interact with various APIs related to dog breeds,
 * facts, and groups. It acts as a client abstraction layer for easier access to dog-related
 * data from the respective APIs.
 *
 * Each method in this class is implemented to interact with specific endpoints
 * provided by the APIs, such as retrieving all breeds, fetching breed details by ID,
 * retrieving facts (with or without limits), fetching all groups, or retrieving
 * group details by ID.
 *
 * This class uses the DogsConfiguration class to obtain pre-configured API interfaces and
 * utilize REST communication with methods executed using appropriate request specifications.
 * Each API call logs necessary details and returns a Response object.
 */

@Slf4j
public class DogsApiClient {

    @Step("Get all breeds")
    public Response getAllBreeds() {
        log.info("Get all breeds");
        return DogsConfiguration.breeds().breedsGet().execute(Function.identity());
    }

    @Step("Get breed by ID: {id}")
    public Response getBreedById(String id) {
        log.info("Get breed by ID: {}", id);
        return DogsConfiguration.breeds().breedsIdGet().idPath(id).execute(Function.identity());
    }

    @Step("Get facts")
    public Response getFacts() {
        log.info("Get facts");
        return DogsConfiguration.facts().factsGet().execute(Function.identity());
    }

    @Step("Get facts with the limit: {limitQuery}")
    public Response getFacts(String limitQuery) {
        log.info("Get facts with the limit: {}", limitQuery);
        return DogsConfiguration.facts().factsGet().limitQuery(limitQuery).execute(Function.identity());
    }

    @Step("Get all groups")
    public Response getAllGroups() {
        log.info("Get all groups");
        return DogsConfiguration.groups().groupsGet().execute(Function.identity());
    }

    @Step("Get group by ID: {id}")
    public Response getGroupById(String id) {
        log.info("Get group by ID: {}", id);
        return DogsConfiguration.groups().groupsIdGet().idPath(id).execute(Function.identity());
    }
}
