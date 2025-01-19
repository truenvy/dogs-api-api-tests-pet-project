package org.truenvy.dogs.rest.models.v2.responses.breeds;

import java.util.List;

public record BreedsResponse(
	List<DataItem> data,
	Links links
) {
}