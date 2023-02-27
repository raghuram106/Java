package com.mfpe.claims.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClaimStatusTest {

    
    ClaimStatus claimStatus;
    @Test
     void testClaimStatusDTO() {
        claimStatus = new ClaimStatus();
        claimStatus.setClaimId("C1");
        assertEquals("C1",claimStatus.getClaimId());
       
        claimStatus = new ClaimStatus();
        claimStatus.setClaimStatus("Pending");
        assertEquals("Pending",claimStatus.getClaimStatus());
        
        claimStatus = new ClaimStatus();
        claimStatus.setClaimDescription("Wait for the Approval");
        assertEquals("Wait for the Approval",claimStatus.getClaimDescription());
        
        claimStatus = new ClaimStatus("C2", "Approved", "Your claims has been approved");
        assertEquals("C2",claimStatus.getClaimId());
        assertEquals("Approved",claimStatus.getClaimStatus());
        assertEquals("Your claims has been approved",claimStatus.getClaimDescription());
        		
    }
}
