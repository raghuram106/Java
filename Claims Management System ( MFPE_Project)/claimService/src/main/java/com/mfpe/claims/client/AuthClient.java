package com.mfpe.claims.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mfpe.claims.model.VaildatingDTO;




@FeignClient(name = "authorization-service", url = "http://localhost:8085/authorization")
public interface AuthClient {
	
	 @GetMapping(value = "/validate")
     public VaildatingDTO getsValidity(@RequestHeader(name = "Authorization", required = true) String token);

}
