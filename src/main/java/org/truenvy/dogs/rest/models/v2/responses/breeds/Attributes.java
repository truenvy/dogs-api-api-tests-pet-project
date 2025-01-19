package org.truenvy.dogs.rest.models.v2.responses.breeds;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Attributes(
	@JsonProperty("male_weight") MaleWeight maleWeight,
	String name,
	Boolean hypoallergenic,
	String description,
	Life life,
	@JsonProperty("female_weight") FemaleWeight femaleWeight
) {
}
