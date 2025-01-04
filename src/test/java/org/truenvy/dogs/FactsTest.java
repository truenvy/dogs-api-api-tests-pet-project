package org.truenvy.dogs;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.truenvy.dogs.asserter.FactsAsserter;
import org.truenvy.dogs.base.AbstractBaseTest;

/**
 * FactsTest is a test class that extends AbstractBaseTest and validates the functionality
 * of the Dogs API for managing and retrieving facts about dogs.
 *
 * The test class uses assertions implemented in standalone assertions classes instead of direct/explicit AssertJ assertions.
 * The FactsAsserter class is used for structured assertion chaining, which provides a clean and readable way to perform JSON response validation.
 *
 * This class contains test methods that employ the JUnit framework for writing tests along with
 * assertion methods from the FactsAsserter utility to validate the correctness of the API responses.
 *
 * The test methods in this class:
 * - Interact with the Dogs API via the dogsApiClient, which is an API client utility class for communication with the API endpoints.
 * - Use Faker to generate random input values (e.g., number of facts to retrieve) where necessary.
 * - Perform tests to ensure that the API returns the correct status codes and expected properties in the response payload.
 * - Validate the number of facts returned by the API and confirm that all facts in the response body contain non-null properties.
 *
 */
public class FactsTest extends AbstractBaseTest {

    private static final int HTTP_OK_STATUS = 200;

    @Test
    @DisplayName("Dogs API :: test number of facts")
    public void testNumberOfFacts() {
        // given
        int expectedNumberOfFacts = faker.number().numberBetween(1, 5);

        // when
        Response factsResponse = dogsApiClient.getFacts(String.valueOf(expectedNumberOfFacts));

        // then
        new FactsAsserter(factsResponse)
                .toHaveStatusCode(HTTP_OK_STATUS)
                .toHaveNumberOfFacts(expectedNumberOfFacts);
    }

    @Test
    @DisplayName("Dogs API :: test facts body not null")
    public void testFactsBodyNotNull() {
        // given
        int expectedNumberOfFacts = faker.number().numberBetween(1, 5);

        // when
        Response factsResponse = dogsApiClient.getFacts(String.valueOf(expectedNumberOfFacts));

        // then
        new FactsAsserter(factsResponse)
                .toHaveStatusCode(HTTP_OK_STATUS)
                .toHaveFactsBodyNotNull();
    }
}
