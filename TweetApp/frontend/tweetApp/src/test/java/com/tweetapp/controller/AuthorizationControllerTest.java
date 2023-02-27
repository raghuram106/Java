package com.tweetapp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.tweetapp.auth.JwtUtil;
import com.tweetapp.controller.AuthorizationController;
import com.tweetapp.dto.AuthenticationRequestDTO;
import com.tweetapp.exception.LoginException;
import com.tweetapp.repository.AuthRequestRepo;
import com.tweetapp.service.AppUserDetailsService;

import io.jsonwebtoken.SignatureException;

@SpringBootTest
public class AuthorizationControllerTest {

	@Mock
	private JwtUtil jwtUtil;

	@Mock
	private AppUserDetailsService appUserDetailsService;

	@Mock
	private AuthRequestRepo authRequestRepo;

	@InjectMocks
	private AuthorizationController authenticationController;

	@Test
	void testValidLogin() throws LoginException {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("mani", "sai");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(),
				authenticationRequestDTO.getPassword(), new ArrayList<>());

		when(appUserDetailsService.loadUserByUsername("mani")).thenReturn(userDetails);
		when(jwtUtil.generateToken(userDetails)).thenReturn("dummy-token");

		ResponseEntity<Object> authenticationResponse = authenticationController
				.createAuthorizationToken(authenticationRequestDTO);
		assertEquals(HttpStatus.OK, authenticationResponse.getStatusCode());
	}

	@Test
	void testInvalidLogin() {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("mani", "sai");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(), "sai", new ArrayList<>());

		when(appUserDetailsService.loadUserByUsername("sai")).thenReturn(userDetails);
		Exception exception = Assertions.assertThrows(NullPointerException.class,
				() -> authenticationController.createAuthorizationToken(authenticationRequestDTO));
		assertThat(exception).isNotNull();
	}

	@Test
	void testValidToken() {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("mani", "sai");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(),
				authenticationRequestDTO.getPassword(), new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenReturn(true);
		when(jwtUtil.extractUsername("token")).thenReturn("mani");
		when(appUserDetailsService.loadUserByUsername("mani")).thenReturn(userDetails);

		ResponseEntity<?> validity = authenticationController.validatingAuthorizationToken("Bearer token");
		assertFalse(validity.getBody().toString().contains("true"));
	}

	@Test
	void testInvalidToken() {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("mani", "mani");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(),
				authenticationRequestDTO.getPassword(), new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenReturn(false);
		when(jwtUtil.extractUsername("token")).thenReturn("mani");
		when(appUserDetailsService.loadUserByUsername("mani")).thenReturn(userDetails);

		ResponseEntity<?> validity = authenticationController.validatingAuthorizationToken("Bearer token");
		assertEquals(false, validity.getBody().toString().contains("false"));
	}

	@Test
	void testInvalidTokenWhenSignatureInvalid() {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("mani1", "mani1");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(),
				authenticationRequestDTO.getPassword(),

				new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenThrow(SignatureException.class);
		when(jwtUtil.extractUsername("token")).thenReturn("mani1");
		when(appUserDetailsService.loadUserByUsername("mani1")).thenReturn(userDetails);

		ResponseEntity<?> user = authenticationController.validatingAuthorizationToken("Bearer token");

	}

}
