package com.mfpe.claims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.claims.client.AuthClient;
import com.mfpe.claims.exception.InvalidClaimIdException;
import com.mfpe.claims.exception.InvalidTokenException;
import com.mfpe.claims.model.Claim;
import com.mfpe.claims.model.ClaimStatus;
import com.mfpe.claims.service.ClaimStatusService;
import com.mfpe.claims.service.SubmitClaimService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/claimModule")
public class ClaimsController {

	@Autowired
	private ClaimStatusService claimStatusService;
	@Autowired
	private SubmitClaimService submitClaimService;
	
	@Autowired
	private AuthClient authClient;
			 
	private String msg = "Token is either expired or invalid...";
		
	
	@GetMapping(path="/getClaimStatus/{cid}/{pid}/{mid}")
	public ResponseEntity<ClaimStatus> getClaimStatus(@PathVariable("cid") String cid,@PathVariable("pid")int pid,@PathVariable("mid")String mid, @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException,InvalidClaimIdException {
		if (!authClient.getsValidity(token).isValidStatus()) {
			
			throw new InvalidTokenException(msg);
		}

		return claimStatusService.getClaimStatus(cid,pid,mid); 
	}
	

	
	@PostMapping(path ="/submitClaim")
	public ResponseEntity<ClaimStatus> submitClaim(@RequestBody Claim claim, @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException,InvalidClaimIdException,NullPointerException {
		if (!authClient.getsValidity(token).isValidStatus()) {
			
			throw new InvalidTokenException(msg);
		}

		return submitClaimService.submitClaim(claim,token);
	}
	
}
