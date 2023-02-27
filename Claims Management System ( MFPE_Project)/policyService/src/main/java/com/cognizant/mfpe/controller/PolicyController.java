package com.cognizant.mfpe.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.mfpe.client.AuthClient;
import com.cognizant.mfpe.exceptions.InvalidTokenException;
import com.cognizant.mfpe.model.*;
import com.cognizant.mfpe.service.*;

@RestController
public class PolicyController {
	
	@Autowired
	private ProviderService proService;
	
	@Autowired
	private MemberService mService;
	
	@Autowired
	private PolicyService pService;
	
	 @Autowired
	private AuthClient authClient;
		 
	 private String msg = "Token is either expired or invalid...";
	 
	 @PostMapping("/policies")
	 public String addPolicy(@RequestBody Policy policy) {
			
			pService.addPolicy(policy);
			return "Policy Added";
			
	 }
	 @PostMapping("/members")
	 public String addMember(@RequestBody MemberPolicy member) {
			
			mService.addMember(member);
			return "Member Added";
			
	 }
	 @PostMapping("/benifits")
	 public String addBenefit(@RequestBody Benefits benefit) {
			
			mService.addBenefit(benefit);
			return "Benefit Added";
			
	 }
	 @PostMapping("/providers")
	 public String addProvider(@RequestBody ProviderPolicy provider) {
			
			proService.addProvider(provider);
			return "Provider Added";
			
	 }
		
	
	@GetMapping(path="/getChainOfProviders/{pid}")
	public List<ProviderPolicy> getProviders(@PathVariable("pid") int pId,@RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException{
		if (!authClient.getsValidity(token).isValidStatus()) {
			
			throw new InvalidTokenException(msg);
		}

		return proService.getProviders(pId);
	}
	
	@GetMapping("/getEligibleBenefits/{pid}/{mid}")
	public List<String> getBenefits(@PathVariable("pid") int pId,@PathVariable("mid") String mId, @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException{
		
		if (!authClient.getsValidity(token).isValidStatus()) {
			
			throw new InvalidTokenException(msg);
		}

		return mService.getBenefits(pId,mId);
		
	}
	
	@GetMapping("/getEligibleClaimAmount/{pid}/{mid}/{bid}")
	public float getAmount(@PathVariable("pid") int pId, @PathVariable("mid") String mId, @PathVariable("bid") String bId, @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException{
		if (!authClient.getsValidity(token).isValidStatus()) {
			
			throw new InvalidTokenException(msg);
		}
		 return pService.getAmount(pId,mId,bId);
	
	}
	
}
