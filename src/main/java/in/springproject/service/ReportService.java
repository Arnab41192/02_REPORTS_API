package in.springproject.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import in.springproject.entitu.EligibilityDetails;
import in.springproject.request.SearchRequest;
import in.springproject.request.SearchResponse;


public interface ReportService{

	public  List<SearchResponse> search (SearchRequest req);
	
	public List<EligibilityDetails> findByCriteria(SearchRequest request);
	
	public void generateExcel(HttpServletResponse response) throws IOException;
	
	public void generatePdf(HttpServletResponse response) throws Exception;
}
