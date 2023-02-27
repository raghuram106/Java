package com.cognizant.mfpe.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cognizant.mfpe.model.Policy;
import com.cognizant.mfpe.repository.MemberPolicyRepository;
import com.cognizant.mfpe.repository.PolicyRepository;

@SpringBootTest
class PolicyServiceTest {
	
	@Autowired
	PolicyService policyService;

	@Autowired
	PolicyRepository prepo;
	
	@Autowired
	MemberPolicyRepository mrepo;

	
	@Test
	void test() {
		assertThat(policyService).isNotNull();
	}
	
	@Test
	void policyTest() {
		Policy p = new Policy(1,"Health Plus Classic",500000,15000);
		policyService.addPolicy(p);
	}
	
	@Test
	void policiesTest() {
		
		Policy p1 = new Policy(1,"Health Plus Classic",500000,15000);
		Policy p2 = new Policy(2,"Health Plus Enhanced",3000000,20000);
		prepo.save(p1);
		prepo.save(p2);
		float amount = policyService.getAmount(1,"m1","b1");
		assertEquals(500000,amount);
	
		 
	}
	

}
