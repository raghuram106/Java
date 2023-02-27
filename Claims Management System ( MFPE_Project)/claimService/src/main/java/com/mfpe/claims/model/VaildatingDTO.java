package com.mfpe.claims.model;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VaildatingDTO {
    @Id
    @JsonProperty
    private boolean validStatus;

	public boolean isValidStatus() {
		return validStatus;
	}

	public void setValidStatus(boolean validStatus) {
		this.validStatus = validStatus;
	}

	public VaildatingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VaildatingDTO(boolean validStatus) {
		super();
		this.validStatus = validStatus;
	}
	
        
}
