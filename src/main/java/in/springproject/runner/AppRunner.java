package in.springproject.runner;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.springproject.entitu.EligibilityDetails;
import in.springproject.repository.EligibilityDetailsRepo;

@Component
public class AppRunner implements ApplicationRunner {
	@Autowired
	EligibilityDetailsRepo eligRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub

		EligibilityDetails entity1 = new EligibilityDetails();
		entity1.setEligId(1);
		entity1.setName("John");
		entity1.setMobile(1234555l);
		entity1.setGender('M');
		entity1.setSsn(686868L);
		entity1.setPlanName("SNAP");
		entity1.setPlanStatus("Approved");
		entity1.setPlanStartDate(LocalDate.parse("2020-01-08"));
		entity1.setPlanEndDate(LocalDate.parse("2020-01-08"));
		eligRepo.save(entity1);

		EligibilityDetails entity2 = new EligibilityDetails();
		entity2.setEligId(2);
		entity2.setName("Smith");
		entity2.setMobile(32555L);
		entity2.setGender('M');
		entity2.setSsn(6862368l);
		entity2.setPlanName("CCAP");
		entity2.setPlanStatus("Denied");
		entity2.setPlanStartDate(LocalDate.parse("2020-03-08"));
		entity2.setPlanEndDate(LocalDate.parse("2020-09-08"));
		eligRepo.save(entity2);
		
		EligibilityDetails entity3 = new EligibilityDetails();
		entity3.setEligId(3);
		entity3.setName("Robert");
		entity3.setMobile(352224l);
		entity3.setGender('M');
		entity3.setSsn(686214L);
		entity3.setPlanName("CCAP");
		entity3.setPlanStatus("Denied");
		entity3.setPlanStartDate(LocalDate.parse("2020-04-08"));
		entity3.setPlanEndDate(LocalDate.parse("2020-06-08"));
		eligRepo.save(entity3);
	}

}
