package com.authorizationservice.authorization.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter

public class AuthenticationRequestDTO {
	private String username;
	private String password;

}
