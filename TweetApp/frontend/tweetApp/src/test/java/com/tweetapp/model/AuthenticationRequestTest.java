package com.tweetapp.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tweetapp.model.AuthenticationRequest;

@SpringBootTest
public class AuthenticationRequestTest {

	AuthenticationRequest authReq = new AuthenticationRequest();

	@Test
	void UserIsWorkingOrNot() {
		assertThat(authReq).isNotNull();
	}

	@Test
	void testingUser() throws ParseException {
		authReq = new AuthenticationRequest("mani", "sai");
		authReq.setPassword("mani");
		authReq.setUsername("sai");
		assertEquals("sai", authReq.getUsername());
		assertEquals("mani", authReq.getPassword());

	}

}
