package com.cognizant.mfpe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.mfpe.exceptions.InvalidMemberIdException;
import com.cognizant.mfpe.exceptions.InvalidPolicyIdException;
import com.cognizant.mfpe.model.MemberPolicy;
import com.cognizant.mfpe.model.Policy;
import com.cognizant.mfpe.repository.MemberPolicyRepository;
import com.cognizant.mfpe.repository.PolicyRepository;

@Service
public class PolicyService {
	
	@Autowired
	PolicyRepository prepo;
	
	@Autowired
	MemberPolicyRepository mrepo;
	
	public void addPolicy(Policy p) {
		prepo.save(p);
	}
	
	public float getAmount(int pId,String mId, String bId) throws InvalidPolicyIdException,InvalidMemberIdException{
		
		Optional<Policy> policy = prepo.findById(pId);
		Optional<MemberPolicy> member = mrepo.findById(mId);
		
		if(!policy.isPresent() )
		{
			throw new InvalidPolicyIdException("Invalid Policy Id...");
		}
		
		if(!member.isPresent())
		{
			throw new InvalidMemberIdException("Invalid Member Id...");
		}
		 List<Policy> policies = prepo.findAll();
		 float sum=0;
		 for(Policy p : policies) {
			 if(p.getPid()==pId) {
				sum=(float) p.getCapamount();
		 }
		 }
		 return sum;
	
	
	}
}
