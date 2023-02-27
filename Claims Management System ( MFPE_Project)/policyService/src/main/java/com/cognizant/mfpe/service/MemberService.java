package com.cognizant.mfpe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.mfpe.exceptions.InvalidMemberIdException;
import com.cognizant.mfpe.exceptions.InvalidPolicyIdException;
import com.cognizant.mfpe.model.Benefits;
import com.cognizant.mfpe.model.MemberPolicy;
import com.cognizant.mfpe.model.Policy;
import com.cognizant.mfpe.repository.BenefitRepository;
import com.cognizant.mfpe.repository.MemberPolicyRepository;
import com.cognizant.mfpe.repository.PolicyRepository;
@Service
public class MemberService {
	
	@Autowired
	MemberPolicyRepository mrepo;
	
	@Autowired
	BenefitRepository brepo;

	@Autowired
	PolicyRepository prepo;
	
	public void addMember(MemberPolicy member) {
		mrepo.save(member);
	}
	
	public void addBenefit(Benefits benefit) {
		brepo.save(benefit);
	}

	public List<String> getBenefits(int pId, String mId) throws InvalidPolicyIdException,InvalidMemberIdException{
		
		
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
		String x=null;
		List<MemberPolicy> mem = mrepo.findAll();
		 for(MemberPolicy m : mem) {
			 if(m.getPid()==pId ) {
			 if(m.getMid().equalsIgnoreCase(mId)) 
				 x=(String) m.getBid();	
			 }
		 }
		 
		 List<String> benifits = new ArrayList<String>();
		 List<Benefits> ben = brepo.findAll();
		 for(Benefits b:ben) {
			 if(b.getBid().equalsIgnoreCase(x))
				 benifits.add(b.getBenifit());
		 }
		return benifits;
	}

}
