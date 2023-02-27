package com.mfpe.claims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mfpe.claims.exception.InvalidClaimIdException;
import com.mfpe.claims.model.Claim;
import com.mfpe.claims.model.ClaimStatus;
import com.mfpe.claims.repository.ClaimRepo;

@Service
public class ClaimStatusService {

	@Autowired
	ClaimRepo crepo;
	
	public ResponseEntity<ClaimStatus> getClaimStatus(String cid,int pid,String mid) throws InvalidClaimIdException {
			
		ClaimStatus status=new ClaimStatus();
		
		if(crepo.findByClaimId(cid)!=null) {
				Claim c= crepo.findByClaimId(cid);
				if(c.getPolicyId()!=pid){
					throw new InvalidClaimIdException("Invalid Claim pID");
				}
				
				if(!(c.getMemberId().equalsIgnoreCase(mid))) {
					throw new InvalidClaimIdException("Invalid Claim mID");
				}
				status.setClaimStatus(c.getStatus());
				status.setClaimDescription(c.getDescription());
				status.setClaimId(cid);
				return new ResponseEntity<>(status, HttpStatus.OK);
			}
		else{
				throw new InvalidClaimIdException("Invalid Claim cID");
			}
			
	}

}
