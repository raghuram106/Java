package com.mfpe.claims.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mfpe.claims.exception.InvalidClaimIdException;
import com.mfpe.claims.model.Claim;
import com.mfpe.claims.model.ClaimStatus;
import com.mfpe.claims.repository.ClaimRepo;


@SpringBootTest
class ClaimStatusServiceTest {

	@Autowired
	ClaimStatusService claimStatusService;
	@MockBean
	ClaimRepo claimRepo;
	@Test
	void claimStatusServiceIsLoadedOrNot() {
		assertThat(claimStatusService).isNotNull();
	}
	
	@Test
	void getClaimStatusTestWithValidId() {
		ClaimStatus c=new ClaimStatus("c1","pending","Verified");
		
		
		when(claimRepo.findByClaimId("c1")).thenReturn(new Claim("c1","Pending","Verified",10200.0,"h3","b3",2,"m1",0));
		
		assertEquals(c.getClaimId(), claimRepo.findByClaimId("c1").getClaimId()); 
	}
	
	@Test
	void claimstatusServiceIs() {
		ClaimStatus claimStatus=new ClaimStatus("c1","Pending Action","All the fields are successfully verified");
		
		when(claimRepo.findByClaimId("c1")).thenReturn(new Claim("c1","Pending Action","All the fields are successfully verified",10200.0,"h3","b3",2,"m1",0));
//		
//		when(claimStatusService.getClaimStatus("c1")).thenReturn(new ResponseEntity<>(c,HttpStatus.OK));
		
		assertEquals(claimStatus.getClaimId(), claimStatusService.getClaimStatus("c1",2,"m1").getBody().getClaimId()); 
		assertEquals(claimStatus.getClaimStatus(), claimStatusService.getClaimStatus("c1",2,"m1").getBody().getClaimStatus()); 
		assertEquals(claimStatus.getClaimDescription(),claimStatusService.getClaimStatus("c1",2,"m1").getBody().getClaimDescription());
		
		
	}
	
	@Test
	void GetClaimStatusWithInvalidValidId() {
		
		assertThrows(InvalidClaimIdException.class, () ->claimStatusService.getClaimStatus("c25678",1,"m1"));
	}
	
	@Test
	void GetClaimStatusWithInvalidValidpId() {
		
		assertThrows(InvalidClaimIdException.class, () ->claimStatusService.getClaimStatus("c1",54367,"m1"));
	}
	
	@Test
	void GetClaimStatusWithInvalidValidmId() {
		
		assertThrows(InvalidClaimIdException.class, () ->claimStatusService.getClaimStatus("c1",1,"m768589"));
	}
	
	
	
}
