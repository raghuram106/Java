package com.mfpe.claims.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.mfpe.claims.client.PolicyClient;
import com.mfpe.claims.exception.PolicyException;
import com.mfpe.claims.model.Claim;
import com.mfpe.claims.model.ClaimStatus;
import com.mfpe.claims.model.ProviderPolicy;
import com.mfpe.claims.repository.ClaimRepo;


@SpringBootTest
class SubmitClaimServiceTest {

	@Autowired
	SubmitClaimService submitClaimService;
	@MockBean
	ClaimRepo claimRepo;
	@MockBean
	PolicyClient policyClient;
	
	@Test
	void submitClaimServiceIsLoadedOrNot() {
		assertThat(submitClaimService).isNotNull();
	}
	
	@Test
	//Checking if SubmitClaim method is working or not
	void testSubmitClaimMethodWithValidClaimObject() {
		Claim claim=new Claim("c1","Pending","All the fields are successfully verified",50000,"h1","b1",1,"m1",0);
		
		String token="rrr";
		float amount=70000;
		
		ProviderPolicy pro1 = new ProviderPolicy("h1",1,"Apollo Hospital","Hyderabad");
		List<ProviderPolicy> hospitalList=new ArrayList<>();
		hospitalList.add(pro1);

		List<String> benefitList=new ArrayList<>();
		benefitList.add("Coverage for COVID-19");
		benefitList.add("Coverage for hospitalization at home");
		
		when(policyClient.getProviders(1, token)).thenReturn(hospitalList);
		when(policyClient.getBenefits(1, "m1", token)).thenReturn(benefitList);
		when(policyClient.getAmount(1, "m1","b1", token)).thenReturn(amount);
		
		ClaimStatus claimStatus=new ClaimStatus();
		claimStatus.setClaimId("c1");
		claimStatus.setClaimStatus("Pending Action");
		claimStatus.setClaimDescription("All the fields are successfully verified");
		
		ResponseEntity<ClaimStatus> c=submitClaimService.submitClaim(claim, token);
		
		assertEquals(claimStatus.getClaimId(), c.getBody().getClaimId()); 
		assertEquals(claimStatus.getClaimStatus(),c.getBody().getClaimStatus()); 
		assertEquals(claimStatus.getClaimDescription(),c.getBody().getClaimDescription());
		
	}
	
	
	@Test
	void testSubmitClaimMethodwithNullProvider() {
		Claim claim=new Claim("c1","Claim Rejected","Insufficient Claim Details(not Valid details)",50000,"h1","b1",1,"m1",0);
		
		String token="rrr";
		float amount=700000;
		
		List<ProviderPolicy> hospitalList=null;
		

		List<String> benefitList=new ArrayList<>();
		benefitList.add("Coverage for COVID-19");
		benefitList.add("Coverage for hospitalization at home");
		
		when(policyClient.getProviders(1, token)).thenReturn(hospitalList);
		when(policyClient.getBenefits(1, "m1", token)).thenReturn(benefitList);
		when(policyClient.getAmount(1, "m1","b1", token)).thenReturn(amount);
		
		ClaimStatus claimStatus=new ClaimStatus();
		claimStatus.setClaimId("c1");
		claimStatus.setClaimStatus("Claim Rejected");
		claimStatus.setClaimDescription("Insufficient Claim Details(not Valid details)");
		
		ResponseEntity<ClaimStatus> c=submitClaimService.submitClaim(claim, token);
		
		assertEquals(claimStatus.getClaimId(), c.getBody().getClaimId()); 
		assertEquals(claimStatus.getClaimStatus(),c.getBody().getClaimStatus()); 
		assertEquals(claimStatus.getClaimDescription(),c.getBody().getClaimDescription());
		
	}
	
	@Test
	void testSubmitClaimWithInvalidPolicyId() {
		Claim claim=new Claim("c1","Pending","Verified",10200.0,"h1","b1",55,"m1",0);
		String token="rrr";
		when(policyClient.getProviders(55, token)).thenThrow(PolicyException.class);
		assertThrows(PolicyException.class, () ->submitClaimService.submitClaim(claim,token));
		
	}
	
	@Test
	void testSubmitClaimWithInvalidamont() {
		Claim claim=new Claim("c1","Pending","Verified",10200.0,"h1","b1",55,"m1",0);
		String token="rrr";
		when(policyClient.getAmount(55,"m1","b1", token)).thenThrow(PolicyException.class);
		assertThrows(PolicyException.class, () ->submitClaimService.submitClaim(claim,token));
		
	}
	

	@Test
	void testSubmitClaimWithInvalidMemeberId() {
		Claim claim=new Claim("c1","Pending","Verified",10200.0,"h1","b1",1,"M546",0);
		
		String token="rrr";
		
		ProviderPolicy pro1 = new ProviderPolicy("h1",1,"Apollo Hospital","Hyderabad");
		
		List<ProviderPolicy> hospitalList=new ArrayList<>();
		hospitalList.add(pro1);
		when(policyClient.getProviders(1, token)).thenReturn(hospitalList);
		when(policyClient.getBenefits(1, "M546", token)).thenThrow(PolicyException.class);
		assertThrows(PolicyException.class, () ->submitClaimService.submitClaim(claim,token));
		
	}
	
	
}
		