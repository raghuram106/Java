package com.tweetapp.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserNotExistsExceptionTest {
	
	UserNotExistsException userException = new UserNotExistsException();

	@Test
	public void UserTest() {
		assertThat(userException).isNotNull();
	}

}
