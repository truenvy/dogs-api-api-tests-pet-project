package org.truenvy.dogs.asserter;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.truenvy.dogs.model.response.facts.FactsResponse;

import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

/**
 * The FactsAsserter class provides assertion methods to validate the properties of an API response.
 * It is specifically designed to work with responses containing facts data, allowing for the
 * validation of HTTP status codes and the number of facts returned in the response.
 *
 * This class utilizes information extracted from the response body and makes it easier to perform
 * structured and reusable API tests with clear and readable assertions.
 *
 * Key Features:
 * - Validate the HTTP status code of the API response.
 * - Verify the number of facts returned in the response payload.
 * - Verify facts to be not null.
 *
 * The methods in this class return the instance itself, enabling method chaining for concise test assertions.
 *
 * Dependencies:
 * - Response: Represents the API response to be validated.
 * - FactsResponse: Extracted model from the response body that maps the facts data.
 * - Logging: Provides logging support for each assertion step.
 */

@Slf4j
public class FactsAsserter {

    private static final String EXPECTED_TYPE = "fact";

    private final Response response;
    private final FactsResponse factsResponse;

    public FactsAsserter(Response response) {
        this.response = response;
        this.factsResponse = this.response.as(FactsResponse.class);
    }

    @Step("API response to have status code: {statusCode}")
    public FactsAsserter toHaveStatusCode(int statusCode) {
        log.info("API response to have status code: {}", statusCode);
        this.response.then().statusCode(statusCode);
        return this;
    }

    @Step("API response should have number of facts: {numberOfFacts}")
    public FactsAsserter toHaveNumberOfFacts(int numberOfFacts) {
        log.info("API response should have number of facts: {}", numberOfFacts);
        assertThat(this.factsResponse.data().size()).isEqualTo(numberOfFacts);
        return this;
    }

    @Step("API response should have facts body not null")
    public FactsAsserter toHaveFactsBodyNotNull() {
        log.info("API response should have facts body not null");
        var factsData = this.factsResponse.data();
        assertSoftly(softly -> {
            softly.assertThat(factsData).allMatch(
                    element -> element.type().equals(EXPECTED_TYPE)
            );
            softly.assertThat(factsData).allMatch(
                    element -> nonNull(element.attributes().body())
            );
        });
        return this;
    }
}
