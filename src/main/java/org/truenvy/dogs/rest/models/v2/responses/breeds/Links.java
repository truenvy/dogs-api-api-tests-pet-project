package org.truenvy.dogs.rest.models.v2.responses.breeds;

public record Links(
	String next,
	String current,
	String last,
	String self
) {
}
