package com.mfpe.memberService.model;

public class ClaimDTO {

	
	private String claimId;
	
    
	private String status;
    
  
	private String description;
    
    
	private double claimAmount;

   
   
    private String hospitalId;
    
  
    
    private String benefitId;
    
    private int policyId;
    
    private String memberId;
    
    private double amountSettled;

	
	public ClaimDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getClaimId() {
		return claimId;
	}


	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getClaimAmount() {
		return claimAmount;
	}


	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}


	public String getHospitalId() {
		return hospitalId;
	}


	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}


	public String getBenefitId() {
		return benefitId;
	}


	public void setBenefitId(String benefitId) {
		this.benefitId = benefitId;
	}


	public int getPolicyId() {
		return policyId;
	}


	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public double getAmountSettled() {
		return amountSettled;
	}


	public void setAmountSettled(double amountSettled) {
		this.amountSettled = amountSettled;
	}


	public ClaimDTO(String claimId, String status, String description, double claimAmount, String hospitalId,
			String benefitId, int policyId, String memberId, double amountSettled) {
		super();
		this.claimId = claimId;
		this.status = status;
		this.description = description;
		this.claimAmount = claimAmount;
		this.hospitalId = hospitalId;
		this.benefitId = benefitId;
		this.policyId = policyId;
		this.memberId = memberId;
		this.amountSettled = amountSettled;
	}

	
    
    
    
    
}
