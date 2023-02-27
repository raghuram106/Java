package com.tweetapp.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthenticationResponseTest {

	AuthenticationResponse authRes = new AuthenticationResponse();

	@Test
	void UserIsWorkingOrNot() {
		assertThat(authRes).isNotNull();
	}

	@Test
	void testingUser() throws ParseException {
		authRes = new AuthenticationResponse("raghu", "ram", 20000, 4000);
		authRes.setJwtAuthToken("raghu");
		authRes.setUsername("ram");
		authRes.setServerCurrentTime(1000);
		authRes.setTokenExpirationTime(30000);
		assertEquals("ram", authRes.getUsername());
		assertEquals("raghu", authRes.getJwtAuthToken());
		assertNotEquals(100, authRes.getServerCurrentTime());
		assertNotEquals(2000, authRes.getTokenExpirationTime());

	}

}
