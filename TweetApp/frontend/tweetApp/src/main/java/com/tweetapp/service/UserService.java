package com.tweetapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.exception.EmailExistsException;
import com.tweetapp.exception.InvalidFieldException;
import com.tweetapp.exception.PasswordMismatchException;
import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.exception.UserNotExistsException;
import com.tweetapp.exception.PasswordIncorrectException;
import com.tweetapp.model.AuthenticationRequest;
import com.tweetapp.model.User;
import com.tweetapp.repository.AuthRequestRepo;
import com.tweetapp.repository.UserRepo;

@Service
public class UserService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);

	String message;

	@Autowired
	UserRepo userRepo;

	@Autowired
	AuthRequestRepo authRepo;
	

	public User registerUser(User user, AuthenticationRequest authUser) throws UserAlreadyExistsException, EmailExistsException, PasswordMismatchException, InvalidFieldException {
		log.info("BEGIN - [Register Service]");
		if(user.getFirstName()=="" || user.getLastName()=="" || user.getLoginId()=="" || user.getPassword()=="" || user.getConfirmPassword()=="" || user.getEmail()=="" || user.getContactNumber()=="")
		{
			throw new InvalidFieldException();
		}
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			throw new PasswordMismatchException();
		} else if (checkLoginId(user.getLoginId())) {
			throw new UserAlreadyExistsException();
		}
		else if(checkEmailId(user.getEmail())) {
			throw new EmailExistsException();
		}
		log.info("END - [Register Service]");
		authRepo.save(authUser);
		return userRepo.save(user);

	}

	public boolean checkLoginId(String id) {
		log.info("BEGIN - [Check Id Service]");
		Boolean x = false;
		List<User> u = userRepo.findAll();
		for (User i : u) {
			if (i.getLoginId().equals(id)) {
				x = true;
				break;
			}
		}
		log.info("END - [Check Id Service]");
		return x;

	}
	public boolean checkEmailId(String emailId) {
		log.info("BEGIN - [Check EmailId Service]");
		Boolean x = false;
		List<User> u = userRepo.findAll();
		for (User i : u) {
			if (i.getEmail().equals(emailId)) {
				x = true;
				break;
			}
		}
		log.info("END - [Check EmailId Service]");
		return x;

	}
	public List<User> allUsers() {
		log.info("BEGIN - [All Users Service]");
		log.info("END - [All Users Service]");
		return (List<User>) userRepo.findAll();

	}

	public String loginUser(String username, String password)
			throws PasswordIncorrectException, UserNotExistsException, InvalidFieldException {
		log.info("BEGIN - [Login Service]");
		List<User> u = userRepo.findAll();
		if(username==""|| password=="") {
			throw new InvalidFieldException();
		}
		for (User i : u) {
			if (i.getLoginId().equals(username) && i.getPassword().equals(password)) {
				message = "user login successful";
				break;
			} else if (i.getLoginId().equals(username) && !i.getPassword().equals(password)) {
				throw new PasswordIncorrectException();
			} else {
				throw new UserNotExistsException();
			}
		}
		log.info("END - [Login Service]");
		return message;
	}

	public User forgotPassword(String username, User user) throws PasswordMismatchException, UserNotExistsException, InvalidFieldException {
		log.info("BEGIN - [Forgot Password Service]");
		List<User> users = userRepo.findAll();
		if(user.getPassword()=="" || user.getConfirmPassword()=="" || username=="") {
			throw new InvalidFieldException();
		}
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			throw new PasswordMismatchException();
		}
		for (User u : users) {
			if (u.getLoginId().equals(username)) {
				u.setPassword(user.getPassword());
				u.setConfirmPassword(user.getConfirmPassword());
				userRepo.save(u);
				List<AuthenticationRequest> a = authRepo.findAll();
				for (AuthenticationRequest b : a) {
					if (b.getUsername().equals(username)) {
						b.setPassword(u.getPassword());
						authRepo.save(b);
						break;
					}
				}
				return u;

			}

		}
		
		log.info("BEGIN - [Forgot Password Service]");
		throw new UserNotExistsException();
	}

}
