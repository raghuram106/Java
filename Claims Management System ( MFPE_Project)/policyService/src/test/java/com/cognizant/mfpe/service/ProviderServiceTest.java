package com.cognizant.mfpe.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.mfpe.model.Policy;
import com.cognizant.mfpe.model.ProviderPolicy;
import com.cognizant.mfpe.repository.PolicyRepository;
import com.cognizant.mfpe.repository.ProviderPolicyRepository;

@SpringBootTest
class ProviderServiceTest {
	
	@Autowired
	ProviderService providerService;
	
	@Autowired
	ProviderPolicyRepository prorepo;
	
	@Autowired
	PolicyRepository prepo;

	@Test
	void test() {
		assertThat(providerService).isNotNull();
	}
	
	@Test
	void testProvider() {

		ProviderPolicy p = new ProviderPolicy("h1",1,"Apollo Hospital","Hyderabad");
		providerService.addProvider(p);
	}
	
	@Test
	void providerTest() {
		Policy p1 = new Policy(1,"Health Plus Classic",500000,15000);
		Policy p2 = new Policy(2,"Health Plus Enhanced",3000000,20000);
	
		prepo.save(p1);
		prepo.save(p2);
		
		ProviderPolicy pro1 = new ProviderPolicy("h1",1,"Apollo Hospital","Hyderabad");
		ProviderPolicy pro2 = new ProviderPolicy("h2",2,"KIMS Hospital","Srikakulam");
		ProviderPolicy pro3 = new ProviderPolicy("h6",1,"Indus","Srikakulam");
		
		prorepo.save(pro1);
		prorepo.save(pro2);
		prorepo.save(pro3);
		List<ProviderPolicy> providers = new ArrayList<>();
		providers.add(pro1);
		providers.add(pro2);
		providers.add(pro3);
		
		List<ProviderPolicy> list1 = providerService.getProviders(1);
		assertNotEquals(providers,list1);
		
	}

}
