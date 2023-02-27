package com.mfpe.claims.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mfpe.claims.model.ProviderPolicy;


@FeignClient(name = "policy-service", url ="http://localhost:8080/policy")
public interface PolicyClient {
	
	@GetMapping(path="/getChainOfProviders/{pid}")
	public List<ProviderPolicy> getProviders(@PathVariable("pid") int pId, @RequestHeader(name = "Authorization", required = true) String token);
	
	@GetMapping("/getEligibleBenefits/{pid}/{mid}")
	public List<String> getBenefits(@PathVariable("pid") int pId,@PathVariable("mid") String mId, @RequestHeader(name = "Authorization", required = true) String token);
	
	@GetMapping("/getEligibleClaimAmount/{pid}/{mid}/{bid}")
	public float getAmount(@PathVariable("pid") int pId, @PathVariable("mid") String mId, @PathVariable("bid") String bId, @RequestHeader(name = "Authorization", required = true) String token);
}
