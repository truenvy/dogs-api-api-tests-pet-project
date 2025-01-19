package org.truenvy.dogs.rest.models.v2.responses.facts;

import java.util.List;

public record FactsResponse(
	List<DataItem> data
) {}