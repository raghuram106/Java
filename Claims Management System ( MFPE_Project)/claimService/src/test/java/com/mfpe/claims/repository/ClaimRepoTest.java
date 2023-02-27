package com.mfpe.claims.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mfpe.claims.model.Claim;


@SpringBootTest
class ClaimRepoTest {

	@MockBean
	ClaimRepo claimRepo;
	
	@Test
	void testClaimRepo() {
		Claim c=new Claim("c1","Pending","Verified",10200.0,"h3","b3",2,"m1",0);
		
		when(claimRepo.findByClaimId("c1")).thenReturn(new Claim("c1","Pending","Verified",10200.0,"h3","b3",2,"m1",0));
		assertEquals(c.getClaimId(),claimRepo.findByClaimId("c1").getClaimId());
	}
}
