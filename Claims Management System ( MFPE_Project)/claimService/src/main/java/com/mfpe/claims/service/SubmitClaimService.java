package com.mfpe.claims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mfpe.claims.client.PolicyClient;
import com.mfpe.claims.exception.PolicyException;
import com.mfpe.claims.model.Claim;
import com.mfpe.claims.model.ClaimStatus;
import com.mfpe.claims.model.ProviderPolicy;
import com.mfpe.claims.repository.ClaimRepo;

@Service
public class SubmitClaimService {
	@Autowired
	private ClaimRepo crepo;
	@Autowired
	private PolicyClient policyClient;

	private boolean checkProvider(Claim claim, String token) {
		List<ProviderPolicy> provider = policyClient.getProviders(claim.getPolicyId(), token);

		if (provider != null)
			return true;

		return false;
	}

	private boolean checkBenefit(Claim claim, String token) {
		List<String> benefits = policyClient.getBenefits(claim.getPolicyId(), claim.getMemberId(), token);

		if (benefits != null)
			return true;
		return false;
	}

	private boolean checkAmount(Claim claim, String token) {
		float claimAmount = policyClient.getAmount(claim.getPolicyId(), claim.getMemberId(), claim.getBenefitId(),
				token);
		if (claimAmount > 0) {
			return (claim.getClaimAmount() <= claimAmount);
		}
		return false;
	}

	public ResponseEntity<ClaimStatus> submitClaim(Claim claim, String token) throws NullPointerException {

		boolean hospitalD = false, benefitD = false, amountD = false;
		try {
			hospitalD = checkProvider(claim, token);
			benefitD = checkBenefit(claim, token);
			amountD = checkAmount(claim, token);
		} catch (PolicyException pe) {
			throw new PolicyException("Policy Serivce is Not working Properly)");
		}

		Claim c = new Claim();
		c.setClaimId(claim.getClaimId());
		c.setBenefitId(claim.getBenefitId());
		c.setClaimAmount(claim.getClaimAmount());
		c.setHospitalId(claim.getHospitalId());
		c.setMemberId(claim.getMemberId());
		c.setPolicyId(claim.getPolicyId());

		if (hospitalD && benefitD && amountD) {
			claim.setStatus("Pending Action");
			claim.setDescription("All the fields are successfully verified");
			crepo.save(claim);
		} else {

			claim.setStatus("Claim Rejected");
			if (!amountD) {
				claim.setDescription("Under Dispute(contradictory Amount found)");

			}
			else {
				claim.setDescription("Insufficient Claim Details(not Valid details)");
			}
		}
		ClaimStatus status = new ClaimStatus();
		status.setClaimStatus(claim.getStatus());
		status.setClaimDescription(claim.getDescription());
		status.setClaimId(claim.getClaimId());
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

}
