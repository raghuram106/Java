package com.mfpe.memberService.dto;


public class ClaimStatusDTO {
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
	public ClaimStatusDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClaimStatusDTO(String claimId, String claimStatus, String claimDescription) {
		super();
		this.claimId = claimId;
		this.claimStatus = claimStatus;
		this.claimDescription = claimDescription;
	}
	
	
	

}
