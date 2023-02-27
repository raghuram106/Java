package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.auth.AuthenticationResponse;
import com.tweetapp.auth.JwtUtil;
import com.tweetapp.dto.AuthenticationRequestDTO;
import com.tweetapp.dto.ValidatingDTO;
import com.tweetapp.exception.EmailExistsException;
import com.tweetapp.exception.InvalidFieldException;
import com.tweetapp.exception.LoginException;
import com.tweetapp.exception.PasswordMismatchException;
import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.model.AuthenticationRequest;
import com.tweetapp.model.User;
import com.tweetapp.service.AppUserDetailsService;
import com.tweetapp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorizationController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuthorizationController.class);
	@Autowired
	private AppUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;


	@Autowired
	UserService userService;

	private ValidatingDTO vaildatingDTO = new ValidatingDTO();

	@PostMapping("/login")
	public ResponseEntity<Object> createAuthorizationToken(
			@RequestBody AuthenticationRequestDTO authenticationRequestDTO) throws LoginException {

		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setUsername(authenticationRequestDTO.getUsername());
		authenticationRequest.setPassword(authenticationRequestDTO.getPassword());
		log.info("BEGIN - [login]");
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		log.debug("{}", userDetails);

		if (userDetails.getPassword().equals(authenticationRequest.getPassword())) {
			log.info("END - [login]");
			return new ResponseEntity<>(new AuthenticationResponse(authenticationRequest.getUsername(),
					jwtTokenUtil.generateToken(userDetails), jwtTokenUtil.getCurrentTime(),
					jwtTokenUtil.getExpirationTime()), HttpStatus.OK);
		}

		log.info("END - [login(customerLoginCredentials)]");
		throw new LoginException("Invalid Username or Password");
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user)
			throws UserAlreadyExistsException, PasswordMismatchException, LoginException, EmailExistsException, InvalidFieldException {

		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setUsername(user.getLoginId());
		authenticationRequest.setPassword(user.getPassword());
		User u = ((UserService) userService).registerUser(user, authenticationRequest);

		log.info("BEGIN - [Registration]");
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		log.debug("{}", userDetails);

		if (userDetails.getPassword().equals(authenticationRequest.getPassword())) {
			log.info("END - [login(customerLoginCredentials)]");
			return new ResponseEntity<>(new AuthenticationResponse(authenticationRequest.getUsername(),
					jwtTokenUtil.generateToken(userDetails), jwtTokenUtil.getCurrentTime(),
					jwtTokenUtil.getExpirationTime()), HttpStatus.OK);
		}

		return new ResponseEntity<User>(u, HttpStatus.CREATED);

	}

	@GetMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ValidatingDTO> validatingAuthorizationToken(
			@RequestHeader(name = "Authorization") String tokenDup) {

		log.info("BEGIN - [validatingAuthorizationToken(JWT-token)]");
		String token = tokenDup.substring(7);
		try {
			UserDetails user = userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(token));
			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(token, user))) {
				log.debug("Token matched is Valid");
				log.info("Token matched is Valid");
				log.info("END - validate()");
				vaildatingDTO.setValidStatus(true);
				return new ResponseEntity<>(vaildatingDTO, HttpStatus.OK);
			} else {
				throw new LoginException("Invalid Token");
			}
		} catch (Exception e) {
			log.debug("Invalid token - Bad Credentials Exception");
			log.info("END Exception - validatingAuthorizationToken()");
			vaildatingDTO.setValidStatus(false);
			return new ResponseEntity<>(vaildatingDTO, HttpStatus.BAD_REQUEST);
		}

	}


}
