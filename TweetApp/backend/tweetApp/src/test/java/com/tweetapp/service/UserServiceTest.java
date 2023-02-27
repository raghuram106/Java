package com.tweetapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tweetapp.exception.EmailExistsException;
import com.tweetapp.exception.InvalidFieldException;
import com.tweetapp.exception.InvalidUserException;
import com.tweetapp.exception.PasswordIncorrectExceptionTest;
import com.tweetapp.exception.PasswordMismatchException;
import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.exception.UserNotExistsException;
import com.tweetapp.exception.PasswordIncorrectException;
import com.tweetapp.model.AuthenticationRequest;
import com.tweetapp.model.User;
import com.tweetapp.repository.AuthRequestRepo;
import com.tweetapp.repository.UserRepo;

@SpringBootTest()
public class UserServiceTest {

	User user = new User();

	@Mock
	UserRepo userRepo;

	@Mock
	AuthRequestRepo authRepo;

	@InjectMocks
	UserService userService;

	

	@Test
	void TestForUserServiceRegisterwitPasswordException() throws UserAlreadyExistsException, EmailExistsException {

		User user = new User("mani", "ch", "mani", "mani@gmail.com", "sai", "saii", "1234567890");
		AuthenticationRequest req = new AuthenticationRequest("mani", "sai");
		Assertions.assertThrows(PasswordMismatchException.class, () -> userService.registerUser(user, req));

	}


	@Test
	void checkLoginIdTest() {
		User user = new User("mani", "ch", "mani", "mani@gmail.com", "sai", "sai", "1234567890");
		AuthenticationRequest req = new AuthenticationRequest("mani", "sai");
		userRepo.save(user);
		authRepo.save(req);
		Boolean y = userService.checkLoginId("mani");
		assertThat(user).isNotNull();
	}

	@Test
	void checkEmailIdTest() {
		User user = new User("mani1", "ch", "mani1", "mani1@gmail.com", "sai", "sai", "1234567890");
		AuthenticationRequest req = new AuthenticationRequest("mani1", "sai");
		userRepo.save(user);
		authRepo.save(req);

		Boolean y = userService.checkEmailId("mani1@gmail.com");
		assertThat(user).isNotNull();

	}

	
	@Test
	void LoginUserInvalidExceptionTest() throws InvalidFieldException {

		Assertions.assertThrows(InvalidFieldException.class,()->userService.loginUser("", "saii"));
	}
	

	@Test
	void forgotTest() throws PasswordMismatchException, UserNotExistsException, InvalidFieldException {

		User user1 = new User("mani1", "ch", "mani", "mani8@gmail.com", "saii", "saii", "1234567890");
		
		Assertions.assertThrows(UserNotExistsException.class, ()->userService.forgotPassword("mani1", user1));
	}

	@Test
	void forgotExceptionTest() throws PasswordMismatchException {


		User user1 = new User("mani9", "ch", "mani", "mani8@gmail.com", "sai", "saii", "1234567890");
		Assertions.assertThrows(PasswordMismatchException.class,()->userService.forgotPassword("mani1", user1));

		
	}
	
	@Test
	void invalidFieldExceptionTest() throws InvalidFieldException {


		User user1 = new User(" ", "ch", "mani", "mani8@gmail.com", " ", " ", "1234567890");
		Assertions.assertThrows(InvalidFieldException.class,()->userService.forgotPassword("", user1));

		
	}
	
	@Test
	void invalidExceptionInUserTest() throws InvalidFieldException{
		
		User user = new User("mani33", "ch", "mani", "", "sai", "sai", "1234567890");
		AuthenticationRequest req = new AuthenticationRequest("mani33", "sai");
		Assertions.assertThrows(InvalidFieldException.class,()->userService.registerUser(user, req));
	}
	
	
	

}
