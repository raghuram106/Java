package com.mfpe.memberService.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "claims")
public class Claim {

	@Id
    @Column(name="CID")
	private String claimId;
	
    @Column(name="Status")	
	private String status;
    
    @Column(name="Description")	
	private String description;
    
    @Column(name="Claim_Amount")	
	private double claimAmount;

   
    @Column(name="hospitalId")
    private String hospitalId;
    
  
    @Column(name="benefitId")
    private String benefitId;
    
    @Column(name="PolicyId")
    private int policyId;
    
    @Column(name="MemberId")
    private String memberId;
    
    @Column(name="AmountSettled")
    private double amountSettled;

	
	public Claim() {
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


	public Claim(String claimId, String status, String description, double claimAmount, String hospitalId,
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
