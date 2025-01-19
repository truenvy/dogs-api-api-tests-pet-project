package org.truenvy.dogs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.truenvy.dogs.base.AbstractBaseTest;
import org.truenvy.dogs.rest.models.v2.responses.breeds.BreedsResponse;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * BreedsTest is a test class that extends AbstractBaseTest and validates the functionality
 * of the Dogs API for breed-related operations.
 *
 * The test class uses direct/explicit AssertJ assertions.
 *
 * The class contains test methods, primarily using the JUnit framework along with assertions
 * to verify API behaviors such as retrieving all dog breeds and confirming the correct structure
 * and data type within the response.
 *
 * - The test methods use the dogsApiClient to make API calls to the Dogs API endpoints.
 * - Assertions ensure that the responses conform to the expected HTTP status codes and data integrity.
 * - Responses are validated to confirm the presence of at least one breed and proper data structure,
 *   including validation of the type field in the response data items.
 */
public class BreedsTest extends AbstractBaseTest {

    @Test
    @DisplayName("Dogs API :: test getting all breeds")
    public void testGetAllBreeds() {
        // given
        var allBreedsResponse = dogsApiClient.getAllBreeds();

        // when
        var allBreedsAssertableResponse = allBreedsResponse.as(BreedsResponse.class);

        // then
        allBreedsResponse.then().statusCode(200);

        assertThat(allBreedsAssertableResponse.data().size()).isGreaterThanOrEqualTo(1);

        assertThat(allBreedsAssertableResponse.data()).allMatch(
                element -> element.type().equals("breed")
        );
    }
}
