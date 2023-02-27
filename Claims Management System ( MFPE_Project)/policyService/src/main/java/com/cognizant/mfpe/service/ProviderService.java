package com.cognizant.mfpe.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.mfpe.exceptions.InvalidPolicyIdException;
import com.cognizant.mfpe.model.Policy;
import com.cognizant.mfpe.model.ProviderPolicy;
import com.cognizant.mfpe.repository.PolicyRepository;
import com.cognizant.mfpe.repository.ProviderPolicyRepository;

@Service
public class ProviderService {
	
	@Autowired
	ProviderPolicyRepository prorepo;
	@Autowired
	PolicyRepository prepo;
	
	public void addProvider(ProviderPolicy provider) {
		prorepo.save(provider);
	}
	
	public List<ProviderPolicy> getProviders(int pId) throws InvalidPolicyIdException{
		Optional<Policy> policy = prepo.findById(pId);
		if(!policy.isPresent())
			throw new InvalidPolicyIdException("Invalid Policy Id");

		return prorepo.find(pId);
	}

}
