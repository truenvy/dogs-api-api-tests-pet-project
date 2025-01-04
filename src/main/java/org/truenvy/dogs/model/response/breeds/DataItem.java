package org.truenvy.dogs.model.response.breeds;

public record DataItem(
	Relationships relationships,
	Attributes attributes,
	String id,
	String type
) {
}
