package in.springproject.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import in.springproject.entitu.EligibilityDetails;
import in.springproject.repository.EligibilityDetailsRepo;
import in.springproject.request.SearchRequest;
import in.springproject.request.SearchResponse;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	EligibilityDetailsRepo eligRepo;

	@Override
	public List<SearchResponse> search(SearchRequest request) {
		// TODO Auto-generated method stub
		
		List<SearchResponse> response = new ArrayList<>();
		
		EligibilityDetails queryBuilder = new EligibilityDetails();
		
		String planName = request.getPlanName();
		if(planName!=null && !planName.equals(""))
		{
			queryBuilder.setPlanName(planName);
		}

		String planStatus = request.getPlanStatus();
		if(planStatus!=null && !planStatus.equals(""))
		{
			queryBuilder.setPlanStatus(planStatus);
		}
		
		LocalDate planStartDate = request.getPlanStartDate();
		if(planStartDate!=null && !planStartDate.equals(""))
		{
			queryBuilder.setPlanStartDate(planStartDate);
		}
		
		LocalDate planEndDate = request.getPlanEndDate();
		if(planEndDate!=null && !planEndDate.equals(""))
		{
		    queryBuilder.setPlanEndDate(planEndDate);	
		}

		Example<EligibilityDetails> example = Example.of(queryBuilder);
		
		List<EligibilityDetails> entities = eligRepo.findAll(example);
		
		for(EligibilityDetails eligibilityDetails : entities)
		{
			SearchResponse sr = new SearchResponse();
			BeanUtils.copyProperties(eligibilityDetails, sr);
			response.add(sr);
		}
		
		return response;
	}

	
	
	public List<EligibilityDetails> findByCriteria(SearchRequest request){
        return eligRepo.findAll(new Specification<EligibilityDetails>() {
//            @Override
//            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> predicates = new ArrayList<>();
//                if(employeeName!=null) {
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("employeeName"), "%"+employeeName+"%")));
//                }
//                if(employeeRole!=null){
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("employeeRole"), employeeRole)));
//                }
//                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//            }

			@Override
			public Predicate toPredicate(Root<EligibilityDetails> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				if(request.getPlanName()!=null)
				{
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("planName"), request.getPlanName())));
				}
				
				return criteriaBuilder.and(predicates.toArray (new Predicate[predicates.size()]));
			
//				for(EligibilityDetails eligibilityDetails : entities)
//				{
//					SearchResponse sr = new SearchResponse();
//					BeanUtils.copyProperties(eligibilityDetails, sr);
//					response.add(sr);
//				}
//				
//				return response;
			
			}
        });
    }
	
	
	
	
	
	
	
	
}
