package in.springproject.repository;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import in.springproject.entitu.EligibilityDetails;

@Repository
public interface EligibilityDetailsRepo extends JpaRepository<EligibilityDetails, Integer>,JpaSpecificationExecutor<EligibilityDetails> {

	
	
}
