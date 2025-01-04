package org.truenvy.dogs.model.response.breeds;

import java.util.List;

public record BreedsResponse(
	List<DataItem> data,
	Links links
) {
}