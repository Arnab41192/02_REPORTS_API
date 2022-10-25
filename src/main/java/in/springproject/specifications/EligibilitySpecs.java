package in.springproject.specifications;

import java.time.LocalDate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import in.springproject.entitu.EligibilityDetails;

public class EligibilitySpecs {

	
//	    public static Specification<EligibilityDetails> isLongTermCustomer() {
//	        return new Specification<EligibilityDetails>() {
//	            public Predicate toPredicate(
//	                Root<EligibilityDetails> root, CriteriaQuery<?> query,
//	                CriteriaBuilder builder) {
//	                //LocalDate date = new LocalDate().minusYears(2);
//	        //        return builder.lessThan(root.get('dateField'), date);
//	            }
//	        };
//	    }
//
//	    public static Specification<EligibilityDetails> hasSalesOfMoreThan(MontaryAmount value) {
//	        return new Specification<EligibilityDetails>() {
//	            public Predicate toPredicate(
//	                Root<EligibilityDetails> root, CriteriaQuery<?> query,
//	                CriteriaBuilder builder) {
//	                // build query here
//	            }
//	        };
//	    }
	
}
