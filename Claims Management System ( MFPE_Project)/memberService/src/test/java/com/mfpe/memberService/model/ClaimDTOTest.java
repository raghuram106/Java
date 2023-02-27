package com.mfpe.memberService.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClaimDTOTest {

 
    ClaimDTO claimDTOObj=new ClaimDTO();

    @Test
    //Checking if claim class is loading or not
     void processingRequestIsLoadedOrNot() {
        assertThat(claimDTOObj).isNotNull();
    }

    //Checking if claim class is responding correctly or not
    @Test
     void testingClaim() throws ParseException{
    	
        claimDTOObj= new ClaimDTO("c1","pending","something",5000,"h1","b1",1,"m1",0);
        
        claimDTOObj.setClaimId("c2");
        claimDTOObj.setStatus("approved");
        claimDTOObj.setDescription("na");
        claimDTOObj.setClaimAmount(6500);
        claimDTOObj.setHospitalId("h2");
        claimDTOObj.setBenefitId("b2");
        claimDTOObj.setPolicyId(2);
        claimDTOObj.setMemberId("m2");
        claimDTOObj.setAmountSettled(1);
        
        
        assertEquals("c2",claimDTOObj.getClaimId());
        assertEquals("approved",claimDTOObj.getStatus());
        assertEquals("na",claimDTOObj.getDescription());
        assertEquals(6500,claimDTOObj.getClaimAmount());
        assertEquals("h2",claimDTOObj.getHospitalId());
        assertEquals("b2",claimDTOObj.getBenefitId());
        assertEquals(2,claimDTOObj.getPolicyId());
        assertEquals("m2",claimDTOObj.getMemberId());
        assertEquals(1,claimDTOObj.getAmountSettled());
     
    }
}
