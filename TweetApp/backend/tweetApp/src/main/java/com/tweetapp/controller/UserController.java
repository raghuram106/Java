package com.tweetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.auth.AuthenticationResponse;
import com.tweetapp.auth.JwtUtil;
import com.tweetapp.exception.EmailExistsException;
import com.tweetapp.exception.InvalidFieldException;
import com.tweetapp.exception.LoginException;
import com.tweetapp.exception.PasswordMismatchException;
import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.exception.UserNotExistsException;
import com.tweetapp.model.AuthenticationRequest;
import com.tweetapp.model.User;
import com.tweetapp.service.AppUserDetailsService;
import com.tweetapp.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1.0/tweets")
public class UserController {


	@Autowired
	UserService userService;
	
	@Autowired
	private AppUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;

	
	@GetMapping("users/all")
	public ResponseEntity<List<User>> allUsers() {

		List<User> users = userService.allUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PostMapping("/{username}/forgot")
	public ResponseEntity<?> forgotPassword(@PathVariable("username") String username, @RequestBody User user)
			throws UserAlreadyExistsException, PasswordMismatchException, LoginException, EmailExistsException, UserNotExistsException, InvalidFieldException {
		User u = ((UserService) userService).forgotPassword(username, user);
		
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setUsername(u.getLoginId());
		authenticationRequest.setPassword(u.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		if (userDetails.getPassword().equals(authenticationRequest.getPassword())) {
			return new ResponseEntity<>(new AuthenticationResponse(authenticationRequest.getUsername(),
					jwtTokenUtil.generateToken(userDetails), jwtTokenUtil.getCurrentTime(),
					jwtTokenUtil.getExpirationTime()), HttpStatus.OK);
		}

		return new ResponseEntity<User>(u, HttpStatus.CREATED);

	}
	
	@GetMapping("/user/search/{str}")
	public List<User> searchByRegex(@PathVariable("str") String str){
		return userService.searchByRegex(str);
	}

}
