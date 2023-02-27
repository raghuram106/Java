package com.tweetapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tweetapp.exception.EmailExistsException;
import com.tweetapp.exception.InvalidFieldException;
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

	@Autowired
	UserRepo userRepo;

	@Autowired
	AuthRequestRepo authRepo;

	@Autowired
	UserService userService;

	@Test
	void TestForUserServiceRegisterwithEmailException() throws UserAlreadyExistsException, EmailExistsException {

		User user = new User("mani21", "ch", "mani", "mani21@gmail.com", "sai", "sai", "1234567890");
		userRepo.save(user);
		User u = new User("mani20", "ch", "mani", "mani21@gmail.com", "sai", "sai", "1234567890");
		AuthenticationRequest req = new AuthenticationRequest("mani", "sai");
		Assertions.assertThrows(EmailExistsException.class, () -> userService.registerUser(u, req));

	}

	@Test
	void TestForUserServiceRegisterwitPasswordException() throws UserAlreadyExistsException, EmailExistsException {

		User user = new User("mani", "ch", "mani", "mani@gmail.com", "sai", "saii", "1234567890");
		AuthenticationRequest req = new AuthenticationRequest("mani", "sai");
		Assertions.assertThrows(PasswordMismatchException.class, () -> userService.registerUser(user, req));

	}

	@Test
	void TestForUserServiceRegister() throws UserAlreadyExistsException, EmailExistsException, PasswordMismatchException, InvalidFieldException {

		User user = new User("mani33", "ch", "mani", "mani33@gmail.com", "sai", "sai", "1234567890");
		AuthenticationRequest req = new AuthenticationRequest("mani33", "sai");
		userService.registerUser(user, req);
		assertEquals("mani33", user.getLoginId());

	}



	@Test
	void TestForUserServiceRegisterwithUserException() throws UserAlreadyExistsException, EmailExistsException {

		User user = new User("mani2", "ch", "mani", "mani2@gmail.com", "sai", "sai", "1234567890");
		userRepo.save(user);
		User u1 = new User("mani2", "ch", "mani", "mani2@gmail.com", "sai", "sai", "1234567890");
		AuthenticationRequest req = new AuthenticationRequest("mani2", "sai");
		Assertions.assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(u1, req));

	}

	@Test
	void checkLoginIdTest() {
		User user = new User("mani3", "ch", "mani", "mani3@gmail.com", "sai", "sai", "1234567890");
		userRepo.save(user);
		Boolean y = userService.checkLoginId(user.getLoginId());
		assertThat(y).isTrue();
	}

	@Test
	void checkEmailIdTest() {

		User user = new User("mani22", "ch", "mani", "mani22@gmail.com", "sai", "sai", "1234567890");
		userRepo.save(user);
		Boolean y = userService.checkEmailId(user.getEmail());
		assertThat(y).isTrue();

	}

	@Test
	void LoginUserExceptionTest() throws UserNotExistsException {

		User user = new User("sai", "ch", "mani", "sai@gmail.com", "sai", "sai", "1234567890");
		userRepo.save(user);
		Assertions.assertThrows(UserNotExistsException.class,()->userService.loginUser("mani8", "saii"));
	}
	
	@Test
	void LoginUserInvalidExceptionTest() throws InvalidFieldException {

		User user = new User("sai22", "ch", "mani", "sai22@gmail.com", "sai", "sai", "1234567890");
		userRepo.save(user);
		Assertions.assertThrows(InvalidFieldException.class,()->userService.loginUser("", "saii"));
	}
	

	@Test
	void forgotTest() throws PasswordMismatchException, UserNotExistsException, InvalidFieldException {

		User user = new User("mani8", "ch", "mani", "mani8@gmail.com", "sai", "sai", "1234567890");
		AuthenticationRequest a = new AuthenticationRequest();
		a.setUsername("mani8");
		a.setPassword("sai");
		authRepo.save(a);
		userRepo.save(user);

		User user1 = new User("mani8", "ch", "mani", "mani8@gmail.com", "saii", "saii", "1234567890");
		User u = userService.forgotPassword("mani8", user1);
		assertThat(u).isNotNull();
	}

	@Test
	void forgotExceptionTest() throws PasswordMismatchException {

		User user = new User("mani9", "ch", "mani", "mani8@gmail.com", "sai", "sai", "1234567890");
		userRepo.save(user);

		User user1 = new User("mani9", "ch", "mani", "mani8@gmail.com", "sai", "saii", "1234567890");
		Assertions.assertThrows(PasswordMismatchException.class,()->userService.forgotPassword("mani8", user1));

		
	}
	
	@Test
	void invalidFieldExceptionTest() throws InvalidFieldException {

		User user = new User("mani9", "ch", "mani", "mani8@gmail.com", "sai", "sai", "1234567890");
		userRepo.save(user);

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
