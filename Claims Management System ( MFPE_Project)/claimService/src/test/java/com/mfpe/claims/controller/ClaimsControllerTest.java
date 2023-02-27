package com.mfpe.claims.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mfpe.claims.client.AuthClient;
import com.mfpe.claims.exception.InvalidTokenException;
import com.mfpe.claims.model.Claim;
import com.mfpe.claims.model.ClaimStatus;
import com.mfpe.claims.model.VaildatingDTO;
import com.mfpe.claims.service.ClaimStatusService;
import com.mfpe.claims.service.SubmitClaimService;

@SpringBootTest
class ClaimsControllerTest {
	

	@Autowired
	ClaimsController claimsController;
	@MockBean
	AuthClient authClient;
	@MockBean
	SubmitClaimService submitClaimService;
	@MockBean
	ClaimStatusService claimStatusService;
	
	@Test
    //Checking for Claims Controller if it is loading or not
    void componentProcessingControllerNotNullTest(){
        assertThat(claimsController).isNotNull();
    }
	
	
	@Test
	//Checking the working of get claim Status
	void testGetClaimStatusWithValidParameters() {
		
		ClaimStatus claimStatus=new ClaimStatus("c1","pending","ok good and verified");
		
		when(authClient.getsValidity("rrr")).thenReturn(new VaildatingDTO(true));
		when(claimStatusService.getClaimStatus("C101",1,"m1")).thenReturn(new ResponseEntity<ClaimStatus>(claimStatus,HttpStatus.OK));
		
		
		assertEquals(claimStatus.getClaimId(), claimsController.getClaimStatus("C101",1,"m1","rrr").getBody().getClaimId()); 
		assertEquals(claimStatus.getClaimStatus(), claimsController.getClaimStatus("C101",1,"m1","rrr").getBody().getClaimStatus()); 
		assertEquals(claimStatus.getClaimDescription(), claimsController.getClaimStatus("C101",1,"m1","rrr").getBody().getClaimDescription()); 
	}
	
	@Test
	//Checking the working of Submit claim
	void testSubmitClaimWithValidParameters() {
		String token="rrr";
		Claim claim=new Claim();
		ClaimStatus claimStatus=new ClaimStatus("c1","pending","ok good and verified");
		
		when(authClient.getsValidity(token)).thenReturn(new VaildatingDTO(true));
		when(submitClaimService.submitClaim(claim,token)).thenReturn(new ResponseEntity<ClaimStatus>(claimStatus,HttpStatus.OK));
		
		
		assertEquals(claimStatus.getClaimId(), claimsController.submitClaim(claim,token).getBody().getClaimId()); 
		assertEquals(claimStatus.getClaimStatus(), claimsController.submitClaim(claim,token).getBody().getClaimStatus()); 
		assertEquals(claimStatus.getClaimDescription(),claimsController.submitClaim(claim,token).getBody().getClaimDescription()); 
	}
	

	@Test
	void testGetClaimStatusEndpointWithInvalidToken() {
		String token="AAA";
		VaildatingDTO vaildatingDTO=new VaildatingDTO(false);
		when(authClient.getsValidity(token)).thenReturn(vaildatingDTO);
		
		assertThrows(InvalidTokenException.class, () ->claimsController.getClaimStatus("C101",1,"m1",token));
	}
	
	@Test
	void testSubmitClaimEndpointWithInvalidToken() {
		String token="AAA";
		VaildatingDTO vaildatingDTO=new VaildatingDTO(false);
		when(authClient.getsValidity(token)).thenReturn(vaildatingDTO);
		Claim claim=new Claim();
		assertThrows(InvalidTokenException.class, () ->claimsController.submitClaim(claim,token));
	}
}
