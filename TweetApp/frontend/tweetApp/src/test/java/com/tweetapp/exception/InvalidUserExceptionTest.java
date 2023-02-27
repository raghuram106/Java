package com.tweetapp.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvalidUserExceptionTest {

	InvalidUserException invalidUserException = new InvalidUserException();
	
	@Test
	public void EmailTest() {
		assertThat(invalidUserException).isNotNull();
	}

}
