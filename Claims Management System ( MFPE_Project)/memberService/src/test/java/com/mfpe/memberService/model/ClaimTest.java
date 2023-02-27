package com.mfpe.memberService.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class ClaimTest {

	Claim claim=new Claim();
	
	@Test
	void ClaimIsLoadedOrNot() {
		assertThat(claim).isNotNull();
	}
	
	@Test
	//Checking getter and setter 
	void testingClaim() {
		Claim claim=new Claim("c1","Pending","Verified",10200.0,"h3","b3",2,"m1",0);
		
		claim.setClaimId("c2");
		claim.setStatus("Rejected");
		claim.setDescription("Invalid Details");
		claim.setClaimAmount(10000.0);
		claim.setHospitalId("h1");
		claim.setBenefitId("b2");
		claim.setPolicyId(1);
		claim.setMemberId("m3");
		claim.setAmountSettled(1);
		
		
		assertEquals("c2",claim.getClaimId());
		assertEquals("Rejected",claim.getStatus());
		assertEquals("Invalid Details",claim.getDescription());
		assertEquals(10000.0,claim.getClaimAmount());
		assertEquals("h1",claim.getHospitalId());
		assertEquals("b2",claim.getBenefitId());
		assertEquals(1,claim.getPolicyId());
		assertEquals("m3",claim.getMemberId());
		assertEquals(1,claim.getAmountSettled());
	
	}
}
