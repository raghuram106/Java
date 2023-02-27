package com.mfpe.claims.model;

public class ClaimStatus {
	private String claimId;
	private String claimStatus;
	private String claimDescription;
	public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getClaimDescription() {
		return claimDescription;
	}
	public void setClaimDescription(String claimDescription) {
		this.claimDescription = claimDescription;
	}
	public ClaimStatus() {
		super();
	}
	public ClaimStatus(String claimId, String claimStatus, String claimDescription) {
		super();
		this.claimId = claimId;
		this.claimStatus = claimStatus;
		this.claimDescription = claimDescription;
	}

}
