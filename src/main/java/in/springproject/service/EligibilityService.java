package in.springproject.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.springproject.request.SearchRequest;
import in.springproject.request.SearchResponse;

public interface EligibilityService {

	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatuses();
	
	public List<SearchResponse> search(SearchRequest request);
	
	public void generateExcel(HttpServletResponse response);
	
	public void generatePdf(HttpServletResponse response);
	 	
}
