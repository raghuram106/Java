package com.tweetapp.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tweetapp.exception.LoginException;

@SpringBootTest
public class LoginExceptionTest {
	
LoginException loginException = new LoginException(null);
	
	@Test
	public void EmailTest() {
		assertThat(loginException).isNotNull();
	}

}
