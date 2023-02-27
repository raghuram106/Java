package com.tweetapp.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
@SpringBootTest
public class JwtUtilTest {
	
	UserDetails userDetails;

	@MockBean
	JwtUtil jwtUtil;

	@Test
	void generateTokenTest() {
		userDetails = new User("admin", "admin", new ArrayList<>());
		String generateToken = jwtUtil.generateToken(userDetails);
		assertThat(generateToken).isNull();
	}

	@ParameterizedTest
	@ValueSource(strings = {"admin","root","user"})
	void validateTokenTest(String arg) {
		userDetails = new User(arg, "admin", new ArrayList<>());
		String generateToken = jwtUtil.generateToken(userDetails);
		Boolean validateToken = jwtUtil.validateToken(generateToken, userDetails);
		assertNotEquals(true, validateToken);
	}
	@Test
	void validateTokenWithNameTest() {
		userDetails = new User("admin", "admin", new ArrayList<>());
		String generateToken = jwtUtil.generateToken(userDetails);
		Boolean validateToken = jwtUtil.validateToken(generateToken, userDetails);
		assertEquals(false, validateToken);
	}

	@Test
	void validateTokenWithNameFalseTest() {
		userDetails = new User("admin", "admin", new ArrayList<>());
		String generateToken = jwtUtil.generateToken(userDetails);
		Boolean validateToken = jwtUtil.validateToken(generateToken, userDetails);
		assertNotEquals(true, validateToken);
    }
	

	

}
