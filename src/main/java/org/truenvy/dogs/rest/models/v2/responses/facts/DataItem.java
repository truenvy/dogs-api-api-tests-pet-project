package org.truenvy.dogs.rest.models.v2.responses.facts;

public record DataItem(
	Attributes attributes,
	String id,
	String type
) {}
