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
		authRes = new AuthenticationResponse("mani", "sai", 20000, 4000);
		authRes.setJwtAuthToken("mani");
		authRes.setUsername("sai");
		authRes.setServerCurrentTime(1000);
		authRes.setTokenExpirationTime(30000);
		assertEquals("sai", authRes.getUsername());
		assertEquals("mani", authRes.getJwtAuthToken());
		assertNotEquals(100, authRes.getServerCurrentTime());
		assertNotEquals(2000, authRes.getTokenExpirationTime());

	}

}
