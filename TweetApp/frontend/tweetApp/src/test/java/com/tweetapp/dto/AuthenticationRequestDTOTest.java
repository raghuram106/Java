package com.tweetapp.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tweetapp.dto.AuthenticationRequestDTO;

@SpringBootTest
public class AuthenticationRequestDTOTest {

	AuthenticationRequestDTO authReq = new AuthenticationRequestDTO();

	@Test
	void UserIsWorkingOrNot() {
		assertThat(authReq).isNotNull();
	}

	@Test
	void testingUser() throws ParseException {
		authReq = new AuthenticationRequestDTO("mani", "sai");
		authReq.setPassword("mani");
		authReq.setUsername("sai");
		assertEquals("sai", authReq.getUsername());
		assertEquals("mani", authReq.getPassword());

	}

}
