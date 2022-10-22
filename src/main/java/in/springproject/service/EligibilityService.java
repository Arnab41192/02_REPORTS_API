package in.springproject.service;

import java.util.List;

import in.springproject.request.SearchRequest;
import in.springproject.request.SearchResponse;

public interface EligibilityService {

	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatuses();
	
	public List<SearchResponse> search(SearchRequest request);
	 	
}
