package com.mfpe.memberService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.memberService.client.AuthClient;
import com.mfpe.memberService.dto.ClaimStatusDTO;
import com.mfpe.memberService.exceptions.InvalidTokenException;
import com.mfpe.memberService.model.Bills;
import com.mfpe.memberService.model.ClaimDTO;
import com.mfpe.memberService.service.ClaimStatusAndDetails;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/memberModule")
public class MemberController { 
    
    @Autowired
    private ClaimStatusAndDetails ClaimStatusAndDetails;
  
    @Autowired
    private AuthClient authClient;
    
    private String msg = "Token is either expired or invalid...";
    
    @GetMapping(value="/viewBills/{memberId}")
    public ResponseEntity<Bills> getBills( @PathVariable("memberId") String memberId, @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException
    {       
    	if (!authClient.getsValidity(token).isValidStatus()) {
			
			throw new InvalidTokenException(msg);
		}
        return new ResponseEntity<>( ClaimStatusAndDetails.fetchBills(memberId), HttpStatus.OK);
    }
    
    
    
    @GetMapping(value="/getClaimStatus/{claimId}/{pid}/{mid}")
    public ResponseEntity<ClaimStatusDTO> returnClaimStatus(@PathVariable("claimId") String claimId,@PathVariable("pid") int pid,@PathVariable("mid") String mid,  @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException 
    {
        return ClaimStatusAndDetails.fetchClaimStatus(claimId,pid,mid,token);        
    }
    
   
    
    @PostMapping(value="/submitClaim")
    public ResponseEntity<ClaimStatusDTO> returnClaimStatusOnUpdate(@RequestBody ClaimDTO claimDetails,  @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException
    {
      
        return  ClaimStatusAndDetails.fetchClaimDetails(claimDetails,token);

    }
    
     
}
