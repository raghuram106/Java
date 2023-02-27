package com.mfpe.memberService.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.mfpe.memberService.client.AuthClient;
import com.mfpe.memberService.exceptions.InvalidClaimIdException;
import com.mfpe.memberService.exceptions.InvalidTokenException;
import com.mfpe.memberService.model.Bills;

import com.mfpe.memberService.model.VaildatingDTO;
import com.mfpe.memberService.service.ClaimStatusAndDetails;





@SpringBootTest
public class MemberControllerTest {
	
	@InjectMocks
	MemberController memberController;
	
	@Mock
	ClaimStatusAndDetails claimStatusAndDetails;

	@Mock
	AuthClient authClient;

    @Test
    //Checking for MemberController - if it is loading or not for @GetMapping
    void MemberControllerNotNullTest(){
    	
    	MemberController memberController = new MemberController();
        assertThat(memberController).isNotNull();
    }
    
    

    @Test
	//Testing get Bills is working correctly or not
	public void testgetBills() {
    	
        Bills billsObj= new Bills("221",1,"2011-07-02",65600,1200,"2020-06-04");
    	when(authClient.getsValidity(anyString())).thenReturn(new VaildatingDTO(true));
    	when(claimStatusAndDetails.fetchBills("121")).thenReturn(new Bills("221",1,"2011-07-02",65600,1200,"2020-06-04"));
    	
    	ResponseEntity<Bills> response = memberController.getBills("121", "token");
    	
    	assertEquals(billsObj.getMemberId(), response.getBody().getMemberId());
	}

    @Test()
    //Testing get Bills invalid Token Id Exception
   	public void testgetBills_fails2() {
       	
    	when(authClient.getsValidity(anyString())).thenReturn(new VaildatingDTO(false));
    	Assertions.assertThrows(InvalidTokenException.class, ()->{
    		memberController.getBills("121", "token");
    	});
   	}
  
    
   /* @Test
   	//Testing get claim Status is working correctly or not
   	public void testgetClaimStatus() {
             
       	when(authClient.getsValidity(anyString())).thenReturn(new VaildatingDTO(true));
       	//when(claimStatusAndDetails.fetchClaimStatus("AAAA","token")).thenReturn(new ClaimStatusDTO("AAAA","Pending","Need More Action"));
       	
       	
       	Mockito.when(claimStatusAndDetails.fetchClaimStatus("AAAA","token")).thenAnswer((Answer<?>) new ClaimStatusDTO("AAAA","Pending","Need More Action"));
       	
       	ResponseEntity<ClaimStatusDTO> response = memberController.returnClaimStatus("AAAA", "token");
       	
       	assertEquals("Pending", response.getBody().getClaimStatus());
   	}*/
       
//       @Test
//      	//Testing get Claim Status invalid Claim Id Exception
//      	public void testgetClaimStatus_fails1() {
//          	
//       	when(authClient.getsValidity(anyString())).thenReturn(new VaildatingDTO(true));
//          	when(claimStatusAndDetails.fetchClaimStatus(anyString(),2,"m1", anyString())).thenThrow(InvalidClaimIdException.class);
//         
//          	Assertions.assertThrows(InvalidClaimIdException.class, ()->{
//          		memberController.returnClaimStatus("AAAA",2,"m","token");
//        	});
//      	}
       
  
      
      /* @Test
      	@DisplayName("Testing get Submitting Claim is working correctly or not")
      	public void testgetClaimStatusOnUpdate() {
          	
       		ClaimDTO claimDetails = new ClaimDTO();
       		claimDetails.setClaimId("C1");
       		claimDetails.setStatus("Pending");       
          	when(authClient.getsValidity(anyString())).thenReturn(new VaildatingDTO(true));
          	when(claimStatusAndDetails.fetchClaimDetails(claimDetails,"token")).thenReturn(new ClaimStatusDTO("C1","Pending","Need More Action"));
          	
          	
     
            ResponseEntity<ClaimStatusDTO> response = memberController.returnClaimStatusOnUpdate(claimDetails, "token");
         
          	assertEquals("Pending", response.getBody().getClaimStatus());
      	}*/
          
     
          
  
}
