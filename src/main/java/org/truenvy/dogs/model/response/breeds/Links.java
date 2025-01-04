package org.truenvy.dogs.model.response.breeds;

public record Links(
	String next,
	String current,
	String last,
	String self
) {
}
