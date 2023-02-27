package com.authorizationservice.authorization.dto;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class VaildatingDTO {
	@Id
	@JsonProperty
	private boolean validStatus;

	public boolean isValidStatus() {
		return validStatus;
	}

}
