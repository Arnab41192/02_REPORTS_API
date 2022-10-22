package in.springproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.springproject.request.SearchRequest;
import in.springproject.request.SearchResponse;


public interface ReportService{

	public  List<SearchResponse> search (SearchRequest req);
}
