package org.truenvy.dogs.rest.models.v2.responses.breeds;

public record DataItem(
	Relationships relationships,
	Attributes attributes,
	String id,
	String type
) {
}
