package com.authorizationservice.authorization.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;

import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@ApiModel(description = "Creating model class for generating authorization token when any customer logs in")

public class AuthenticationResponse {

	@ApiModelProperty(value = "Username of the Customer doing login")
	private String username;

	@ApiModelProperty(value = "Authorization token of the customer")
	private String jwtAuthToken;

	private long serverCurrentTime;

	private long tokenExpirationTime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getJwtAuthToken() {
		return jwtAuthToken;
	}

	public void setJwtAuthToken(String jwtAuthToken) {
		this.jwtAuthToken = jwtAuthToken;
	}

	public long getServerCurrentTime() {
		return serverCurrentTime;
	}

	public void setServerCurrentTime(long serverCurrentTime) {
		this.serverCurrentTime = serverCurrentTime;
	}

	public long getTokenExpirationTime() {
		return tokenExpirationTime;
	}

	public void setTokenExpirationTime(long tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}

	public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthenticationResponse(String username, String jwtAuthToken, long serverCurrentTime,
			long tokenExpirationTime) {
		super();
		this.username = username;
		this.jwtAuthToken = jwtAuthToken;
		this.serverCurrentTime = serverCurrentTime;
		this.tokenExpirationTime = tokenExpirationTime;
	}

	
}
