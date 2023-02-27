package com.mfpe.memberService.service;


import java.util.Optional;
//import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mfpe.memberService.client.ClaimsClient;
import com.mfpe.memberService.dto.ClaimStatusDTO;
import com.mfpe.memberService.exceptions.InvalidClaimIdException;
import com.mfpe.memberService.exceptions.InvalidMemberIdException;
import com.mfpe.memberService.model.Bills;
import com.mfpe.memberService.model.ClaimDTO;
import com.mfpe.memberService.repository.BillsRepo;


@Service
public class ClaimStatusAndDetails{

        
		@Autowired
        private BillsRepo billsRepo;
        
        @Autowired
        private ClaimsClient claimsClient;
        
        // This method fetch claim Status from Claims MicroService
        public  ResponseEntity<ClaimStatusDTO> fetchClaimStatus(String cid,int pid,String mid, String token)
        {
        	ResponseEntity<ClaimStatusDTO> claimStatusDTO = null;
        	//ResponseEntity<ClaimStatusDTO> claimStatusDTO = new ResponseEntity<ClaimStatusDTO>(cl);
        	try {
        		 claimStatusDTO = claimsClient.getClaimStatus(cid,pid,mid,token);
        	}
            catch(Exception e)
            {
                throw new InvalidClaimIdException("The Claim Id is Invalid");
            }
            return claimStatusDTO;
        }
        
        
        // This method fetch Bills from Bills table
        public  Bills fetchBills(String memberId)
        {
             
            Optional<Bills> bill = billsRepo.findById(memberId);
            if(!bill.isPresent() )
    		{
    			throw new InvalidMemberIdException("The Member Id is Invalid");
    		}
            
            return bill.get();
           
        }
        
        
        // This method submit the claim details to Claims MicroService
        public  ResponseEntity<ClaimStatusDTO> fetchClaimDetails( ClaimDTO claimDetails,String token)
        {
            
            //claimDetails.setClaimId(generateClaimId());
                    
            ResponseEntity<ClaimStatusDTO> claimStatusDTO = claimsClient.submitClaim(claimDetails,token);
            
            return claimStatusDTO;
        }
        
       /* public String generateClaimId()
        {
        	return UUID.randomUUID().toString();
        }*/
        
}
