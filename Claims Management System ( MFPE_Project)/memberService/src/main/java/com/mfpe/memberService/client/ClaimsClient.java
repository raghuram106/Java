package com.mfpe.memberService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mfpe.memberService.dto.ClaimStatusDTO;
import com.mfpe.memberService.model.ClaimDTO;



@FeignClient(name = "claim-service", url = "http://localhost:8010/claimModule")
public interface ClaimsClient {

	@GetMapping(path="/getClaimStatus/{cid}/{pid}/{mid}")
	public ResponseEntity<ClaimStatusDTO> getClaimStatus(@PathVariable("cid") String cid,@PathVariable("pid")int pid,@PathVariable("mid")String mid, @RequestHeader(name = "Authorization", required = true) String token);
		
	@PostMapping(path ="/submitClaim")
	public ResponseEntity<ClaimStatusDTO> submitClaim(@RequestBody ClaimDTO claimDTO,@RequestHeader(name = "Authorization", required = true) String token);
	
}
