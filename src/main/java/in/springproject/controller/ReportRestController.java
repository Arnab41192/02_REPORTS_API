package in.springproject.controller;

import java.io.IOException;
import java.security.Provider.Service;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.springproject.entitu.EligibilityDetails;
import in.springproject.request.SearchRequest;
import in.springproject.request.SearchResponse;
import in.springproject.service.ReportService;

@RestController
public class ReportRestController {

	@Autowired
	ReportService reportService;
	
	@PostMapping("/getDetailsByExample")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest searchRequest)
	{
		
		List<SearchResponse> response = reportService.search(searchRequest);
		return  new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@PostMapping("/getDetailsByCriteria")
	public ResponseEntity<List<EligibilityDetails>> searchByCriteria(@RequestBody SearchRequest searchRequest)
	{
		
		List<EligibilityDetails> response = reportService.findByCriteria(searchRequest);
		return  new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.xlsx";
		
		response.setHeader(headerKey, headerValue);
		
	    reportService.generateExcel(response);
		
	}
	
	@GetMapping("/pdf")
	public void pdfReport(HttpServletResponse response) throws Exception
	{
		response.setContentType("application/pdf");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.pdf";
		
		response.setHeader(headerKey, headerValue);
		
	    reportService.generatePdf(response);;
		
	}
	
	
	
}
