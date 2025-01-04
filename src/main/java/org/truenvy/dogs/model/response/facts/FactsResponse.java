package org.truenvy.dogs.model.response.facts;

import java.util.List;

public record FactsResponse(
	List<DataItem> data
) {}