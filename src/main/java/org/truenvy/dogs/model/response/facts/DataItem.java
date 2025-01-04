package org.truenvy.dogs.model.response.facts;

public record DataItem(
	Attributes attributes,
	String id,
	String type
) {}
