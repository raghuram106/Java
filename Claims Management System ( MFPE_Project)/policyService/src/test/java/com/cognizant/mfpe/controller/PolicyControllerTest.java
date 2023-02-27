package com.cognizant.mfpe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cognizant.mfpe.client.AuthClient;
import com.cognizant.mfpe.model.Benefits;
import com.cognizant.mfpe.model.MemberPolicy;
import com.cognizant.mfpe.model.Policy;
import com.cognizant.mfpe.model.ProviderPolicy;
import com.cognizant.mfpe.model.VaildatingDTO;
import com.cognizant.mfpe.service.PolicyService;


@SpringBootTest
class PolicyControllerTest {
	@Autowired
	PolicyController policyController;
	@MockBean
	PolicyService policyService;

	@Autowired
	AuthClient authClient;
	
	@Test
	void test() {
		assertThat(policyController).isNotNull();
	}
	

	
	@Test
	void testPolicy() {
		Policy p = new Policy(1,"Health Plus Classic",500000,15000);
		String x = policyController.addPolicy(p);
		assertEquals("Policy Added",x);
		
	}
	
	@Test
	void testMember() {
		MemberPolicy m1 = new MemberPolicy("m1",1,"b1","vijay",32,"male","talavaram","10/11/2020",10);
		String x = policyController.addMember(m1);
		assertEquals("Member Added",x);
		
	}
	
	@Test
	void testProvider() {
		ProviderPolicy pro1 = new ProviderPolicy("h1",1,"Apollo Hospital","Hyderabad");
		String x = policyController.addProvider(pro1);
		assertEquals("Provider Added",x);
	}
	
	@Test
	void testBenefit(){
		Benefits b = new Benefits(1,"b1","Coverage for COVID-19");
		String x = policyController.addBenefit(b);
		assertEquals("Benefit Added",x);
		
	}
	/*
	@Test
	void testproviders() {

		Policy p1 = new Policy(1,"Health Plus Classic",500000,15000);
		
		policyController.addPolicy(p1);
		
		ProviderPolicy pro1 = new ProviderPolicy("h1",1,"Apollo Hospital","Hyderabad");
		ProviderPolicy pro2 = new ProviderPolicy("h2",2,"KIMS Hospital","Srikakulam");
		
		policyController.addProvider(pro1);
		policyController.addProvider(pro2);
	

		String token="maneesha";
		//List<ProviderPolicy> list = policyController.getProviders(1,token);
	
		
	
	}
	@Test
	void testamount() {
		String token="maneesha";
		Policy p1 = new Policy(1,"Health Plus Classic",500000,150);
		policyController.addPolicy(p1);		
		when(policyService.getAmount(1,"m1","b1")).thenReturn((float) 500000);
		//float amount = policyController.getAmount(1,"m1","b1",token);
		//assertNotEquals(1000,amount);	
      	

			 
		}*/
	
}
