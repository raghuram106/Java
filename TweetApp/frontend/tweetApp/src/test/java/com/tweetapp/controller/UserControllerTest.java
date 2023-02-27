package com.tweetapp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.tweetapp.dto.AuthenticationRequestDTO;
import com.tweetapp.exception.EmailExistsException;
import com.tweetapp.exception.InvalidFieldException;
import com.tweetapp.exception.LoginException;
import com.tweetapp.exception.PasswordMismatchException;
import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.exception.UserNotExistsException;
import com.tweetapp.exception.PasswordIncorrectException;
import com.tweetapp.model.User;
import com.tweetapp.repository.UserRepo;

@SpringBootTest
public class UserControllerTest {

	@Autowired
	UserController userController;

	@Autowired
	UserRepo userRepo;

	@Test
	void allUsersTest() {

		User user = new User("mani50", "ch", "mani", "mani50@gmail.com", "sai", "sai", "1234567890");
		userRepo.save(user);
		ResponseEntity<List<User>> users = userController.allUsers();
		assertEquals(HttpStatus.OK,users.getStatusCode());
	}

	@Test
	void forgotPasswordTest() throws PasswordMismatchException, UserNotExistsException, UserAlreadyExistsException, LoginException, EmailExistsException, InvalidFieldException {

		User user = new User("sai50", "ch", "mani", "sai50@gmail.com", "sai", "sai", "1234567890");
		userRepo.save(user);
		String username = "sai50";
		User user1 = new User("sai50", "hi", "hi", "sai50@gmail.com", "sai", "sai", "");
		 //userController.forgotPassword(username, user1);
		//assertThat(HttpStatus.CREATED);
		

	}

	@Test
	void LoginUserExceptionTest() throws PasswordIncorrectException, UserNotExistsException {

		User user = new User("sai1", "ch", "mani", "sai1@gmail.com", "sai", "sai", "1234567890");
		userRepo.save(user);
		Assertions.assertThrows(UserNotExistsException.class, () -> userController.forgotPassword("manisha", user));
	}

}
