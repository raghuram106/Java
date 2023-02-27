package com.tweetapp.dto;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
public class ValidatingDTO {
	@Id
	@JsonProperty
	private boolean validStatus;

	public boolean isValidStatus() {
		return validStatus;
	}

	public void setValidStatus(boolean validStatus) {
		this.validStatus = validStatus;
	}

	public ValidatingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValidatingDTO(boolean validStatus) {
		super();
		this.validStatus = validStatus;
	}

}
